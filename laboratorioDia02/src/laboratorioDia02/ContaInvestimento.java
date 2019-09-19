package laboratorioDia02;

public class ContaInvestimento extends Conta{
	private TipoAplicacao tipoAplicacao;
	
	public ContaInvestimento(String id, double saldoInicial, TipoAplicacao tipoAplicacao) {
		super(id, saldoInicial);
		this.tipoAplicacao = tipoAplicacao;
	}
	
	
	public double recuperaRentabilidade() {
		return tipoAplicacao.recuperaRentabilidade();
	}
	
	@Override
	public void accept(AplicaTaxaMensalContaVisitor visitor) {
		visitor.visit(this);
		
	}
	
	
}
