package br.com.zup.quadrinho.api.exception;

public class UsuarioJaPossuiQuadrinhoException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String campo;
	
	public UsuarioJaPossuiQuadrinhoException(String campo, String mensagem) {
		super(mensagem);
		this.campo = campo;
	}
	
	public String getCampo() {
		return campo;
	}

}
