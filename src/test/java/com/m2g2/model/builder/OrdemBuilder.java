package com.m2g2.model.builder;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.m2g2.enums.TipoOrdem;
import com.m2g2.model.Ordem;

public class OrdemBuilder {

	private String ticket;
	private BigDecimal valor;
	private Integer quantidade;
	private TipoOrdem tipo;
	private LocalDate data;
	
	public OrdemBuilder comTicket(String ticket) {
		this.ticket = ticket;
		return this;
	}
	
	public OrdemBuilder comValor(BigDecimal valor) {
		this.valor = valor;
		return this;
	}
	
	public OrdemBuilder comQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
		return this;
	}
	
	public OrdemBuilder comTipo(TipoOrdem tipo) {
		this.tipo = tipo;
		return this;
	}
	
	public OrdemBuilder comData(LocalDate data) {
		this.data = data;
		return this;
	}
	
	public Ordem build() {
		Ordem ordem = new Ordem(ticket, valor, quantidade, tipo);
		ordem.setData(data);
		return ordem;
	}
}
