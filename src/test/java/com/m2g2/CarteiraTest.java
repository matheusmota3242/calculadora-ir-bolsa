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
}
