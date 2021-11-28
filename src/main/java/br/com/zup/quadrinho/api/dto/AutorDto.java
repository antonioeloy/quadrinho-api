package br.com.zup.quadrinho.api.dto;

public class AutorDto {

	private String uri;
	private String nome;

	public AutorDto(String uri, String nome) {
		this.uri = uri;
		this.nome = nome;
	}

	public String getUri() {
		return uri;
	}

	public String getNome() {
		return nome;
	}

}
