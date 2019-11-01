package br.jus.trt3.cliente;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Principal {
	private static final String baseURL= "http://api-gateway-homebanking.127.0.0.1.nip.io/";
	
	public static void executarOperacao(String operacao, String parametros) throws IOException, InterruptedException {
	   HttpClient client = HttpClient.newHttpClient();
        
		 // String no formato Json que ir� conter o corpo da requisi��o POST
       

       //Criando um HttpRequest do tipo Post, especificando sua URI e atribuindo ao m�todo Post o corpo da requisi��o
       HttpRequest request = HttpRequest.newBuilder()
               .GET()
               .uri(URI.create(baseURL + operacao+ "?" + parametros)).version(HttpClient.Version.HTTP_1_1).build();

       // Enviando a requisi��o e recebendo o Objeto de resposta da mesma.
       HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
       
	}
      
	public static String criarObjeto(String url, String json) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
        
		 // String no formato Json que ir� conter o corpo da requisi��o POST
        String body = json;

        //Criando um HttpRequest do tipo Post, especificando sua URI e atribuindo ao m�todo Post o corpo da requisi��o
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .uri(URI.create(url)).header("Content-Type", "application/json").version(HttpClient.Version.HTTP_1_1).build();

        // Enviando a requisi��o e recebendo o Objeto de resposta da mesma.
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.headers().firstValue("location").get()
        		// Execução com openshift
        		.replace("homebanking-server:8081", "homebanking-server-homebanking.127.0.0.1.nip.io").replace("coaf:8082", "coaf-homebanking.127.0.0.1.nip.io").replace("cartao-credito:8083","cartao-credito-homebanking.127.0.0.1.nip.io")
        		// Execução com docker
        		.replace("home-banking-server", "localhost").replace("coaf-server", "localhost").replace("cartao-credito-server","localhost");
	}
	
	public static int criarAssociacao(String url, String objetoPai) throws IOException, InterruptedException {
		
		HttpClient client = HttpClient.newHttpClient();
		 
       // String no formato Json que ir� conter o corpo da requisi��o POST
       String body = objetoPai; // "http://localhost:8080/libraries/1"
       
       //Criando um HttpRequest do tipo PUT, especificando sua URI e atribuindo ao m�todo PUT o corpo da requisi��o
       HttpRequest request = HttpRequest.newBuilder()
               .PUT(HttpRequest.BodyPublishers.ofString(body))
               .uri(URI.create(url)).header("Content-Type", "text/uri-list").version(HttpClient.Version.HTTP_1_1).build();

       // Enviando a requisi��o e recebendo o Objeto de resposta da mesma.
       HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
       
       return response.statusCode();
	}
	
	
	
	public static void main(String[] args) {
		try {
			
			String urlCliente1 = criarObjeto(baseURL + "clientes", "{ \"nome\": \"Rodrigo\", \"sobrenome\": \"Possa\", \"telefone\": \"12323-0998\"}");
			String urlCliente2 = criarObjeto(baseURL + "clientes", "{ \"nome\": \"Tarcisio\", \"sobrenome\": \"Brandao\", \"endereco\": \"Av. A, N 1 - Serra\"}");
			
			String urlConta1 = criarObjeto(baseURL + "contaCorrentes", "{\"codigoConta\":\"1111-1\",\"saldo\": 1000.0, \"limiteCredito\": 1000.0}");
			String urlConta2 = criarObjeto(baseURL + "contaCorrentes", "{\"codigoConta\":\"2222-2\",\"saldo\": 800.0, \"limiteCredito\": 1000.0}");
			
			
			String urlConta3 = criarObjeto(baseURL + "contaInvestimentoes", "{\"codigoConta\":\"3333-3\",\"saldo\": 12000.0}");
			String urlConta4 = criarObjeto(baseURL + "contaInvestimentoes", "{\"codigoConta\":\"4444-4\",\"saldo\": 20000.0}");
			
			String urlTipoAplicacao1 = criarObjeto(baseURL + "poupancas", "{\"taxa\": 0.003}");
			String urlTipoAplicacao2 = criarObjeto(baseURL + "cDBs", "{\"taxa\": 0.01}, \"percentual\":1.0}");
		
			criarAssociacao(urlConta3 + "/contaInvestimentoTipoAplicacao", urlTipoAplicacao1);
			criarAssociacao(urlConta4 + "/contaInvestimentoTipoAplicacao", urlTipoAplicacao2);
			
			criarAssociacao(urlConta1 + "/cliente", urlCliente1);
			criarAssociacao(urlConta2 + "/cliente", urlCliente2);
			criarAssociacao(urlConta3 + "/cliente", urlCliente1);
			criarAssociacao(urlConta4 + "/cliente", urlCliente2);
			
			// Come�a a usar o manipulador de contas ( � um controller do servidor que acessa os reposit�rios CRUD.
			// Por ora ele est� acessando somente o reposit�rio de clientes.
			executarOperacao("registraSaldoInicial", "nomeCliente=Rodrigo");
			executarOperacao("registraSaldoInicial", "nomeCliente=Tarcisio");
			
						
			// TODO
			/*ClienteAdapter clienteAdapter = new ClienteAdapterImpl(cliente1);
			System.out.println(clienteAdapter.getNomeClientePadrao());
			System.out.println("Endereco: " + cliente1.getEndereco() + "; Telefone: " + cliente1.getTelefone());*/
			
			executarOperacao("depositar", "codigoConta=1111-1&valor=51000.0");
			executarOperacao("aplicar", "codigoConta=3333-3&valor=10000.0");
			executarOperacao("aplicar", "codigoConta=4444-4&valor=20000.0");

			executarOperacao("transferir", "codigoContaOrigem=1111-1&codigoContaDestino=2222-2&valor=1100.0");
			
			executarOperacao("resgatar", "codigoConta=3333-3&valor=10000.0");
			executarOperacao("sacar", "codigoConta=2222-2&valor=1000.0");
			executarOperacao("resgatar", "codigoConta=4444-4&valor=20000.0");
			
			//TODO: realizar as opera��es do cart�o de cr�dito
			cargaCartaoCredito();
			executarOperacao("cartaoapp/debitaCartao", "numeroCartao=1111.1111.1111.1111&valorDebitar=100.0");
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
			/*Cliente cliente1 = new Cliente.ClienteBuilder("Rodrigo", "Possa")
				.setTelefone("319898-1212")
				.build()
				.adicionarConta(new ContaCorrente("1", 1000.0, 1000.0))
				.adicionarConta(new ContaInvestimento("2", 12000.0, new Poupanca(0.003)));*/
		
		
		
	/*	manipuladorConta.registerObserver(PortifolioObserver.getInstance());
		
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
		manipuladorConta.aplicar(contaInvestimentoCliente1, 10000.0);*/
	}

	private static void cargaCartaoCredito() throws IOException, InterruptedException {
		String urlCartaoCredito1 = criarObjeto(baseURL + "cartaoCreditoes", "{ \"limite\": \"1000.0\", \"saldo\": \"0.0\", \"codigoConta\": \"1111-1\", \"numeroCartao\": \"1111.1111.1111.1111\" }");
	}		
			
			
}
