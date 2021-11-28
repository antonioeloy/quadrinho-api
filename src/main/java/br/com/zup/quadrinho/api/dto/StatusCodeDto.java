package br.com.zup.quadrinho.api.dto;

public class StatusCodeDto {

	private String mensagem;

	public StatusCodeDto(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

}
