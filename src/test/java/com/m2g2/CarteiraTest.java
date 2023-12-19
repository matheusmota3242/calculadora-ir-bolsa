package com.m2g2;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.m2g2.enums.TipoOrdem;
import com.m2g2.model.Carteira;
import com.m2g2.model.Ordem;
import com.m2g2.model.builder.OrdemBuilder;

class CarteiraTest {

	// Método temporário apenas para TDD
	@ParameterizedTest
	@MethodSource("proverArgumentos")
	void enviarOrdemCompraTest(List<Ordem> ordens, BigDecimal impostoEsperado) {
		Carteira carteira = new Carteira();
		
		try {
			for (Ordem ordem : ordens) {
				carteira.enviarOrdem(ordem);
			}
			System.out.println(carteira.toString());
			Assertions.assertEquals(impostoEsperado, carteira.consolidarImpostos());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static Stream<List<Ordem>> proverOrdens() {
		OrdemBuilder builder = new OrdemBuilder();
		return Stream.of(
				Arrays.asList(
						builder.comTicket("ITUB4").comValor(new BigDecimal(60.00)).comQuantidade(1000).comTipo(TipoOrdem.COMPRA).build(),
						builder.comTicket("ITUB4").comValor(new BigDecimal(50.00)).comQuantidade(400).comTipo(TipoOrdem.VENDA).build())
				);
	}
	
	private static Stream<Arguments> proverArgumentos() {
		OrdemBuilder builder = new OrdemBuilder();
		return Stream.of(
				Arguments.of(Arrays.asList(
						builder.comTicket("ITUB4").comValor(new BigDecimal(20.00)).comQuantidade(1000).comTipo(TipoOrdem.COMPRA).build(),
						builder.comTicket("ITUB4").comValor(new BigDecimal(19.99)).comQuantidade(1000).comTipo(TipoOrdem.VENDA).build()),
						BigDecimal.ZERO.setScale(2)),
				
				Arguments.of(Arrays.asList(
						builder.comTicket("ITUB4").comValor(new BigDecimal(20.00)).comQuantidade(1000).comTipo(TipoOrdem.COMPRA).build(),
						builder.comTicket("ITUB4").comValor(new BigDecimal(20.00)).comQuantidade(1000).comTipo(TipoOrdem.VENDA).build()),
						BigDecimal.ZERO.setScale(2)),
				
				Arguments.of(Arrays.asList(
						builder.comTicket("ITUB4").comValor(new BigDecimal(50.00)).comQuantidade(1000).comTipo(TipoOrdem.COMPRA).build(),
						builder.comTicket("ITUB4").comValor(new BigDecimal(50.00)).comQuantidade(400).comTipo(TipoOrdem.VENDA).build()),
						BigDecimal.ZERO.setScale(2)),
				Arguments.of(Arrays.asList(
						builder.comTicket("ITUB4").comValor(new BigDecimal(19.99999)).comQuantidade(1000).comTipo(TipoOrdem.COMPRA).build(),
						builder.comTicket("ITUB4").comValor(new BigDecimal(20.00001)).comQuantidade(1000).comTipo(TipoOrdem.VENDA).build()),
						BigDecimal.ZERO.setScale(2))		
				);
	}
}
