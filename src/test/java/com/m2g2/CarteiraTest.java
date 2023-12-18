package com.m2g2;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.m2g2.enums.TipoOrdem;
import com.m2g2.model.Carteira;
import com.m2g2.model.Ordem;
import com.m2g2.model.builder.OrdemBuilder;

class CarteiraTest {

	// Método temporário apenas para TDD
	@Test
	void enviarOrdemCompraTest() {
		Carteira carteira = new Carteira();
		
		try {
			OrdemBuilder builder = new OrdemBuilder();
			Ordem ordem1 = builder.comTicket("ITUB4").comValor(new BigDecimal(50.00)).comQuantidade(1000).comTipo(TipoOrdem.COMPRA).build();
			Ordem ordem2 = builder.comTicket("ITUB4").comValor(new BigDecimal(60.00)).comQuantidade(1000).comTipo(TipoOrdem.COMPRA).build();
			Ordem ordem3 = builder.comTicket("ITUB4").comValor(new BigDecimal(50.00)).comQuantidade(400).comTipo(TipoOrdem.VENDA).build();

			carteira.enviarOrdem(ordem1);
			carteira.enviarOrdem(ordem2);
			carteira.enviarOrdem(ordem3);
			System.out.println(carteira.getAtivos().get(0).toString());
			System.out.println(carteira.getImpostoRetidoNaFonte());
			Assertions.assertAll(() -> {
				Assertions.assertEquals(new BigDecimal(55.00).setScale(2), carteira.getAtivos().get(0).getPrecoMedio());
				Assertions.assertTrue(carteira.getImpostoRetidoNaFonte().compareTo(BigDecimal.ZERO) == 1);
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
