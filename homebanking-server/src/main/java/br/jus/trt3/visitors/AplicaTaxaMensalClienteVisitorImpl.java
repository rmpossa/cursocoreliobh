package br.jus.trt3.visitors;

import br.jus.trt3.models.Cliente;
import br.jus.trt3.models.Conta;

public class AplicaTaxaMensalClienteVisitorImpl implements AplicaTaxaMensalClienteVisitor{
	@Override
	public void visit(Cliente cliente) {
		AplicaTaxaMensalContaVisitor aplicaTaxaMensalContaVisitor = new AplicaTaxaMensalContaVisitorImpl();
		
		for(Conta conta:cliente.getContas()) {
		conta.accept(aplicaTaxaMensalContaVisitor);
		}
		
	}
}
