package br.com.promocaodiaria.integrador.query

class Queries {
	
	private static final String select_produtos = """
	
		SELECT 
			a.ID_IDENTIFICADOR, 
			b.DESCRICAO, 
			b.PRC_VENDA, 
			a.QTD_ATUAL, 
			a.COD_BARRA, 
			a.COD_NCM, 
			b.UNI_MEDIDA, 
			c.DT_INICIO, 
			c.DT_FIM
		FROM TB_EST_PRODUTO a INNER JOIN TB_ESTOQUE b
			ON b.ID_ESTOQUE = a.ID_IDENTIFICADOR
		LEFT JOIN TB_ESTOQUE_PRECOS c 
			ON a.ID_IDENTIFICADOR = c.ID_IDENTIFICADOR 
		WHERE
	"""
	
	public static final String select_produtos_por_id = select_produtos.concat(" a.ID_IDENTIFICADOR = :id")

	public static final String select_produtos_por_descricao = select_produtos.concat(" b.DESCRICAO LIKE '%'||:query||'%'")
	
	public static final String select_produto_alterado = select_produtos.concat(""" a.ID_IDENTIFICADOR = :idIdentificador
		AND (
			a.QTD_ATUAL     != :qtAtual
			OR a.COD_BARRA  != :codBarra
			OR a.COD_NCM    != :codNcm
			OR b.DESCRICAO  != :descricao
			OR b.PRC_VENDA  != :prcVenda 
			OR b.UNI_MEDIDA != :uniMedida
		)
	""")
}
