package strategies;

import br.jus.trt3.models.Conta;

public class ClienteDiferenteStrategy implements TransferenciaStrategy {
	
	private double taxa;

	public ClienteDiferenteStrategy(double valorTaxa) {
		this.taxa = valorTaxa;
	}

	@Override
	public void transfere(Conta contaOrigem, Conta contaDestino, double valor) throws Exception {
		contaOrigem.debita(valor*(1+taxa/100));
		contaDestino.credita(valor);
	}

}
