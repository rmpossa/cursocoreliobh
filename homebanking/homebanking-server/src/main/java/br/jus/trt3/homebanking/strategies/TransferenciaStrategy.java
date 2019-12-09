package br.jus.trt3.homebanking.strategies;

import br.jus.trt3.homebanking.models.Conta;

public interface TransferenciaStrategy {

	public void transfere(Conta contaOrigem, Conta contaDestino, double valor) throws Exception;
}
