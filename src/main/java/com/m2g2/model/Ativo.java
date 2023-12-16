package com.m2g2.model;

import java.math.BigDecimal;

public class Ativo {

	private String ticket;
	private BigDecimal precoMedio;
	private Integer quantidade;
	
	public Ativo(String ticket, BigDecimal precoMedio, Integer quantidade) {
		super();
		this.ticket = ticket;
		this.precoMedio = precoMedio;
		this.quantidade = quantidade;
	}
	
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public BigDecimal getPrecoMedio() {
		return precoMedio;
	}
	public void setPrecoMedio(BigDecimal precoMedio) {
		this.precoMedio = precoMedio;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	
}
