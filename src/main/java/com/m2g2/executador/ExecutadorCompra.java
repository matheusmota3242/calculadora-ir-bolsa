package com.m2g2.executador;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

import com.m2g2.factory.IExecutador;
import com.m2g2.model.Ativo;
import com.m2g2.model.Carteira;
import com.m2g2.model.Ordem;

public class ExecutadorCompra implements IExecutador {

	@Override
	public void executarOrdem(Ordem ordem, Optional<Ativo> optional, Carteira carteira) {
		if (optional.isPresent()) {
			Ativo ativo = optional.get();			
			Integer quantidade = ativo.getQuantidade() + ordem.getQuantidade();
			BigDecimal novoPrecoMedio = ativo.getPrecoMedio()
					.multiply(new BigDecimal(ativo.getQuantidade()))
					.add(ordem.getValor().multiply(new BigDecimal(ordem.getQuantidade())))
					.divide(new BigDecimal(quantidade))
					.setScale(2, RoundingMode.HALF_UP);
			ativo.setQuantidade(quantidade);
			ativo.setPrecoMedio(novoPrecoMedio);
		} else {
			carteira.getAtivos().add(new Ativo(ordem.getTicket(), ordem.getValor(), ordem.getQuantidade()));
		}		
	}

}
