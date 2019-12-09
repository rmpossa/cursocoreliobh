package br.jus.trt3.homebanking.exceptions;

public class HomeBankingException extends RuntimeException {

	private String nomeOperacao;

	public HomeBankingException(String nomeOperacao) {
		this.nomeOperacao = nomeOperacao;
	}
	
	public String getNomeOperacao() {
		return nomeOperacao;
	}

	public void setNomeOperacao(String nomeOperacao) {
		this.nomeOperacao = nomeOperacao;
	}
}
