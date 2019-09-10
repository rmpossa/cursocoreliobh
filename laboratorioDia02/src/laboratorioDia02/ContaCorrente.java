package laboratorioDia02;

public class ContaCorrente extends Conta{
	private double limiteCredito;

	public double getLimiteCredito() {
		return limiteCredito;
	}

	public void setLimiteCredito(double limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	public ContaCorrente(Cliente cliente) {
		super(cliente);
	}
	
	
}
