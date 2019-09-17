package laboratorioDia02;

public class CDB implements TipoAplicacao {

	private double taxa;
	private double percentual;
	
	public CDB(double taxa, double percentual) {
		this.taxa = taxa;
		this.percentual = percentual;
	}
	
	public double getTaxa() {
		return taxa;
	}

	public void setTaxa(double taxa) {
		this.taxa = taxa;
	}

	public double getPercentual() {
		return percentual;
	}

	public void setPercentual(double percentual) {
		this.percentual = percentual;
	}

	@Override
	public double recuperaRentabilidade() {
		// TODO Auto-generated method stub
		return 0;
	}

}
