package br.com.zup.quadrinho.api.dto;

public class StatusCode400Dto {

	private String campo;
	private String mensagem;

	public StatusCode400Dto() {
	}

	public StatusCode400Dto(String campo, String mensagem) {
		this.campo = campo;
		this.mensagem = mensagem;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

}
