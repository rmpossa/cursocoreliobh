package br.jus.trt3.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.data.rest.core.annotation.RestResource;

import br.jus.trt3.visitor.AplicaTaxaMensalContaVisitor;

@Entity
@DiscriminatorValue(value = "I")
public class ContaInvestimento extends Conta{
	@OneToOne
    @JoinColumn(name = "tipoaplicacao_id")
    @RestResource(path = "contaInvestimentoTipoAplicacao", rel="tipoAplicacao")
	private TipoAplicacao tipoAplicacao;
	
	public ContaInvestimento(Long id, double saldoInicial, TipoAplicacao tipoAplicacao) {
		super(id, saldoInicial);
		this.tipoAplicacao = tipoAplicacao;
	}
	
	public ContaInvestimento() {
	
	}
	
	public double recuperaRentabilidade() {
		return tipoAplicacao.recuperaRentabilidade();
	}
	
	@Override
	public void accept(AplicaTaxaMensalContaVisitor visitor) {
		visitor.visit(this);
		
	}

	public TipoAplicacao getTipoAplicacao() {
		return tipoAplicacao;
	}

	public void setTipoAplicacao(TipoAplicacao tipoAplicacao) {
		this.tipoAplicacao = tipoAplicacao;
	}
	
	
	
	
}
