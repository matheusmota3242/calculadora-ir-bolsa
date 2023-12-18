package com.m2g2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.m2g2.factory.AbstractFactory;
import com.m2g2.factory.IExecutador;

public class Carteira {

	private List<Ordem> ordens = new ArrayList<>();
	
	private List<Ativo> ativos = new ArrayList<>();
	
	public void enviarOrdem(Ordem ordem) throws Exception {
		Optional<Ativo> optional = ativos.stream().filter(a -> a.getTicket().equals(ordem.getTicket())).findFirst();
		IExecutador executador = AbstractFactory.criarExecutador(ordem.getTipo());
		executador.executarOrdem(ordem, optional, ativos);
		ordens.add(ordem);
	}

	public List<Ordem> getOrdens() {
		return ordens;
	}

	public void setOrdens(List<Ordem> ordens) {
		this.ordens = ordens;
	}

	public List<Ativo> getAtivos() {
		return ativos;
	}

	public void setAtivos(List<Ativo> ativos) {
		this.ativos = ativos;
	}
	
	

}
