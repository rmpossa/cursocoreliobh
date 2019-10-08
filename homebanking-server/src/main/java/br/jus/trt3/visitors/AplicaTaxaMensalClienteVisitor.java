package br.jus.trt3.visitors;

import br.jus.trt3.models.Cliente;

public interface AplicaTaxaMensalClienteVisitor {
	public void visit(Cliente cliente);
}
