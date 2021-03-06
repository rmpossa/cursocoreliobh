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

	public void registraSaldoInicial(Cliente cliente) {
		cliente.getContas().forEach(c1 -> registraMovimentacao(c1, c1.getSaldo(), TipoOperacao.SALDO_INICIAL));
	}
		
	public void sacar(Conta conta, double valor) throws Exception{
		if(valor > conta.getSaldo()) {
			throw new Exception("Conta sem saldo suficiente para esta opera��o.");
		}
		LocalDateTime hora = LocalDateTime.now();
		
		if((hora.getHour() < 6 || hora.getHour() > 22) && valor > 1000.00) {
			throw new Exception("N�o � poss�vel sacar mais de 1000 reais antes das 6h e depois das 22h.");
		}
		conta.debita(valor) ;
		
		registraMovimentacao(conta, -valor, TipoOperacao.SAQUE);
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
		
		
		registraMovimentacao(contaOrigem, -(saldoContaOrigem - contaOrigem.getSaldo()), TipoOperacao.TRANSFERENCIA_ORIGEM);
		registraMovimentacao(contaDestino, contaDestino.getSaldo() - saldoContaDestino, TipoOperacao.TRANSFERENCIA_DESTINO);
		
		notifyObservers(contaOrigem, saldoContaOrigem - contaOrigem.getSaldo(), TipoOperacao.TRANSFERENCIA_ORIGEM);
		notifyObservers(contaDestino, contaDestino.getSaldo() - saldoContaDestino, TipoOperacao.TRANSFERENCIA_DESTINO);
	}
	
	public void aplicar(Conta conta, double valor) throws Exception {
		if (!(conta instanceof ContaInvestimento)) {
			throw new Exception("� necess�rio uma conta investimento para fazer aplica��es");
		}
		if(valor > conta.getSaldo()) {
			throw new Exception("Conta sem saldo suficiente para esta opera��o.");
		}
		ContaInvestimento contaInvestimento = (ContaInvestimento) conta;
		contaInvestimento.debita(valor) ;
		
		registraMovimentacao(contaInvestimento, -valor, TipoOperacao.APLICACAO);
		notifyObservers(contaInvestimento, valor, TipoOperacao.APLICACAO);
	}
	
	public void resgatar(Conta conta, double valor) throws Exception {
		if (!(conta instanceof ContaInvestimento)) {
			throw new Exception("� necess�rio uma conta investimento para fazer resgates");
		}
		ContaInvestimento contaInvestimento = (ContaInvestimento) conta;
		double valorResgatado = valor*contaInvestimento.recuperaRentabilidade();
		contaInvestimento.credita(valorResgatado) ;
		
		registraMovimentacao(contaInvestimento, valorResgatado, TipoOperacao.RESGATE);
		notifyObservers(contaInvestimento, valorResgatado, TipoOperacao.RESGATE);
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
				if(valor > 50000 ) { // falta verificar se � dep�sito
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
		synchronized (System.out) {
			if (conta == null) {
				System.out.println("");
				System.out.println("Falha ao imprimir extrato: conta nula");
				System.out.println("");
				return;
			}
			List<Movimentacao> movimentacoesContaEspecifica = movimentacoesContas.get(conta);
			
			System.out.println("");
			System.out.println("==========================================================================================");
	//		System.out.println("Extrato da conta n� " + conta.getId() + " do cliente " + conta.getCliente().getNome());
			System.out.println("EXTRATO DA CONTA N� " + conta.getId() + " DO CLIENTE " + conta.getCliente().getNome());
			System.out.println("");
			
			if (movimentacoesContaEspecifica != null) {
				System.out.println("DATA           OPERA��O                                               VALOR          SALDO");
				System.out.println("------------------------------------------------------------------------------------------");
				double saldo = 0.0;
//				movimentacoesContaEspecifica.forEach(
	//				m -> System.out.println("Data: " + m.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " ; Tipo Opera��o: " + m.getTipoOperacao() +" ; Valor: " + m.getValor()));
				for (Movimentacao m:movimentacoesContaEspecifica) {
					saldo += m.getValor();
					System.out.println(
						String.format("%-15s",m.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))) + 
						String.format("%-45s",m.getTipoOperacao()) +
						String.format("%15.2f",m.getValor()) +
						String.format("%15.2f",saldo));
				}
			}
			else
				System.out.println("N�o h� movimenta��es para esta conta");
			System.out.println("==========================================================================================");
			System.out.println("");
		}
	}
	
}
