package br.com.zup.quadrinho.api.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "precos_quadrinhos")
public class PrecoQuadrinho {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String tipo;
	private BigDecimal preco;

	@ManyToOne(fetch = FetchType.LAZY)
	private Quadrinho quadrinho;

	public PrecoQuadrinho() {
	}

	public PrecoQuadrinho(String tipo, BigDecimal preco) {
		this.tipo = tipo;
		this.preco = preco;
	}

	public Long getId() {
		return id;
	}

	public String getTipo() {
		return tipo;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public Quadrinho getQuadrinho() {
		return quadrinho;
	}

	public void setQuadrinho(Quadrinho quadrinho) {
		this.quadrinho = quadrinho;
	}
	
	public void aplicaDesconto(BigDecimal percentual) {
		BigDecimal valorDesconto = this.preco.multiply(percentual).setScale(2, RoundingMode.HALF_UP);
		
		this.preco = this.preco.subtract(valorDesconto);
	}

}
