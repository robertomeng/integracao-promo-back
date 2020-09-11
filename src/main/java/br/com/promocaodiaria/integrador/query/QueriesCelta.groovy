package br.com.promocaodiaria.integrador.query

class QueriesCelta {

	private static final String select_produtos = """
	select
		p.PRO_CODIGO as id_identificador,
		p.PRO_DESCRICAO as nome,
		p.CODIGO_BARRAS as cod_barra,
		p.PRECO_VENDA as valor,
		p.ESTOQUE_DISPONIVEL as qtd_atual,
		p.APLICACOES as descricao,
		u.UM_DESCRICAO as unidade_medida,
		p.NCM as cod_ncm,
		c.DATA_INICIAL as data_inicio_promocao,
		c.DATA_FINAL as data_termino_promocao,
		i.PROM_VALOR as valor_promocao,
		iif (p.INATIVO = 'N','S','N') as ativo
	from PRODUTOS p
		left join MARCAS m
			on m.MAR_CODIGO = p.MAR_CODIGO
		left join UNIDADE_MEDIDA u
			on u.UM_SIGLA = p.UNIDADE
		left join PROMOCOES_ITENS i
			on i.PRO_CODIGO = p.PRO_CODIGO
		left join PROMOCOES c
			on c.PROM_CODIGO = i.PROM_CODIGO
		WHERE a.INATIVO = 'F'
			AND %s
		group by
			p.PRO_CODIGO,
			p.PRO_DESCRICAO,
			p.CODIGO_BARRAS,
			p.PRECO_VENDA,
			p.ESTOQUE_DISPONIVEL,
			p.APLICACOES,
			u.UM_DESCRICAO,
			p.NCM,
			p.INATIVO,
			p.UNIDADE,
			c.DATA_INICIAL,
			c.DATA_FINAL,
			i.PROM_VALOR
	"""

	public static final String select_produtos_por_id = String.format(select_produtos, " p.PRO_CODIGO = :idIdentificador");

	public static final String select_produtos_por_descricao = String.format(select_produtos, " p.PRO_DESCRICAO LIKE UPPER('%'||:query||'%') AND p.PRO_DESCRICAO NOT IN (:idsNotIn) ");
}
