package laboratorioDia02;

public class Conta {
	private String id;
	private Double saldo = 0.0;
	private Cliente cliente;
	
	public Conta(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
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
	
}
