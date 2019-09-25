package laboratorioDia02;

public class Poupanca implements TipoAplicacao {

	private double taxa;
	
	public Poupanca(double taxa) {
		this.taxa = taxa;
	}
	
	public double getTaxa() {
		return taxa;
	}

	public void setTaxa(double taxa) {
		this.taxa = taxa;
	}

	@Override
	public double recuperaRentabilidade() {
		// TODO Auto-generated method stub
		return 1+taxa;
	}

}
