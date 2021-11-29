package br.com.zup.quadrinho.api.exception;

public class DadoEmUsoException extends PreenchimentoDadosException {

	private static final long serialVersionUID = 1L;

	public DadoEmUsoException(String campo) {
		super(campo, "Deve ser único");
	}

}
