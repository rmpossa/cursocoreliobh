package laboratorioDia02;

public interface TransferenciaStrategy {

	public void transfere(Conta contaOrigem, Conta contaDestino, double valor) throws Exception;
}
