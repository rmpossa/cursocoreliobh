package br.jus.trt3.homebanking.models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import br.jus.trt3.homebanking.visitors.AplicaTaxaMensalContaVisitor;

@Entity
@DiscriminatorValue(value = "C")
public class ContaCorrente extends Conta{
	@Column
	private double limiteCredito;

	public double getLimiteCredito() {
		return limiteCredito;
	}

	public void setLimiteCredito(double limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	public ContaCorrente(Long id, double saldoInicial, double limiteCredito) {
		super(id, saldoInicial);
		this.limiteCredito = limiteCredito;
	}
	
	public ContaCorrente() {
		
	}

	@Override
	public void accept(AplicaTaxaMensalContaVisitor visitor) {
		visitor.visit(this);
		
	}
	
	
	
	
}
