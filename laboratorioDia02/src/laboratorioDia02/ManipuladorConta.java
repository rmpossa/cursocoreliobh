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
	
	public void debita(Conta conta, double valor) throws Exception{
		double saldoAtual = conta.getSaldo();
		
		if(valor > saldoAtual) {
			throw new Exception("Conta sem saldo suficiente para esta operação.");
		}
		conta.setSaldo(saldoAtual - valor) ;
		
		notifyObservers(conta, valor, TipoMovimentacao.DEBITADO);
	}
	
	public void credita(Conta conta, double valor) {
		conta.setSaldo(conta.getSaldo() + valor) ;
		
		notifyObservers(conta, valor, TipoMovimentacao.CREDITADO);
	}
	
	public void saque(Conta conta, double valor) throws Exception{
		LocalDateTime hora = LocalDateTime.now();
		
		if((hora.getHour() < 6 || hora.getHour() > 22) && valor > 1000.00) {
			throw new Exception("Não é possível sacar mais de 1000 reais antes das 6h e depois das 22h.");
		}
		
		debita(conta, valor);
	}
	
	public void transfere(Conta contaDebitada, Conta contaCreditada, double valor) throws Exception{
		double taxa = 0.0;
		
		if(contaDebitada.getCliente().getNome().equals(contaCreditada.getCliente().getNome())) {
			taxa = 0.0;
		} else {
			taxa = 1.0;
		}
		
		debita(contaDebitada, valor*(1+taxa/100));
		credita(contaCreditada, valor);
	}
	
	@Override
    public void registerObserver(Observador o) {
        this.observers.add(o);
    }

	@Override
	public void notifyObservers(Conta conta, double valor, TipoMovimentacao tipoMovimentacao) {
		LocalDate dataAtual = LocalDate.now();
		
		for(Observador observer : this.observers){
            observer.registraEvento(conta, valor, dataAtual, tipoMovimentacao);
        }		
	}
	
	
}
