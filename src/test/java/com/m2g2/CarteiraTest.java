package com.m2g2;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.m2g2.enums.TipoOrdem;
import com.m2g2.model.Carteira;
import com.m2g2.model.Ordem;

class CarteiraTest {

	// Método temporário apenas para TDD
	@Test
	void enviarOrdemCompraTest() {
		Carteira carteira = new Carteira();
		
		try {
			carteira.enviarOrdem(new Ordem("ITUB4", new BigDecimal(50.00), 100, TipoOrdem.COMPRA));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO testar impacto da ordem na carteira
	}
}
