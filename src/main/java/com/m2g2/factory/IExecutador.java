package com.m2g2.factory;

import java.util.List;
import java.util.Optional;

import com.m2g2.model.Ativo;
import com.m2g2.model.Ordem;

public interface IExecutador {
	
	void executarOrdem(Ordem ordem, Optional<Ativo> optional, List<Ativo> ativos) throws Exception;
	
}
