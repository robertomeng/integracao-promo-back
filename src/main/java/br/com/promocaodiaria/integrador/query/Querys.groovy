package br.com.promocaodiaria.integrador.query

class Querys {

	public static final String select_produtos = """
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
			b.DESCRICAO LIKE '%'||:query||'%'
	"""
}
