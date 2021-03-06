package br.jus.trt3.homebanking.models;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "D")
public class CDB extends TipoAplicacao {

		
	@Column
	private double taxa;
	@Column
	private double percentual;
	
	public CDB(double taxa, double percentual) {
		this.taxa = taxa;
		this.percentual = percentual;
	}
	
	public CDB() {
		
	}
	
	public double getTaxa() {
		return taxa;
	}

	public void setTaxa(double taxa) {
		this.taxa = taxa;
	}

	public double getPercentual() {
		return percentual;
	}

	public void setPercentual(double percentual) {
		this.percentual = percentual;
	}

	@Override
	public double recuperaRentabilidade() {
		// TODO Auto-generated method stub
		return 1+(percentual*taxa);
	}

	

}
