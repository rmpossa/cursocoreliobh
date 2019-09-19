package laboratorioDia02;

public class AplicaTaxaMensalClienteVisitorImpl implements AplicaTaxaMensalClienteVisitor{
	@Override
	public void visit(Cliente cliente) {
		AplicaTaxaMensalContaVisitor aplicaTaxaMensalContaVisitor = new AplicaTaxaMensalContaVisitorImpl();
		
		for(Conta conta:cliente.getContas()) {
			conta.accept(aplicaTaxaMensalContaVisitor);
		}
		
	}
}
