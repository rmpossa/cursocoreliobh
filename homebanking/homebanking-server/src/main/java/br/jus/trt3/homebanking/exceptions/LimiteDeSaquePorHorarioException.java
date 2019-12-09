package br.jus.trt3.homebanking.exceptions;

public class LimiteDeSaquePorHorarioException extends HomeBankingException {

	private String codigoConta;
	
	public LimiteDeSaquePorHorarioException(String nomeOperacao, String codigoConta) {
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
