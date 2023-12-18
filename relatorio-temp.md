# Relatório

1. Dada uma série de compras e vendas, calcular se teve lucro e testar

- Criada classe de teste CarteiraTest com entradas: "quantidade", "ticker", "valor" e "tipoOrdem"

- Criada classe carteira

- Criado método "enviarOrdem" que recebe esses campos como entrada

- Criada classe Ordem com esses campos

- Criada classe Ativo

- Carteira recebe lista de Ordem de e lista de Ativo

- Criado método enviarOrdemCompraTest em CarteiraTest

- Verificado inicialmente se uma Ordem de compra gera pelo menos  um ativo na carteira

```
@Test
	void enviarOrdemCompraTest() {
		Carteira carteira = new Carteira();
		
		try {
			carteira.enviarOrdem(new Ordem("ITUB4", new BigDecimal(50.00), 100, TipoOrdem.COMPRA));
			System.out.println(carteira.getAtivos().get(0).getTicket());
			Assertions.assertTrue(!carteira.getAtivos().isEmpty());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO testar impacto da ordem na carteira
	}
```
- Verificado o cálculo do novo preço médio

```
@Test
	void enviarOrdemCompraTest() {
		Carteira carteira = new Carteira();
		
		try {
			OrdemBuilder builder = new OrdemBuilder();
			Ordem ordem1 = builder.comTicket("ITUB4").comValor(new BigDecimal(50.00)).comQuantidade(100).comTipo(TipoOrdem.COMPRA).build();
			Ordem ordem2 = builder.comTicket("ITUB4").comValor(new BigDecimal(60.00)).comQuantidade(100).comTipo(TipoOrdem.COMPRA).build();
			carteira.enviarOrdem(ordem1);
			carteira.enviarOrdem(ordem2);
			System.out.println(carteira.getAtivos().get(0).toString());
			Assertions.assertEquals(new BigDecimal(55.00).setScale(2), carteira.getAtivos().get(0).getPrecoMedio());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
```