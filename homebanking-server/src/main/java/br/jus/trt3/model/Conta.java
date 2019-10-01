package br.jus.trt3.model;

import javax.persistence.*;

import br.jus.trt3.visitor.AplicaTaxaMensalContaVisitor;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", length = 1, discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "T")
public class Conta {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private Double saldo;
	
	@ManyToOne
    @JoinColumn(name="cliente_id")
	private Cliente cliente;
	
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
	
	//abstract public void accept(AplicaTaxaMensalContaVisitor visitor);
	
}
