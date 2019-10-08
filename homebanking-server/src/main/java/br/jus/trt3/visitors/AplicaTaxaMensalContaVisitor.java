package br.jus.trt3.visitors;

import br.jus.trt3.models.ContaCorrente;
import br.jus.trt3.models.ContaInvestimento;

public interface AplicaTaxaMensalContaVisitor {
	public void visit(ContaCorrente contaCorrente);
	public void visit(ContaInvestimento contaInvestimento);
}
