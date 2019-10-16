package br.jus.trt3.homebanking.exceptions;

@SuppressWarnings("serial")
public class NaoEContaInvestimentoException extends HomeBankingException {

	private String codigoConta;
	
	public NaoEContaInvestimentoException(String nomeOperacao, String codigoConta) {
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
