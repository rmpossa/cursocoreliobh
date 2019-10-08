package strategies;

import br.jus.trt3.models.Conta;

public class MesmoClienteStrategy implements TransferenciaStrategy {

	public MesmoClienteStrategy() {}
	
	@Override
	public void transfere(Conta contaOrigem, Conta contaDestino, double valor) throws Exception {
		contaOrigem.debita(valor);
		contaDestino.credita(valor);
	}

}
