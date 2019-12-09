package br.jus.trt3.homebanking.exceptions;

@SuppressWarnings("serial")
public class ContaNaoEncontradaException extends HomeBankingException {

	private String codigoConta;
	
	public ContaNaoEncontradaException(String nomeOperacao, String codigoConta) {
		super(nomeOperacao);
		this.codigoConta = codigoConta;
	}

	public String getCodigoConta() {
		return codigoConta;
	}

	public void setCodigoConta(String codigoConta) {
		this.codigoConta = codigoConta;
	}
	
}
