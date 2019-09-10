package laboratorioDia02;

public class ContaInvestimento extends Conta{
	private TipoAplicacao tipoAplicacao;
	
	public ContaInvestimento(TipoAplicacao tipoAplicacao, Cliente cliente) {
		super(cliente);
		this.tipoAplicacao = tipoAplicacao;
	}
	
	
	public double recuperaRentabilidade() {
		return tipoAplicacao.recuperaRentabilidade();
	}
	
	
}
