package com.m2g2.factory;

import com.m2g2.enums.TipoOrdem;
import com.m2g2.executador.ExecutadorCompra;
import com.m2g2.executador.ExecutadorVenda;

public class AbstractFactory {
	
	private AbstractFactory() {}

	public static IExecutador criarExecutador(TipoOrdem tipoOrdem) throws Exception {
		if (TipoOrdem.COMPRA.equals(tipoOrdem)) {
			return new ExecutadorCompra();
		} else if (TipoOrdem.VENDA.equals(tipoOrdem)) {
			return new ExecutadorVenda();
		}
		throw new Exception("Erro no na obtenção do tipo da ordem.");
	}
}
