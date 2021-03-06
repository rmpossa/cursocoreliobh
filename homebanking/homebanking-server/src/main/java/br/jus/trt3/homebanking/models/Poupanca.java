package br.jus.trt3.homebanking.models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "P")
public class Poupanca extends TipoAplicacao {
		
	@Column
	private double taxa;
	
	public Poupanca(double taxa) {
		this.taxa = taxa;
	}
	
	public Poupanca() {
		
	}

	
	public double getTaxa() {
		return taxa;
	}

	public void setTaxa(double taxa) {
		this.taxa = taxa;
	}

	@Override
	public double recuperaRentabilidade() {
		// TODO Auto-generated method stub
		return 1+taxa;
	}
	

}
