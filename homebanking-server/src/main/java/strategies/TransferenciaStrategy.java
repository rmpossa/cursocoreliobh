package strategies;

import br.jus.trt3.models.Conta;

public interface TransferenciaStrategy {

	public void transfere(Conta contaOrigem, Conta contaDestino, double valor) throws Exception;
}
