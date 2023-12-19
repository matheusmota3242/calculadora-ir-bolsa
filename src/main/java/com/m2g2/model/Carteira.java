package com.m2g2.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.m2g2.factory.AbstractFactory;
import com.m2g2.factory.IExecutador;

public class Carteira {

	private List<Ordem> ordens = new ArrayList<>();
	
	private List<Ativo> ativos = new ArrayList<>();
	
	private BigDecimal impostoRetidoNaFonte = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
	
	private BigDecimal imposto = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
	
	private BigDecimal resultado = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
	
	public void enviarOrdem(Ordem ordem) throws Exception {
		Optional<Ativo> optional = ativos.stream().filter(a -> a.getTicket().equals(ordem.getTicket())).findFirst();
		IExecutador executador = AbstractFactory.criarExecutador(ordem.getTipo());
		executador.executarOrdem(ordem, optional, this);
		ordens.add(ordem);
	}
	
	public BigDecimal consolidarImpostos() {

		if (resultado.compareTo(BigDecimal.ZERO) <= 0) {
			
			return BigDecimal.ZERO.setScale(2);
		}
		return imposto;
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

	public BigDecimal getImpostoRetidoNaFonte() {
		return impostoRetidoNaFonte;
	}

	public void setImpostoRetidoNaFonte(BigDecimal impostoRetidoNaFonte) {
		this.impostoRetidoNaFonte = impostoRetidoNaFonte;
	}

	public BigDecimal getImposto() {
		return imposto;
	}

	public void setImposto(BigDecimal imposto) {
		this.imposto = imposto;
	}

	public BigDecimal getResultado() {
		return resultado;
	}

	public void setResultado(BigDecimal resultado) {
		this.resultado = resultado;
	}
	
	@Override
	public String toString() {
		return "Carteira [ordens=" + ordens + ", ativos=" + ativos + ", impostoRetidoNaFonte=" + impostoRetidoNaFonte
				+ ", imposto=" + imposto + ", resultado=" + resultado + "]";
	}
	
}
