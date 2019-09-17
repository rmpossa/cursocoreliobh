package laboratorioDia02;

public class Principal {
	public static void main(String[] args) {
		ExtratoMovimentacoes extratoMovimentacoes = new ExtratoMovimentacoes();
		
		Cliente cliente1 = new Cliente();
		cliente1.setNome("Rodrigo");
		cliente1.adicionarConta("1");
		Conta conta1Cliente1 = cliente1.getContas().get(0);
		
		ManipuladorConta manipuladorConta = new ManipuladorConta();
		manipuladorConta.registerObserver(extratoMovimentacoes);
		
		manipuladorConta.depositar(conta1Cliente1, 1500);
		
		Cliente cliente2 = new Cliente();
		cliente2.setNome("Tarcisio");
		cliente2.adicionarConta("2");
		Conta conta2Cliente2 = cliente2.getContas().get(0);
		
		try {
			manipuladorConta.transferir(new ClienteDiferenteStrategy(0.01), conta1Cliente1, conta2Cliente2, 1100);
			
			extratoMovimentacoes.imprimirExtrato(conta1Cliente1);
			extratoMovimentacoes.imprimirExtrato(conta2Cliente2);
			
			manipuladorConta.sacar(conta2Cliente2, 1000);
			extratoMovimentacoes.imprimirExtrato(conta2Cliente2);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		/*Conta conta1 = new Conta();
		conta1.setId("1");
		
		Conta conta2 = new Conta();
		conta2.setId("2");
		
		ExtratoMovimentacoes extratoMovimentacoes = new ExtratoMovimentacoes();
		
		ManipuladorConta manipuladorConta = new ManipuladorConta();
		
		manipuladorConta1.registerObserver(extratoMovimentacoes);
		manipuladorConta2.registerObserver(extratoMovimentacoes);
		manipuladorConta1.credita(10);
		manipuladorConta2.debita(15);
		manipuladorConta1.debita(5);
		manipuladorConta2.credita(20);
		
		extratoMovimentacoes.imprimirExtrato(conta1);
		extratoMovimentacoes.imprimirExtrato(conta2);*/
		
	}
}
