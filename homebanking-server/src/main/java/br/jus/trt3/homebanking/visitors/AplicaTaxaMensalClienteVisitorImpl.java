package br.jus.trt3.homebanking.visitors;

import br.jus.trt3.homebanking.models.Cliente;
import br.jus.trt3.homebanking.models.Conta;

public class AplicaTaxaMensalClienteVisitorImpl implements AplicaTaxaMensalClienteVisitor{
	@Override
	public void visit(Cliente cliente) {
		AplicaTaxaMensalContaVisitor aplicaTaxaMensalContaVisitor = new AplicaTaxaMensalContaVisitorImpl();
		
		for(Conta conta:cliente.getContas()) {
		conta.accept(aplicaTaxaMensalContaVisitor);
		}
		
	}
}
