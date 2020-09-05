package br.com.promocaodiaria.integrador.query

class QueriesClippStore {
	
	private static final String select_produtos = """
		SELECT a.ID_IDENTIFICADOR as referencia_produto,
 b.DESCRICAO as nome,
 b.OBSERVACAO as descricao,
 u.DESCRICAO as unidade_medida,
 a.COD_BARRA as gtin,
 a.COD_NCM as ncm,
 b.PRC_VENDA as valor,
 a.QTD_ATUAL as estoque,
 c.DT_INICIO as data_inicio_promocao,
 c.DT_FIM as data_termino_Promocao,
 c.PRC_VENDA as valor_promocao,
 iif (b.STATUS = 'A','S','N') as ativo
 FROM TB_EST_PRODUTO a INNER JOIN TB_ESTOQUE b 
ON b.ID_ESTOQUE = a.ID_IDENTIFICADOR
 left JOIN TB_ESTOQUE_PRECOS c
ON a.ID_IDENTIFICADOR = c.ID_IDENTIFICADOR
 left join TB_UNI_MEDIDA u
 on u.UNIDADE = b.UNI_MEDIDA
 where b.STATUS = 'A'
AND %s
 group by a.ID_IDENTIFICADOR,
 b.DESCRICAO,
 b.OBSERVACAO,
 u.DESCRICAO,
 a.COD_BARRA,
 a.COD_NCM,
 b.PRC_VENDA,
 a.QTD_ATUAL,
 c.DT_INICIO,
 c.DT_FIM,
 c.PRC_VENDA,
 b.STATUS
	"""
	
	public static final String select_produtos_por_id = String.format(select_produtos, " a.ID_IDENTIFICADOR = :idIdentificador");

	public static final String select_produtos_por_descricao = String.format(select_produtos, " b.DESCICAO LIKE UPPER('%'||:query||'%') AND a.ID_IDENTIFICADOR NOT IN (:idsNotIn) ");
}
