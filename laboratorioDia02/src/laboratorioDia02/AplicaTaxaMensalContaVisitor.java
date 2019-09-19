package laboratorioDia02;

public interface AplicaTaxaMensalContaVisitor {
	public void visit(ContaCorrente contaCorrente);
	public void visit(ContaInvestimento contaInvestimento);
}
