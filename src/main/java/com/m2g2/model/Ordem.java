package com.m2g2.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.m2g2.enums.TipoOrdem;

public class Ordem {

	private String ticket;
	private BigDecimal valor;
	private Integer quantidade;
	private TipoOrdem tipo;
	private LocalDate data;
	
	
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

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public TipoOrdem getTipo() {
		return tipo;
	}

	public void setTipo(TipoOrdem tipo) {
		this.tipo = tipo;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
}
