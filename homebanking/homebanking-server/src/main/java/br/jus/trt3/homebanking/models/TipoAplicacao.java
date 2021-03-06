package br.jus.trt3.homebanking.models;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", length = 1, discriminatorType = DiscriminatorType.STRING)
public abstract class TipoAplicacao {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(mappedBy = "tipoAplicacao")
	private ContaInvestimento contaInvestimento;
	
	abstract double recuperaRentabilidade();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ContaInvestimento getContaInvestimento() {
		return contaInvestimento;
	}

	public void setContaInvestimento(ContaInvestimento contaInvestimento) {
		this.contaInvestimento = contaInvestimento;
	}


	
}
