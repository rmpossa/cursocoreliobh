package br.jus.trt3.homebanking.models;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class ContaCorrenteTest {
	private ContaCorrente conta;
	
	@Before
	public void init() {
		conta = new ContaCorrente(1l, 100.0, 100.0);
	}
	
	@Test
	@DisplayName("deve debitar R$ 100,00 e zerar o saldo") 
	public void debito() {
		conta.debita(100);
		assertTrue(conta.getSaldo() == 0);		
	}
	
	@Test
	@DisplayName("deve creditar R$ 100,00 e o saldo deve ser R$200,00")
	public void deveFazerOCredito() {
		conta.credita(100);
		assertTrue(conta.getSaldo() == 200.0);	
	}
	
	@Test
	@DisplayName("deve")
	public void debitoMenorQueLimite() {
		ContaCorrente contaDevedora = new ContaCorrente(2l, 0, 0);
		contaDevedora.debita(101);
		assertTrue(contaDevedora.getSaldo() == -101);	
	}
	
	@Test
	public void deevTransferirValor() {
		
	}


}
