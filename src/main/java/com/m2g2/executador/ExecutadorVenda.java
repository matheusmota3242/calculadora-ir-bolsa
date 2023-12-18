package com.m2g2.executador;

import java.util.List;
import java.util.Optional;

import com.m2g2.factory.IExecutador;
import com.m2g2.model.Ativo;
import com.m2g2.model.Ordem;

public class ExecutadorVenda implements IExecutador {

	@Override
	public void executarOrdem(Ordem ordem, Optional<Ativo> optional, List<Ativo> ativos) throws Exception {
		if (optional.isPresent()) {
			Ativo ativo = optional.get();
			if (ativo.getQuantidade() > ordem.getQuantidade()) {
				ativo.setQuantidade(ativo.getQuantidade() - ordem.getQuantidade());
				ativos.replaceAll(a -> a.getTicket().equals(ativo.getTicket()) ? ativo : a);
			} else if (ativo.getQuantidade().equals(ordem.getQuantidade())) {
				ativos.removeIf(a -> a.getTicket().equals(ordem.getTicket()));
			} else {
				throw new Exception("Quantidade de ativos na carteira menor que a da ordem");
			}
		}		
	}


}
