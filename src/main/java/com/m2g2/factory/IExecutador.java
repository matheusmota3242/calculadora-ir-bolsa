package com.m2g2.factory;

import java.util.Optional;

import com.m2g2.model.Ativo;
import com.m2g2.model.Carteira;
import com.m2g2.model.Ordem;

public interface IExecutador {
	
	void executarOrdem(Ordem ordem, Optional<Ativo> optional, Carteira carteira) throws Exception;
	
}
