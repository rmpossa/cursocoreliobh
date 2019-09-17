package laboratorioDia02;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
	private String nome;
	private String sobrenome;
	private String endereco;
	private String telefone;
	
	
	public static class ClienteBuilder {
		
		private String nome;
		private String sobrenome;
		private String endereco;
		private String telefone;
				
		
		public ClienteBuilder(String nome, String sobrenome) {
			this.nome = nome;
			this.sobrenome = sobrenome;
		}


		public ClienteBuilder setEndereco(String endereco) {
			this.endereco = endereco;
			return this;
		}


		public ClienteBuilder setTelefone(String telefone) {
			this.telefone = telefone;
			return this;
		}
		
		public Cliente build() {
			return new Cliente(this);
		}
		
		
	}
	
	
	private Cliente(ClienteBuilder clienteBuilder) {
		this.nome = clienteBuilder.nome;
		this.sobrenome = clienteBuilder.sobrenome;
		this.endereco = clienteBuilder.endereco;
		this.telefone = clienteBuilder.telefone;
		
	}
	
	
	
	private List<Conta> contas = new ArrayList<Conta>();
	
	public Cliente adicionarConta(Conta conta) {
		conta.setCliente(this);
		contas.add(conta);
		
		return this;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
	public String getNomeCompleto() {
		return this.nome + " " + this.sobrenome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}
	
	public double recuperaSaldoTotal() {
		double saldoTotal = 0.0;
		
		for(Conta conta:contas) {
			saldoTotal += conta.getSaldo();
		}
		
		return saldoTotal;
	}
	
	public String toString() {
		return "Cliente: " + getNome() + " " + getSobrenome();
	}
	
}
