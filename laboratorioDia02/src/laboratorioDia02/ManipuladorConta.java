package laboratorioDia02;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManipuladorConta implements Subject{
	
	private Map<Conta,List<Movimentacao>> movimentacoesContas;
	
	private List<Observador> observers = new ArrayList<Observador>();
	
	public ManipuladorConta() {
		this.movimentacoesContas = new HashMap<Conta,List<Movimentacao>>();
	}
	
	public List<Conta> obterContasCliente(Cliente cliente) {
		return cliente.getContas();
	}

		
	public void sacar(Conta conta, double valor) throws Exception{
		if(valor > conta.getSaldo()) {
			throw new Exception("Conta sem saldo suficiente para esta operação.");
		}
		LocalDateTime hora = LocalDateTime.now();
		
		if((hora.getHour() < 6 || hora.getHour() > 22) && valor > 1000.00) {
			throw new Exception("Não é possível sacar mais de 1000 reais antes das 6h e depois das 22h.");
		}
		conta.debita(valor) ;
		
		registraMovimentacao(conta, valor, TipoOperacao.SAQUE);
		notifyObservers(conta, valor, TipoOperacao.SAQUE);
	}
	
	public void depositar(Conta conta, double valor) {
		conta.credita(valor) ;
		
		registraMovimentacao(conta, valor, TipoOperacao.DEPOSITO);
		notifyObservers(conta, valor, TipoOperacao.DEPOSITO);
	}
	
	public void transferir(TransferenciaStrategy transferenciaStrategy, Conta contaOrigem, Conta contaDestino, double valor) throws Exception{
	    double saldoContaOrigem = contaOrigem.getSaldo();
	    double saldoContaDestino = contaDestino.getSaldo();
		transferenciaStrategy.transfere(contaOrigem, contaDestino, valor);
		
		
		registraMovimentacao(contaOrigem, saldoContaOrigem - contaOrigem.getSaldo(), TipoOperacao.TRANSFERENCIA_ORIGEM);
		registraMovimentacao(contaDestino, contaDestino.getSaldo() - saldoContaDestino, TipoOperacao.TRANSFERENCIA_DESTINO);
		
		notifyObservers(contaOrigem, saldoContaOrigem - contaOrigem.getSaldo(), TipoOperacao.TRANSFERENCIA_ORIGEM);
		notifyObservers(contaDestino, contaDestino.getSaldo() - saldoContaDestino, TipoOperacao.TRANSFERENCIA_DESTINO);
	}
	
	@Override
    public void registerObserver(Observador o) {
        this.observers.add(o);
    }

	@Override
	public void notifyObservers(Conta conta, double valor, TipoOperacao tipoOperacao) {
		LocalDate dataAtual = LocalDate.now();
		
		for(Observador observer : this.observers){
			if(observer instanceof Coaf) {
				if(valor > 50000 ) { // falta verificar se é depósito
					observer.registraEvento(conta, valor, dataAtual, tipoOperacao);
					
				}
			} else {
				observer.registraEvento(conta, valor, dataAtual, tipoOperacao);
			}
        }		
	}
	
	public void registraMovimentacao(Conta conta, double valor, TipoOperacao tipoOperacao) {
		
		LocalDate dataAtual = LocalDate.now();
		
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setConta(conta);
		movimentacao.setData(dataAtual);
		movimentacao.setValor(valor);
		movimentacao.setTipoOperacao(tipoOperacao);
		
		if(!movimentacoesContas.containsKey(conta)) {
			movimentacoesContas.put(conta, new ArrayList<Movimentacao>());
		}
		
		List<Movimentacao> movimentacoesContaEspecifica = movimentacoesContas.get(conta);
		movimentacoesContaEspecifica.add(movimentacao);
		
	}
	
	public void imprimirExtrato(Conta conta) {
		List<Movimentacao> movimentacoesContaEspecifica = movimentacoesContas.get(conta);
		
		System.out.println("Conta Id: " + conta.getId());
		
		movimentacoesContaEspecifica.forEach(
				m -> System.out.println("Data: " + m.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " ; Tipo Operação: " + m.getTipoOperacao() +" ; Valor: " + m.getValor()));	
	}
	
}
