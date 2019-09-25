package laboratorioDia02;

public class Principal {
	public static void main(String[] args) {
		try {
			ManipuladorConta manipuladorConta = new ManipuladorConta();
			manipuladorConta.registerObserver(Coaf.getInstance());
			manipuladorConta.registerObserver(PortifolioObserver.getInstance());
			
			Cliente cliente1 = new Cliente.ClienteBuilder("Rodrigo", "Possa")
				.setTelefone("319898-1212")
				.build()
				.adicionarConta(new ContaCorrente("1", 1000.0, 1000.0))
				.adicionarConta(new ContaInvestimento("2", 12000.0, new Poupanca(0.003)));
			manipuladorConta.registraSaldoInicial(cliente1);
			
			ClienteAdapter clienteAdapter = new ClienteAdapterImpl(cliente1);
			System.out.println(clienteAdapter.getNomeClientePadrao());
			System.out.println("Endereco: " + cliente1.getEndereco() + "; Telefone: " + cliente1.getTelefone());
	//		Conta conta1Cliente1 = cliente1.getContas().get(0);
			Conta contaCorrenteCliente1 = cliente1.getConta("1");
			Conta contaInvestimentoCliente1 = cliente1.getConta("2");
			
			manipuladorConta.depositar(contaCorrenteCliente1, 51000.0);
			manipuladorConta.aplicar(contaInvestimentoCliente1, 10000.0);
			
			Cliente cliente2 = new Cliente.ClienteBuilder("Tarcísio", "Brandão")
				.setEndereco("Av xyz, 123 - B. abc")
				.build()
				.adicionarConta(new ContaCorrente("3", 800.0, 1000.0))
				.adicionarConta(new ContaInvestimento("4", 20000.0, new CDB(0.01,1.0)));
			manipuladorConta.registraSaldoInicial(cliente2);

			clienteAdapter = new ClienteAdapterImpl(cliente2);
			System.out.println(clienteAdapter.getNomeClienteFormatoAmericano());
			System.out.println("Endereco: " + cliente2.getEndereco() + "; Telefone: " + cliente2.getTelefone());
			
	//		Conta conta2Cliente2 = cliente2.getContas().get(0);
			Conta contaCorrenteCliente2 = cliente2.getConta("3");
			Conta contaInvestimentoCliente2 = cliente2.getConta("4");
			
			manipuladorConta.aplicar(contaInvestimentoCliente2, 20000.0);
			
			Cliente cliente3 = new Cliente.ClienteBuilder("Lucas", "Bibiano")
				.setEndereco("Rua dos Anjos, 23 - Belvedere")
				.build()
				.adicionarConta(new ContaCorrente("5", 300.0, 1500.0))
				.adicionarConta(new ContaInvestimento("6", 28000.0, new CDB(0.01,1.0)));
			manipuladorConta.registraSaldoInicial(cliente3);
			
			Conta contaCorrenteCliente3 = cliente3.getConta("5");
			Conta contaInvestimentoCliente3 = cliente3.getConta("6");
		
			manipuladorConta.transferir(new ClienteDiferenteStrategy(0.01), contaCorrenteCliente1, contaCorrenteCliente2, 1100);
			
			manipuladorConta.imprimirExtrato(contaCorrenteCliente1);
			
			manipuladorConta.resgatar(contaInvestimentoCliente1, 10000.0);
			manipuladorConta.imprimirExtrato(contaInvestimentoCliente1);

			manipuladorConta.sacar(contaCorrenteCliente2, 1000);
			manipuladorConta.imprimirExtrato(contaCorrenteCliente2);
			
			manipuladorConta.resgatar(contaInvestimentoCliente2, 20000.0);
			manipuladorConta.imprimirExtrato(contaInvestimentoCliente2);

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
