package br.com.promocaodiaria.integrador.schedules;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

import br.com.promocaodiaria.integrador.fire.model.ProdutoClienteWrapper;
import br.com.promocaodiaria.integrador.fire.repository.ProdutoClienteRepository;
import br.com.promocaodiaria.integrador.pg.model.Config;
import br.com.promocaodiaria.integrador.pg.model.ProdutoPromoDiaria;
import br.com.promocaodiaria.integrador.pg.repository.ConfigRepository;
import br.com.promocaodiaria.integrador.pg.repository.ProdutoPromoDiariaRepository;
import br.com.promocaodiaria.integrador.service.ProdutoPromoDiariaService;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class Agendador implements SchedulingConfigurer {

	@Autowired
	ConfigRepository configRepository;

	@Autowired
	ProdutoPromoDiariaRepository produtoPromoDiariaRepository;

	@Autowired
	ProdutoClienteRepository estoqueRepository;
	
	@Autowired
	ProdutoPromoDiariaService produtoPromoDiariaService;

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.addTriggerTask(new Runnable() {

			@Override
			public void run() {

				log.info("Iniciando busca de produtos a atualizar");
				List<ProdutoPromoDiaria> promocoes = produtoPromoDiariaRepository.findAll();

				promocoes.forEach(produto -> {
					try {
						
						log.info("Produto a ser atualizado {}", produto.toString());
						
						ProdutoClienteWrapper produtoCliente = estoqueRepository.getProdutoAlterado(produto);
						
						
						boolean stockHadUpdate = stockHadUpdate(produto, produtoCliente);
						boolean produtoHadUpdate = produtoHadUpdate(produto, produtoCliente);
						
						if (produtoCliente != null && (produtoHadUpdate || stockHadUpdate)) {
							
							boolean updateOnlyStock = !produtoHadUpdate && stockHadUpdate;
							
							ProdutoPromoDiaria atualizado = produtoPromoDiariaService.update(produto, produtoCliente, updateOnlyStock);
							
							log.info("Produto Atulizado com sucesso produto {}", atualizado.toString());
						}
					
					} catch (EmptyResultDataAccessException e) {
						log.info("Este produto nao teve alteracao recente id={} desc={}", produto.getIdIdentificador(), produto.getDescricao());
					
					} catch (IncorrectResultSizeDataAccessException e) {
						log.info("Encontrou mais de um produto com este id={} desc={}", produto.getIdIdentificador(), produto.getDescricao());
					}
				});

			}
		}, new Trigger() {

			@Override
			public Date nextExecutionTime(TriggerContext triggerContext) {
				Calendar nextExecutionTime = new GregorianCalendar();
				Date lastActualExecutionTime = triggerContext.lastActualExecutionTime();
				nextExecutionTime.setTime(lastActualExecutionTime != null ? lastActualExecutionTime : new Date());
				nextExecutionTime.add(Calendar.MILLISECOND, getNewExecutionTime());
				return nextExecutionTime.getTime();
			}
		});
	}

	private int getNewExecutionTime() {
		List<Config> config = configRepository.findAll();
		return config.get(0).getTempoScan().intValue();
	}
	
	private boolean stockHadUpdate(ProdutoPromoDiaria produto, ProdutoClienteWrapper produtoCliente) {
		
		return !produto.getQtAtual().equals(produtoCliente.getQtAtual());

	}
	
	private boolean produtoHadUpdate(ProdutoPromoDiaria produto, ProdutoClienteWrapper produtoCliente) {
		return !(isEquals(produto.getCodBarra(), produtoCliente.getCodBarra())
				|| isEquals(produto.getCodNcm(), produtoCliente.getCodNcm())
				|| isEquals(produto.getNome(), produtoCliente.getNome())
				|| isEquals(produto.getDescricao(), produtoCliente.getDescricao())
				|| isEquals(produto.getUniMedida(), produtoCliente.getUniMedida())
				|| isEquals(produto.getValor(), produtoCliente.getValor())
				|| isEquals(produto.getVlPromocao(), produtoCliente.getVlPromocao())
				|| isEquals(produto.getDtInicio(), produtoCliente.getDtInicio())
				|| isEquals(produto.getDtFim(), produtoCliente.getDtFim()));
	}
	
	private boolean isEquals(Object obj1, Object obj2) {
		boolean bothNulls = Objects.isNull(obj1) && Objects.isNull(obj2);
		
		if (!bothNulls) {
			
			
			if (Objects.isNull(obj1) || Objects.isNull(obj2)) {
				return  false;
			}
			
			if(obj1.equals(obj2)) {
				return true;
			}
		}
		
		return bothNulls;
	}
}
