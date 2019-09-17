package laboratorioDia02;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.List;

public class ManipuladorConta implements Subject{
	
	private List<Observador> observers = new ArrayList<Observador>();
	
	public ManipuladorConta() {
		
	}
	
//	public void debita(Conta conta, double valor) throws Exception{
//		double saldoAtual = conta.getSaldo();
//		
//		if(valor > saldoAtual) {
//			throw new Exception("Conta sem saldo suficiente para esta operação.");
//		}
//		conta.setSaldo(saldoAtual - valor) ;
//		
//		notifyObservers(conta, valor, TipoMovimentacao.DEBITADO);
//	}
	
	public void sacar(Conta conta, double valor) throws Exception{
		if(valor > conta.getSaldo()) {
			throw new Exception("Conta sem saldo suficiente para esta operação.");
		}
		LocalDateTime hora = LocalDateTime.now();
		
		if((hora.getHour() < 6 || hora.getHour() > 22) && valor > 1000.00) {
			throw new Exception("Não é possível sacar mais de 1000 reais antes das 6h e depois das 22h.");
		}
		conta.debita(valor) ;
		
		notifyObservers(conta, valor, TipoMovimentacao.DEBITADO);
	}
	
//	public void credita(Conta conta, double valor) {
//		conta.setSaldo(conta.getSaldo() + valor) ;
//		
//		notifyObservers(conta, valor, TipoMovimentacao.CREDITADO);
//	}
	
	public void depositar(Conta conta, double valor) {
		conta.credita(valor) ;
		
		notifyObservers(conta, valor, TipoMovimentacao.CREDITADO);
	}
	
//	public void saque(Conta conta, double valor) throws Exception{
//		LocalDateTime hora = LocalDateTime.now();
//		
//		if((hora.getHour() < 6 || hora.getHour() > 22) && valor > 1000.00) {
//			throw new Exception("Não é possível sacar mais de 1000 reais antes das 6h e depois das 22h.");
//		}
//		
//		debita(conta, valor);
//	}
	
	public void transferir(TransferenciaStrategy transferenciaStrategy, Conta contaOrigem, Conta contaDestino, double valor) throws Exception{
	    double saldoContaOrigem = contaOrigem.getSaldo();
	    double saldoContaDestino = contaDestino.getSaldo();
		transferenciaStrategy.transfere(contaOrigem, contaDestino, valor);
		notifyObservers(contaOrigem, saldoContaOrigem - contaOrigem.getSaldo(), TipoMovimentacao.DEBITADO);
		notifyObservers(contaDestino, contaDestino.getSaldo() - saldoContaDestino, TipoMovimentacao.CREDITADO);
	}
	
	@Override
    public void registerObserver(Observador o) {
        this.observers.add(o);
    }

	@Override
	public void notifyObservers(Conta conta, double valor, TipoMovimentacao tipoMovimentacao) {
		LocalDate dataAtual = LocalDate.now();
		
		for(Observador observer : this.observers){
			if(observer instanceof Coaf) {
				if(valor > 50000 ) { // falta verificar se é depósito
					observer.registraEvento(conta, valor, dataAtual, tipoMovimentacao);
					
				}
			} else {
				observer.registraEvento(conta, valor, dataAtual, tipoMovimentacao);
			}
        }		
	}
	
	
}
