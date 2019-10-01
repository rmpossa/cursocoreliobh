package br.jus.trt3.visitor;

import br.jus.trt3.model.ContaCorrente;
import br.jus.trt3.model.ContaInvestimento;

public interface AplicaTaxaMensalContaVisitor {
	public void visit(ContaCorrente contaCorrente);
	public void visit(ContaInvestimento contaInvestimento);
}
