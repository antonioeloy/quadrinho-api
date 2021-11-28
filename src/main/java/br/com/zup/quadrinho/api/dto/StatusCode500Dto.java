package br.com.zup.quadrinho.api.dto;

import java.time.LocalDateTime;

public class StatusCode500Dto {

	private LocalDateTime timestamp;
	private String erro;
	private String mensagem;
	private String path;

	public StatusCode500Dto() {
	}

	public StatusCode500Dto(LocalDateTime timestamp, String erro, String mensagem, String path) {
		this.timestamp = timestamp;
		this.erro = erro;
		this.mensagem = mensagem;
		this.path = path;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
