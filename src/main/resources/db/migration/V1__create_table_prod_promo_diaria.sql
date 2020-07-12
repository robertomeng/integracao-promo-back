CREATE TABLE public.prod_promo_diaria (
	id serial NOT NULL,
	descricao varchar(100) NOT NULL,
	id_identificador int4 NOT NULL,
	dt_inicio date NULL,
	dt_fim date NULL,
	prc_venda float8 NOT NULL,
	vl_custo float8 NULL,
	vl_promocao float8 NULL,
	qt_atual float8 NULL,
	cod_barra varchar(15) NULL,
	cod_ncm varchar(20) NULL,
	uni_medida varchar(30) NULL,
	sync bool NOT NULL DEFAULT false,
	ativo bool NOT NULL DEFAULT false,
	CONSTRAINT id_identificador_uk UNIQUE (id_identificador)
);