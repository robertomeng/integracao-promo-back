package br.com.promocaodiaria.integrador.query

class QueriesEcoSys {

	private static final String select_produtos = """
		select
			p.produto as referencia_produto,
			g.DESCRICAO as nome,
			p.PRPRATICADO as preco,
			p.ESTDISPONIVEL as estoque,
			g.APLICACAO as descricao,
			m.DESCRICAO as marca,
			e.DESCRICAO as unidade_medida,
			g.CODIGOBARRA as gtin,
			G.CLASSIFICACAOFISCAL AS ncm,
			iif (p.ativo = 'S','T','F') as ativo
		 from testproduto p
			 left join testprodutogeral g
				 on g.CODIGO = p.PRODUTO
			 left join TESTMARCA m
				 on m.CODIGO = g.MARCA
			 left join TESTEMBALAGEM e
				 on e.CODIGO = g.EMBALAGEM
		 group by p.produto,
			 g.DESCRICAO,
			 p.ESTDISPONIVEL,
			 p.TABELAPROMOCIONAL,
			 p.ativo,
			 g.CODIGOBARRA,
			 m.DESCRICAO,
			 g.APLICACAO,
			 g.CLASSIFICACAOFISCAL,
			 p.PRPRATICADO,
			 e.DESCRICAO,
			 g.EMBALAGEM,
			 g.EMBALAGEM
	"""

	public static final String select_produtos_por_id = String.format(select_produtos, " p.PRODUTO = :idIdentificador");

	public static final String select_produtos_por_descricao = String.format(select_produtos, " g.DESCRICAO LIKE UPPER('%'||:query||'%') AND g.DESCRICAO NOT IN (:idsNotIn) ");
}
