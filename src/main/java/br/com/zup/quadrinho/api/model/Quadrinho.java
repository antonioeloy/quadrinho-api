package br.com.zup.quadrinho.api.model;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "quadrinhos")
public class Quadrinho {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private Integer comicId;

	@NotBlank
	private String titulo;

	@Embedded
	@NotNull
	@Valid
	private IsbnData isbnData;

	private String descricao;

	@OneToMany(mappedBy = "quadrinho", cascade = CascadeType.ALL)
	@NotEmpty
	private List<PrecoQuadrinho> precos = new ArrayList<>();

	@ManyToMany
	@JoinTable(name = "autores_quadrinhos", joinColumns = @JoinColumn(name = "quadrinho_id"), inverseJoinColumns = @JoinColumn(name = "autor_id"))
	@NotEmpty
	private List<Autor> autores = new ArrayList<>();

	@ManyToMany(mappedBy = "quadrinhos")
	private List<Usuario> usuarios = new ArrayList<>();

	public Quadrinho() {
	}

	public Quadrinho(Integer comicId, String titulo, IsbnData isbnData, String descricao) {
		this.comicId = comicId;
		this.titulo = titulo;
		this.isbnData = isbnData;
		this.descricao = descricao;
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

	public IsbnData getIsbnData() {
		return isbnData;
	}

	public String getDescricao() {
		return descricao;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public List<PrecoQuadrinho> getPrecos() {

		if (this.descontoPrecoEstaAtivo()) {
			BigDecimal percentualDesconto = new BigDecimal("0.10");
			precos.forEach(preco -> preco.aplicaDesconto(percentualDesconto));
		}

		return precos;
	}

	public void adicionaPreco(PrecoQuadrinho preco) {
		this.precos.add(preco);
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void adicionaAutor(Autor autor) {
		this.autores.add(autor);
	}

	public Boolean descontoPrecoEstaAtivo() {
		DayOfWeek diaSemanaDescontoPreco = this.getIsbnData().getDiaSemanaDescontoPreco();
		DayOfWeek diaSemanaHoje = LocalDate.now().getDayOfWeek();

		return diaSemanaDescontoPreco.getValue() == diaSemanaHoje.getValue();
	}

}
