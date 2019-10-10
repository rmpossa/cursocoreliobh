package br.jus.trt3.cliente;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Principal {
	private static final String baseURL= "http://localhost:8080/";
	
	public static void executarOperacao(String operacao, String parametros) throws IOException, InterruptedException {
	   HttpClient client = HttpClient.newHttpClient();
        
		 // String no formato Json que irá conter o corpo da requisição POST
       

       //Criando um HttpRequest do tipo Post, especificando sua URI e atribuindo ao método Post o corpo da requisição
       HttpRequest request = HttpRequest.newBuilder()
               .GET()
               .uri(URI.create(baseURL + operacao+ "?" + parametros)).version(HttpClient.Version.HTTP_1_1).build();

       // Enviando a requisição e recebendo o Objeto de resposta da mesma.
       HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
       
	}
      
	public static String criarObjeto(String url, String json) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
        
		 // String no formato Json que irá conter o corpo da requisição POST
        String body = json;

        //Criando um HttpRequest do tipo Post, especificando sua URI e atribuindo ao método Post o corpo da requisição
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .uri(URI.create(url)).header("Content-Type", "application/json").version(HttpClient.Version.HTTP_1_1).build();

        // Enviando a requisição e recebendo o Objeto de resposta da mesma.
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        return response.headers().firstValue("location").get();
	}
	
	public static int criarAssociacao(String url, String objetoPai) throws IOException, InterruptedException {
		
		HttpClient client = HttpClient.newHttpClient();
		 
       // String no formato Json que irá conter o corpo da requisição POST
       String body = objetoPai; // "http://localhost:8080/libraries/1"
       
       //Criando um HttpRequest do tipo PUT, especificando sua URI e atribuindo ao método PUT o corpo da requisição
       HttpRequest request = HttpRequest.newBuilder()
               .PUT(HttpRequest.BodyPublishers.ofString(body))
               .uri(URI.create(url)).header("Content-Type", "text/uri-list").version(HttpClient.Version.HTTP_1_1).build();

       // Enviando a requisição e recebendo o Objeto de resposta da mesma.
       HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
       
       return response.statusCode();
	}
	
	
	
	public static void main(String[] args) {
		try {
			
			String urlCliente1 = criarObjeto("http://localhost:8080/clientes", "{ \"nome\": \"Rodrigo\", \"sobrenome\": \"Possa\", \"telefone\": \"12323-0998\"}");
			String urlCliente2 = criarObjeto("http://localhost:8080/clientes", "{ \"nome\": \"Tarcisio\", \"sobrenome\": \"Brandao\", \"endereco\": \"Av. A, N 1 - Serra\"}");
			
			String urlConta1 = criarObjeto("http://localhost:8080/contaCorrentes", "{\"codigoConta\":\"1111-1\",\"saldo\": 1000.0, \"limiteCredito\": 1000.0}");
			String urlConta2 = criarObjeto("http://localhost:8080/contaCorrentes", "{\"codigoConta\":\"2222-2\",\"saldo\": 800.0, \"limiteCredito\": 1000.0}");
			
			
			String urlConta3 = criarObjeto("http://localhost:8080/contaInvestimentoes", "{\"codigoConta\":\"3333-3\",\"saldo\": 12000.0}");
			String urlConta4 = criarObjeto("http://localhost:8080/contaInvestimentoes", "{\"codigoConta\":\"4444-4\",\"saldo\": 20000.0}");
			
			String urlTipoAplicacao1 = criarObjeto("http://localhost:8080/poupancas", "{\"taxa\": 0.003}");
			String urlTipoAplicacao2 = criarObjeto("http://localhost:8080/cDBs", "{\"taxa\": 0.01}, \"percentual\":1.0}");
		
			criarAssociacao(urlConta3 + "/contaInvestimentoTipoAplicacao", urlTipoAplicacao1);
			criarAssociacao(urlConta4 + "/contaInvestimentoTipoAplicacao", urlTipoAplicacao2);
			
			criarAssociacao(urlConta1 + "/cliente", urlCliente1);
			criarAssociacao(urlConta2 + "/cliente", urlCliente2);
			criarAssociacao(urlConta3 + "/cliente", urlCliente1);
			criarAssociacao(urlConta4 + "/cliente", urlCliente2);
			
			// Começa a usar o manipulador de contas ( é um controller do servidor que acessa os repositórios CRUD.
			// Por ora ele está acessando somente o repositório de clientes.
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
			
			
}
