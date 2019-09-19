package laboratorioDia02;

public class Principal {
	public static void main(String[] args) {
		Cliente cliente1 = new Cliente.ClienteBuilder("Rodrigo", "Possa")
			.setTelefone("319898-1212")
			.build()
			.adicionarConta(new ContaCorrente("1", 1000.0, 1000.0))
			.adicionarConta(new ContaInvestimento("2", 12000.0, new Poupanca(0.03)));
		
		ClienteAdapter clienteAdapter = new ClienteAdapterImpl(cliente1);
		System.out.println(clienteAdapter.getNomeClientePadrao());
		System.out.println("Endereco: " + cliente1.getEndereco() + "; Telefone: " + cliente1.getTelefone());
//		Conta conta1Cliente1 = cliente1.getContas().get(0);
		Conta conta1Cliente1 = cliente1.getConta("1");
		
		ManipuladorConta manipuladorConta = new ManipuladorConta();
		manipuladorConta.registerObserver(Coaf.getInstance());
		manipuladorConta.registerObserver(PortifolioObserver.getInstance());
		
		manipuladorConta.depositar(conta1Cliente1, 51000.0);
		
		Cliente cliente2 = new Cliente.ClienteBuilder("Tarcísio", "Brandão")
			.setEndereco("Av xyz, 123 - B. abc")
			.build()
			.adicionarConta(new ContaCorrente("3", 800.0, 1000.0))
			.adicionarConta(new ContaInvestimento("4", 20000.0, new CDB(0.05,0.9)));
		
		clienteAdapter = new ClienteAdapterImpl(cliente2);
		System.out.println(clienteAdapter.getNomeClienteFormatoAmericano());
		System.out.println("Endereco: " + cliente2.getEndereco() + "; Telefone: " + cliente2.getTelefone());
		
//		Conta conta2Cliente2 = cliente2.getContas().get(0);
		Conta conta2Cliente2 = cliente2.getConta("3");
		Conta conta3Cliente2 = cliente2.getConta("4");
		
		try {
			manipuladorConta.transferir(new ClienteDiferenteStrategy(0.01), conta1Cliente1, conta2Cliente2, 1100);
			
			manipuladorConta.imprimirExtrato(conta1Cliente1);
			manipuladorConta.imprimirExtrato(conta2Cliente2);
			
			manipuladorConta.sacar(conta2Cliente2, 1000);
			manipuladorConta.imprimirExtrato(conta2Cliente2);
			
			manipuladorConta.sacar(conta3Cliente2, 5000);
			
			System.out.println("Saldo cliente 1:" + cliente1.recuperaSaldoTotal());
			System.out.println("Saldo cliente 2:" + cliente2.recuperaSaldoTotal());
			
			// para dar tempo de os subscribers lerem as mensagens publicadas
			Thread.sleep(1000);
			
			AplicaTaxaMensalClienteVisitor aplicaTaxaMensalClienteVisitor = new AplicaTaxaMensalClienteVisitorImpl();
			
			cliente1.accept(aplicaTaxaMensalClienteVisitor);
			cliente2.accept(aplicaTaxaMensalClienteVisitor);
			
			System.out.println("Saldo cliente 1:" + cliente1.recuperaSaldoTotal());
			System.out.println("Saldo cliente 2:" + cliente2.recuperaSaldoTotal());
			
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
