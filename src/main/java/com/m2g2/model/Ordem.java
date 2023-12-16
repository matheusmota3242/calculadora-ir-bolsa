package com.m2g2.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.m2g2.enums.TipoOrdem;

public class Ordem {

	private final String ticket;
	private final BigDecimal valor;
	private final Integer quantidade;
	private final TipoOrdem tipo;
	private final LocalDate data;
	
	
	public Ordem(String ticket, BigDecimal valor, Integer quantidade, TipoOrdem tipo) {
		super();
		this.ticket = ticket;
		this.valor = valor;
		this.quantidade = quantidade;
		this.tipo = tipo;
		this.data = LocalDate.now();
	}
	
	public String getTicket() {
		return ticket;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public TipoOrdem getTipo() {
		return tipo;
	}
	
	public LocalDate getData() {
		return data;
	}
	
	
}
