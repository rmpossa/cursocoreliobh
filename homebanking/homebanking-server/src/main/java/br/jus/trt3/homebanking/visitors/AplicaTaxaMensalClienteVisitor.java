package br.jus.trt3.homebanking.visitors;

import br.jus.trt3.homebanking.models.Cliente;

public interface AplicaTaxaMensalClienteVisitor {
	public void visit(Cliente cliente);
}
