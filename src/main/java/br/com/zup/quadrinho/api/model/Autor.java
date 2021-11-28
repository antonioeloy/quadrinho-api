package br.com.zup.quadrinho.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "autores")
public class Autor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String uri;
	private String nome;

	@ManyToMany(mappedBy = "autores")
	private List<Quadrinho> quadrinhos = new ArrayList<>();

	public Autor() {
	}

	public Autor(String uri, String nome) {
		this.uri = uri;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public String getUri() {
		return uri;
	}

	public String getNome() {
		return nome;
	}

	public List<Quadrinho> getQuadrinhos() {
		return quadrinhos;
	}

}
