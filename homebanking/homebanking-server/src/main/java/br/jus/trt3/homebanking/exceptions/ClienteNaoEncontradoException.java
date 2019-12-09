package br.jus.trt3.homebanking.exceptions;

@SuppressWarnings("serial")
public class ClienteNaoEncontradoException extends HomeBankingException {

	private String nome;
	
	public ClienteNaoEncontradoException(String nomeOperacao, String nome) {
		super(nomeOperacao);
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
