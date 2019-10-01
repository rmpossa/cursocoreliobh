package br.jus.trt3.visitor;

import br.jus.trt3.model.Cliente;
import br.jus.trt3.model.Conta;

public class AplicaTaxaMensalClienteVisitorImpl implements AplicaTaxaMensalClienteVisitor{
	@Override
	public void visit(Cliente cliente) {
		AplicaTaxaMensalContaVisitor aplicaTaxaMensalContaVisitor = new AplicaTaxaMensalContaVisitorImpl();
		
		for(Conta conta:cliente.getContas()) {
		//	conta.accept(aplicaTaxaMensalContaVisitor);
		}
		
	}
}
