package br.com.zup.quadrinho.api.dto;

import java.math.BigDecimal;

public class PrecoQuadrinhoDto {

	private String tipo;
	private BigDecimal preco;

	public PrecoQuadrinhoDto(String tipo, BigDecimal preco) {
		this.tipo = tipo;
		this.preco = preco;
	}

	public String getTipo() {
		return tipo;
	}

	public BigDecimal getPreco() {
		return preco;
	}

}
