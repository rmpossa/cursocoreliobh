package laboratorioDia02;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
	private String nome;
	private String sobrenome;
	private String endereco;
	private String telefone;
	
	private List<Conta> contas = new ArrayList<Conta>();
	
	public void adicionarConta(String id) {
		Conta conta = new Conta(this);
		conta.setId(id);
		contas.add(conta);
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
