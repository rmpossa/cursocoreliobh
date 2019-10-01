package br.jus.trt3.visitor;

import br.jus.trt3.model.Cliente;

public interface AplicaTaxaMensalClienteVisitor {
	public void visit(Cliente cliente);
}
