package br.jus.trt3.homebanking.controllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.jus.trt3.homebanking.models.Cliente;
import br.jus.trt3.homebanking.models.Conta;
import br.jus.trt3.homebanking.models.ContaInvestimento;
import br.jus.trt3.homebanking.models.Movimentacao;
import br.jus.trt3.homebanking.models.TipoOperacao;
import br.jus.trt3.homebanking.observers.Coaf;
import br.jus.trt3.homebanking.observers.Observador;
import br.jus.trt3.homebanking.observers.Subject;
import br.jus.trt3.homebanking.repositories.ClienteRepositorio;
import br.jus.trt3.homebanking.repositories.ContaRepositorio;
import br.jus.trt3.homebanking.repositories.MovimentacaoRepositorio;
import br.jus.trt3.homebanking.strategies.ClienteDiferenteStrategy;
import br.jus.trt3.homebanking.strategies.MesmoClienteStrategy;
import br.jus.trt3.homebanking.strategies.TransferenciaStrategy;

@RestController
public class ManipuladorConta implements Subject{
	
	@Autowired
	private ClienteRepositorio clienteRepositorio;
	@Autowired
	private ContaRepositorio contaRepositorio;
	@Autowired
	private MovimentacaoRepositorio movimentacaoRepositorio;
	
	private List<Observador> observers = new ArrayList<Observador>();
	
	public ManipuladorConta() {
		registerObserver(Coaf.getInstance());
	}
	

	@RequestMapping("/registraSaldoInicial")
	public void registraSaldoInicial(@RequestParam(value="nomeCliente", defaultValue="") String nomeCliente) {
		Cliente cliente = clienteRepositorio.findByNome(nomeCliente);
		cliente.getContas().forEach(c1 -> registraMovimentacao(c1, c1.getSaldo(), TipoOperacao.SALDO_INICIAL));
	}

	@RequestMapping("/depositar")
	public void depositar(@RequestParam(value="codigoConta") String codigoConta, @RequestParam(value="valor") double valor) {
		Conta conta = contaRepositorio.findByCodigoConta(codigoConta);
		conta.credita(valor) ;
		
		registraMovimentacao(conta, valor, TipoOperacao.DEPOSITO);
		notifyObservers(conta, valor, TipoOperacao.DEPOSITO);
	}
	
	@RequestMapping("/aplicar")
	public void aplicar(@RequestParam(value="codigoConta") String codigoConta, @RequestParam(value="valor") double valor) throws Exception{
		Conta conta = contaRepositorio.findByCodigoConta(codigoConta);
		if (!(conta instanceof ContaInvestimento)) {
			throw new Exception("É necessário uma conta investimento para fazer aplicações");
		}
		if(valor > conta.getSaldo()) {
			throw new Exception("Conta sem saldo suficiente para esta operação.");
		}
		ContaInvestimento contaInvestimento = (ContaInvestimento) conta;
		contaInvestimento.debita(valor) ;
		
		registraMovimentacao(contaInvestimento, -valor, TipoOperacao.APLICACAO);
		notifyObservers(contaInvestimento, valor, TipoOperacao.APLICACAO);
	}
	
	@RequestMapping("/sacar")
	public void sacar(@RequestParam(value="codigoConta") String codigoConta, @RequestParam(value="valor") double valor) throws Exception{
		Conta conta = contaRepositorio.findByCodigoConta(codigoConta);
		if(valor > conta.getSaldo()) {
			throw new Exception("Conta sem saldo suficiente para esta operação.");
		}
		LocalDateTime hora = LocalDateTime.now();
		
		if((hora.getHour() < 6 || hora.getHour() > 22) && valor > 1000.00) {
			throw new Exception("Não é possível sacar mais de 1000 reais antes das 6h e depois das 22h.");
		}
		conta.debita(valor) ;
		
		registraMovimentacao(conta, -valor, TipoOperacao.SAQUE);
		notifyObservers(conta, valor, TipoOperacao.SAQUE);
	}
	
	@RequestMapping("/transferir")
	public void transferir(@RequestParam(value="codigoContaOrigem") String codigoContaOrigem, @RequestParam(value="codigoContaDestino") String codigoContaDestino, @RequestParam(value="valor") double valor) throws Exception{
		Conta contaOrigem = contaRepositorio.findByCodigoConta(codigoContaOrigem);
		Conta contaDestino= contaRepositorio.findByCodigoConta(codigoContaDestino);
	    double saldoContaOrigem = contaOrigem.getSaldo();
	    double saldoContaDestino = contaDestino.getSaldo();
	    
	    TransferenciaStrategy transferenciaStrategy;
	    
	    if(contaOrigem.getCliente().getId() == contaDestino.getCliente().getId()) {
	    	transferenciaStrategy = new MesmoClienteStrategy();
	    } else {
	    	transferenciaStrategy = new ClienteDiferenteStrategy(0.01);
	    }
	    	
	    transferenciaStrategy.transfere(contaOrigem, contaDestino, valor);
		
		
		registraMovimentacao(contaOrigem, -(saldoContaOrigem - contaOrigem.getSaldo()), TipoOperacao.TRANSFERENCIA_ORIGEM);
		registraMovimentacao(contaDestino, contaDestino.getSaldo() - saldoContaDestino, TipoOperacao.TRANSFERENCIA_DESTINO);
		
		notifyObservers(contaOrigem, saldoContaOrigem - contaOrigem.getSaldo(), TipoOperacao.TRANSFERENCIA_ORIGEM);
		notifyObservers(contaDestino, contaDestino.getSaldo() - saldoContaDestino, TipoOperacao.TRANSFERENCIA_DESTINO);
	}
	
	@RequestMapping("/resgatar")
	public void resgatar(@RequestParam(value="codigoConta") String codigoConta, @RequestParam(value="valor") double valor) throws Exception{
		Conta conta = contaRepositorio.findByCodigoConta(codigoConta);
		if (!(conta instanceof ContaInvestimento)) {
			throw new Exception("É necessário uma conta investimento para fazer resgates");
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
				if(valor > 50000 ) { // falta verificar se é depósito
					observer.registraEvento(conta, valor, dataAtual, tipoOperacao);
					
				}
			} else {
				observer.registraEvento(conta, valor, dataAtual, tipoOperacao);
			}
        }		
	}
	
	private void registraMovimentacao(Conta conta, double valor, TipoOperacao tipoOperacao) {
		
		LocalDate dataAtual = LocalDate.now();
		
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setConta(conta);
		movimentacao.setData(dataAtual);
		movimentacao.setValor(valor);
		movimentacao.setTipoOperacao(tipoOperacao);
		
		movimentacaoRepositorio.save(movimentacao);
	}
	
	@RequestMapping("/recuperaExtrato")
	@ResponseBody
	public String recuperaExtrato(@RequestParam(value="codigoConta") String codigoConta) {
		Conta conta = contaRepositorio.findByCodigoConta(codigoConta);
		String extrato = "";
		synchronized (System.out) {
			if (conta == null) {
				return "Falha ao imprimir extrato: conta nula";
			}
			List<Movimentacao> movimentacoesContaEspecifica = conta.getMovimentacoes();
			
			extrato = "<html><body>";
			extrato += "==========================================================================================<br/>";
			extrato += "EXTRATO DA CONTA No. " + conta.getCodigoConta()+ " DO CLIENTE " + conta.getCliente().getNome() + "<p>";
			
			if (movimentacoesContaEspecifica != null) {
				extrato+= "<table border=\"1\"><tr><td>DATA</td><td>OPERAÇÃO</td><td>VALOR</td><td>SALDO</td></tr>";
				extrato += "------------------------------------------------------------------------------------------<br/>";
				double saldo = 0.0;

				for (Movimentacao m:movimentacoesContaEspecifica) {
					saldo += m.getValor();
					extrato += "<tr><th>"+ m.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "</th>" + 
							"<th>"+ m.getTipoOperacao() + "</th>" +
							"<th>"+ m.getValor() + "</th>" +
							"<th>"+ saldo + "</th></tr>"; 
						
				}
				extrato += "</table><p>";
			}
			else
				extrato += "</table><p>Não há movimentações para essa conta.<p>";
			extrato += "==========================================================================================";
			extrato += "</body></html>";
		}
		return extrato;
	}
	
}
