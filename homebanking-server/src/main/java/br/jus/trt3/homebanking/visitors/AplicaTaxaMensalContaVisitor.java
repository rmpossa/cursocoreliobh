package br.jus.trt3.homebanking.visitors;

import br.jus.trt3.homebanking.models.ContaCorrente;
import br.jus.trt3.homebanking.models.ContaInvestimento;

public interface AplicaTaxaMensalContaVisitor {
	public void visit(ContaCorrente contaCorrente);
	public void visit(ContaInvestimento contaInvestimento);
}
