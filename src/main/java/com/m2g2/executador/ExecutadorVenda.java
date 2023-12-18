package com.m2g2.executador;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import com.m2g2.factory.IExecutador;
import com.m2g2.model.Ativo;
import com.m2g2.model.Carteira;
import com.m2g2.model.Ordem;

public class ExecutadorVenda implements IExecutador {

	@Override
	public void executarOrdem(Ordem ordem, Optional<Ativo> optional, Carteira carteira) throws Exception {
		
		if (optional.isPresent()) {
			Ativo ativo = optional.get();
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
		
		BigDecimal valorDaVenda = ordem.getValor().multiply(new BigDecimal(ordem.getQuantidade()));
		if (valorDaVenda.compareTo(BigDecimal.valueOf(20000.00)) >= 0) {
			carteira.setImpostoRetidoNaFonte(valorDaVenda.multiply(BigDecimal.valueOf(0.00005)).setScale(2, RoundingMode.HALF_UP));
		}
	}	

}
