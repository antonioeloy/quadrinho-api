package br.com.zup.quadrinho.api.dto;

import java.util.ArrayList;
import java.util.List;

public class QuadrinhoDto {

	private Long id;
	private Integer comicId;
	private String titulo;
	private String isbn;
	private String descricao;
	private Boolean descontoAtivo;
	private List<PrecoQuadrinhoDto> precos = new ArrayList<>();
	private List<AutorDto> autores = new ArrayList<>();

	public QuadrinhoDto(Long id, Integer comicId, String titulo, String isbn, String descricao, Boolean descontoAtivo) {
		this.id = id;
		this.comicId = comicId;
		this.titulo = titulo;
		this.isbn = isbn;
		this.descricao = descricao;
		this.descontoAtivo = descontoAtivo;
	}

	public Long getId() {
		return id;
	}

	public Integer getComicId() {
		return comicId;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getDescricao() {
		return descricao;
	}

	public Boolean getDescontoAtivo() {
		return descontoAtivo;
	}

	public List<PrecoQuadrinhoDto> getPrecos() {
		return precos;
	}

	public void adicionaPreco(PrecoQuadrinhoDto preco) {
		this.precos.add(preco);
	}

	public List<AutorDto> getAutores() {
		return autores;
	}

	public void adicionaAutor(AutorDto autor) {
		this.autores.add(autor);
	}

}
