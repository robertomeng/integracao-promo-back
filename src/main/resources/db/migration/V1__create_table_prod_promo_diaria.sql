-- para Postgres
--CREATE TABLE public.prod_promo_diaria (
--	id serial NOT NULL,
--	descricao varchar(100) NOT NULL,
--	id_identificador int4 NOT NULL,
--	dt_inicio date NULL,
--	dt_fim date NULL,
--	prc_venda float8 NOT NULL,
--	vl_custo float8 NULL,
--	vl_promocao float8 NULL,
--	qt_atual float8 NULL,
--	cod_barra varchar(15) NULL,
--	cod_ncm varchar(20) NULL,
--	uni_medida varchar(30) NULL,
--	sync bool NOT NULL DEFAULT false,
--	ativo bool NOT NULL DEFAULT false,
--	CONSTRAINT id_identificador_uk UNIQUE (id_identificador)
--);

-- Para Firebird
CREATE TABLE prod_promo_diaria (
	id integer not null PRIMARY KEY,
	nome varchar(100),
	descricao BLOB SUB_TYPE TEXT,
	id_identificador integer,
	dt_inicio date,
	dt_fim date,
	prc_venda float,
	vl_custo float,
	vl_promocao float,
	qt_atual float,
	cod_barra varchar(15),
	cod_ncm varchar(20),
	uni_medida varchar(30),
	sync char(1),
	ativo char(1),
	log BLOB SUB_TYPE TEXT,
	CONSTRAINT id_identificador_uk UNIQUE (id_identificador)
);

-- codigo para auto incrementar o id da tabela prod_promo_diaria
CREATE GENERATOR sq_prod_promo_diaria;

CREATE TRIGGER tr_prod_promo_diaria for prod_promo_diaria
BEFORE INSERT position 0
AS
BEGIN
new.id = gen_id(sq_prod_promo_diaria,1);
END;


-- para postgres
--CREATE TABLE public.config (
--	tempo_scan int4 NOT NULL DEFAULT 300000,
--	id serial NOT NULL,
--	CONSTRAINT config_pk PRIMARY KEY (id)
--);

-- para firebird
CREATE TABLE config (
	id integer,
	tempo_scan integer,
	url_integracao varchar(255),
	token varchar(255),
	CONSTRAINT config_pk PRIMARY KEY (id)
);

-- tempo scan = 5 minutos
INSERT INTO config (id, tempo_scan, url_integracao, token) 
VALUES (1, 300000, 'https://promocaodiaria.com/wshomologacao', '7c8bb07ff4b9bf1bbb6215f95c1b04d2');