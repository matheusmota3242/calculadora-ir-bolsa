package com.m2g2.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.m2g2.constantes.ConstantesGlobais;
import com.m2g2.enums.TipoOrdem;
import com.m2g2.factory.AbstractFactory;
import com.m2g2.factory.IExecutador;

public class Carteira {

	private List<Ordem> ordens = new ArrayList<>();
	
	private List<Ativo> ativos = new ArrayList<>();
	
	private BigDecimal impostoRetidoNaFonte = BigDecimal.ZERO;
	
	private BigDecimal imposto = BigDecimal.ZERO;
	
	private BigDecimal resultado = BigDecimal.ZERO;
	
	public void enviarOrdem(Ordem ordem) throws Exception {
		Optional<Ativo> optional = ativos.stream().filter(a -> a.getTicket().equals(ordem.getTicket())).findFirst();
		IExecutador executador = AbstractFactory.criarExecutador(ordem.getTipo());
		executador.executarOrdem(ordem, optional, this);
		ordens.add(ordem);
	}
	
	public BigDecimal consolidarImpostos() {
//		BigDecimal valorTotalDasVendas = ordens.stream().filter(ordem -> TipoOrdem.VENDA.equals(ordem.getTipo())).map(Ordem::getValorTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
////		ordens.stream().filter(ordem -> TipoOrdem.VENDA.equals(ordem.getTipo())).forEach(ordem -> {
////			 
////			
////			if (ordem.getValorTotal().compareTo(BigDecimal.valueOf(20000.00)) >= 0) {
////				imposto = imposto.add(ordem.getValorTotal()
////						.multiply(BigDecimal.valueOf(ConstantesGlobais.ALIQUOTA_IR)
////								.subtract(BigDecimal.valueOf(ConstantesGlobais.ALIQUOTA_IR_FONTE))));
////			} else if (true) {
////				
////			}
////		});
		if (resultado.compareTo(BigDecimal.ZERO) > 0) {
			return imposto.setScale(2, RoundingMode.HALF_UP);
		} else {
			return BigDecimal.ZERO.setScale(2);
		}
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
