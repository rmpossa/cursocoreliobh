package laboratorioDia02;

public class ContaCorrente extends Conta{
	private double limiteCredito;

	public double getLimiteCredito() {
		return limiteCredito;
	}

	public void setLimiteCredito(double limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	public ContaCorrente(String id, double saldoInicial, double limiteCredito) {
		super(id, saldoInicial);
		this.limiteCredito = limiteCredito;
	}

	@Override
	public void accept(AplicaTaxaMensalContaVisitor visitor) {
		visitor.visit(this);
		
	}
	
	
	
	
}
