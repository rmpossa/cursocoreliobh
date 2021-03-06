package br.jus.trt3.homebanking.visitors;

import br.jus.trt3.homebanking.models.ContaCorrente;
import br.jus.trt3.homebanking.models.ContaInvestimento;

public class AplicaTaxaMensalContaVisitorImpl implements AplicaTaxaMensalContaVisitor{

	@Override
	public void visit(ContaCorrente contaCorrente) {
		contaCorrente.debita(1.0);
		
	}

	@Override
	public void visit(ContaInvestimento contaInvestimento) {
		// aplica rendimento
		contaInvestimento.credita(contaInvestimento.getSaldo()*((contaInvestimento.recuperaRentabilidade()/100)));
		// desconta 0.01% do montante da conta (taxa mensa do banco, conforme enunciado do labotar�rio 2)
		contaInvestimento.debita(contaInvestimento.getSaldo()*0.0001); 
	}
	
	

}
