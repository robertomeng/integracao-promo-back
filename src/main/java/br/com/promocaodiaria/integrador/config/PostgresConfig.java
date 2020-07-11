package br.com.promocaodiaria.integrador.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "pgEntityManagerFactory", transactionManagerRef = "pgTransactionManager", basePackages = "br.com.promocaodiaria.integrador.pg")
public class PostgresConfig {

	@Bean(name = "pgDataSource")
	@ConfigurationProperties(prefix = "spring.second-datasource")
	public HikariDataSource pgDataSource() {
		return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
	}

	@Bean(name = "pgEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean pgEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("pgDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("br.com.promocaodiaria.integrador.pg.model")
				.persistenceUnit("pgPU").build();
	}

	@Bean(name = "pgTransactionManager")
	public PlatformTransactionManager pgTransactionManager(
			@Qualifier("pgEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
