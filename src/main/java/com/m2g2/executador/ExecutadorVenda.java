package com.m2g2.executador;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import com.m2g2.constantes.ConstantesGlobais;
import com.m2g2.factory.IExecutador;
import com.m2g2.model.Ativo;
import com.m2g2.model.Carteira;
import com.m2g2.model.Ordem;

public class ExecutadorVenda implements IExecutador {
	
	@Override
	public void executarOrdem(Ordem ordem, Optional<Ativo> optional, Carteira carteira) throws Exception {
		BigDecimal resultadoOperacao = BigDecimal.ZERO;
		if (optional.isPresent()) {
			Ativo ativo = optional.get();
			if (ordem.getValor().compareTo(ativo.getPrecoMedio()) > 0) {
				resultadoOperacao = ordem.getValorTotal().subtract(ativo.getPrecoMedio().multiply(BigDecimal.valueOf(ordem.getQuantidade())));
			}
			
			if (ativo.getQuantidade() > ordem.getQuantidade()) {
				ativo.setQuantidade(ativo.getQuantidade() - ordem.getQuantidade());
				carteira.getAtivos().replaceAll(a -> a.getTicket().equals(ativo.getTicket()) ? ativo : a);
			} else if (ativo.getQuantidade().equals(ordem.getQuantidade())) {
				carteira.getAtivos().removeIf(a -> a.getTicket().equals(ordem.getTicket()));
			} else {
				throw new Exception("Quantidade de ativos na carteira menor que a da ordem");
			}
		} else {
			throw new Exception(String.format("Não há registro do ativo '%s' na carteira.", ordem.getTicket()));
		}
		
		BigDecimal valorDaVenda = ordem.getValorTotal();
		if (valorDaVenda.compareTo(BigDecimal.valueOf(20000.00)) >= 0) {
			carteira.setImpostoRetidoNaFonte(valorDaVenda.multiply(BigDecimal.valueOf(ConstantesGlobais.ALIQUOTA_IR_FONTE)).setScale(2, RoundingMode.HALF_UP));
			if (resultadoOperacao.compareTo(BigDecimal.ZERO) > 0) {
				carteira.setImposto(carteira.getImposto().add(resultadoOperacao.multiply(BigDecimal.valueOf(ConstantesGlobais.ALIQUOTA_IR).subtract(BigDecimal.valueOf(ConstantesGlobais.ALIQUOTA_IR_FONTE)))));
			}
		} else if (resultadoOperacao.compareTo(BigDecimal.ZERO) > 0) {
			carteira.setImposto(carteira.getImposto().add(resultadoOperacao.multiply(BigDecimal.valueOf(ConstantesGlobais.ALIQUOTA_IR))));
		}
		
		carteira.setResultado(carteira.getResultado().add(resultadoOperacao)); 
		
	}	

}
