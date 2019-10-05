package br.jus.trt3.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.jus.trt3.visitor.AplicaTaxaMensalContaVisitor;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", length = 1, discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "T")
public abstract class Conta {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String codigoConta;
	@Column
	private Double saldo;
	
	@ManyToOne
    @JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	@OneToMany(mappedBy = "conta")
	private List<Movimentacao> movimentacoes = new ArrayList<Movimentacao>();
	
	public Conta() {
		
	}
	public Conta(Long id, double saldoInicial) {
		this.id = id;
		this.saldo = saldoInicial;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Double getSaldo() {
		return saldo;
	}
	
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void debita(double valor) {
		this.saldo -= valor;
	}
	
	public void credita(double valor) {
		this.saldo += valor;
	}
	
	public String getCodigoConta() {
		return codigoConta;
	}
	public void setCodigoConta(String codigoConta) {
		this.codigoConta = codigoConta;
	}
	
	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}
	public void setMovimentacoes(List<Movimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}
	abstract public void accept(AplicaTaxaMensalContaVisitor visitor);
	
}
