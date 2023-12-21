package com.m2g2;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.m2g2.enums.TipoOrdem;
import com.m2g2.model.Carteira;
import com.m2g2.model.Ordem;
import com.m2g2.model.builder.OrdemBuilder;

class CarteiraTest {

	@ParameterizedTest
	@MethodSource("proverArgumentos")
	void enviarOrdemTest(List<Ordem> ordens, BigDecimal impostoEsperado) {
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
	
	private static Stream<Arguments> proverArgumentos() {
		OrdemBuilder builder = new OrdemBuilder();
		return Stream.of(
				Arguments.of(Arrays.asList(
						builder.comTicket("ITUB4").comValor(new BigDecimal(20.00).setScale(2, RoundingMode.HALF_UP)).comQuantidade(1000).comTipo(TipoOrdem.COMPRA).build(),
						builder.comTicket("ITUB4").comValor(new BigDecimal(19.99).setScale(2, RoundingMode.HALF_UP)).comQuantidade(1000).comTipo(TipoOrdem.VENDA).build()),
						BigDecimal.ZERO.setScale(2)),
				
				Arguments.of(Arrays.asList(
						builder.comTicket("ITUB4").comValor(new BigDecimal(20.00).setScale(2, RoundingMode.HALF_UP)).comQuantidade(1000).comTipo(TipoOrdem.COMPRA).build(),
						builder.comTicket("ITUB4").comValor(new BigDecimal(20.00).setScale(2, RoundingMode.HALF_UP)).comQuantidade(1000).comTipo(TipoOrdem.VENDA).build()),
						BigDecimal.ZERO.setScale(2)),
		
				Arguments.of(Arrays.asList(
						builder.comTicket("ITUB4").comValor(new BigDecimal(19.99).setScale(2, RoundingMode.HALF_UP)).comQuantidade(1000).comTipo(TipoOrdem.COMPRA).build(),
						builder.comTicket("ITUB4").comValor(new BigDecimal(20.01).setScale(2, RoundingMode.HALF_UP)).comQuantidade(1000).comTipo(TipoOrdem.VENDA).build()),
						BigDecimal.valueOf(3).setScale(2)),
						
				Arguments.of(Arrays.asList(
						builder.comTicket("ITUB4").comValor(new BigDecimal(20.00).setScale(2, RoundingMode.HALF_UP)).comQuantidade(2000).comTipo(TipoOrdem.COMPRA).build(),
						builder.comTicket("ITUB4").comValor(new BigDecimal(20.01).setScale(2, RoundingMode.HALF_UP)).comQuantidade(1000).comTipo(TipoOrdem.VENDA).build(),
						builder.comTicket("ITUB4").comValor(new BigDecimal(19.99).setScale(2, RoundingMode.HALF_UP)).comQuantidade(1000).comTipo(TipoOrdem.VENDA).build()),
						BigDecimal.ZERO.setScale(2)),
				
				Arguments.of(Arrays.asList(
                        builder.comTicket("ITUB4").comValor(new BigDecimal(30.00).setScale(2, RoundingMode.HALF_UP)).comQuantidade(1000).comTipo(TipoOrdem.COMPRA).build(),
                        builder.comTicket("ITUB4").comValor(new BigDecimal(20.00).setScale(2, RoundingMode.HALF_UP)).comQuantidade(500).comTipo(TipoOrdem.VENDA).build()),
                        BigDecimal.ZERO.setScale(2)),

                
                Arguments.of(Arrays.asList(
                        builder.comTicket("ITUB4").comValor(new BigDecimal(150.00).setScale(2, RoundingMode.HALF_UP)).comQuantidade(1000).comTipo(TipoOrdem.COMPRA).build(),
                        builder.comTicket("ITUB4").comValor(new BigDecimal(150.00).setScale(2, RoundingMode.HALF_UP)).comQuantidade(150).comTipo(TipoOrdem.VENDA).build()),
                        BigDecimal.ZERO.setScale(2))
				);
	}
	
	@ParameterizedTest
	@MethodSource("proverArgumentos2")
	void testarExcecoes(List<Ordem> ordens, String mensagemEsperada) {
		Carteira carteira = new Carteira();
					
		Exception exception = assertThrows(Exception.class, () -> {
			for (Ordem ordem : ordens) {
				carteira.enviarOrdem(ordem);
			}
		});

		String expectedMessage = mensagemEsperada;
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	private static Stream<Arguments> proverArgumentos2() {
		OrdemBuilder builder = new OrdemBuilder();
		return Stream.of(
				Arguments.of(Arrays.asList(
						builder.comTicket("ITUB4").comValor(new BigDecimal(20.00).setScale(2, RoundingMode.HALF_UP)).comQuantidade(1000).comTipo(TipoOrdem.COMPRA).build(),
						builder.comTicket("ITUB4").comValor(new BigDecimal(19.99).setScale(2, RoundingMode.HALF_UP)).comQuantidade(1500).comTipo(TipoOrdem.VENDA).build()),
						"Quantidade de ativos na carteira menor que a da ordem."),
				
				Arguments.of(Arrays.asList(
						builder.comTicket("ITUB4").comValor(new BigDecimal(20.00).setScale(2, RoundingMode.HALF_UP)).comQuantidade(1000).comTipo(TipoOrdem.COMPRA).build(),
						builder.comTicket("ITUB5").comValor(new BigDecimal(19.99).setScale(2, RoundingMode.HALF_UP)).comQuantidade(1000).comTipo(TipoOrdem.VENDA).build()),
						"Nao ha registro do ativo 'ITUB5' na carteira.")
				);
	}
}
