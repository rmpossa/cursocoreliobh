package br.jus.trt3.homebanking.exceptions;

public class SaldoInsuficienteException extends HomeBankingException {

	private String codigoConta;
	
	public SaldoInsuficienteException(String nomeOperacao, String codigoConta) {
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
