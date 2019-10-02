package laboratorioDia02;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PrincipalRest {
	
	public static int criarObjeto(String url, String json) throws IOException, InterruptedException {
		 HttpClient client = HttpClient.newHttpClient();
        
		 // String no formato Json que irá conter o corpo da requisição POST
        String body = json;

        //Criando um HttpRequest do tipo Post, especificando sua URI e atribuindo ao método Post o corpo da requisição
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .uri(URI.create(url)).header("Content-Type", "application/json").version(HttpClient.Version.HTTP_1_1).build();

        // Enviando a requisição e recebendo o Objeto de resposta da mesma.
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        return response.statusCode();
	}
	
	public static int criarAssociacao(String url, String objetoPai) throws IOException, InterruptedException {
		
		HttpClient client = HttpClient.newHttpClient();
		 
       // String no formato Json que irá conter o corpo da requisição POST
       String body = objetoPai; // "http://localhost:8080/libraries/1"
       
       //Criando um HttpRequest do tipo Post, especificando sua URI e atribuindo ao método Post o corpo da requisição
       HttpRequest request = HttpRequest.newBuilder()
               .PUT(HttpRequest.BodyPublishers.ofString(body))
               .uri(URI.create(url)).header("Content-Type", "text/uri-list").version(HttpClient.Version.HTTP_1_1).build();

       // Enviando a requisição e recebendo o Objeto de resposta da mesma.
       HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
       
       return response.statusCode();
	}
	
	
	
	public static void main(String[] args) {
		try {
			
			criarObjeto("http://localhost:8080/clientes", "{ \"nome\": \"Rodrigo\", \"sobrenome\": \"Possa\", \"telefone\": \"12323-0998\"}");
			criarObjeto("http://localhost:8080/clientes", "{ \"nome\": \"Tarcisio\", \"sobrenome\": \"Brandao\", \"endereco\": \"Av. A, N 1 - Serra\"}");
			
			criarObjeto("http://localhost:8080/contaCorrentes", "{\"saldo\": 1000.0, \"limiteCredito\": 1000.0}");
			criarObjeto("http://localhost:8080/contaCorrentes", "{\"saldo\": 1500.0, \"limiteCredito\": 1500.0}");
			
			
			criarObjeto("http://localhost:8080/contaInvestimentoes", "{\"saldo\": 12000.0}");
			criarObjeto("http://localhost:8080/contaInvestimentoes", "{\"saldo\": 2000.0}");
			
			criarObjeto("http://localhost:8080/poupancas", "{\"taxa\": 0.003}");
			criarObjeto("http://localhost:8080/cDBs", "{\"taxa\": 0.01}, \"percentual\":1.0}");
		
			criarAssociacao("http://localhost:8080/contaInvestimentoes/3/contaInvestimentoTipoAplicacao", "http://localhost:8080/poupancas/1");
			criarAssociacao("http://localhost:8080/contaInvestimentoes/4/contaInvestimentoTipoAplicacao", "http://localhost:8080/cDBs/2");
			
			
			criarAssociacao("http://localhost:8080/contas/1/cliente", "http://localhost:8080/clientes/1");
			criarAssociacao("http://localhost:8080/contas/2/cliente", "http://localhost:8080/clientes/2");
			criarAssociacao("http://localhost:8080/contas/3/cliente", "http://localhost:8080/clientes/1");
			criarAssociacao("http://localhost:8080/contas/4/cliente", "http://localhost:8080/clientes/2");
			
			
			/*.adicionarConta(new ContaCorrente("1", 1000.0, 1000.0))
			.adicionarConta(new ContaInvestimento("2", 12000.0, new Poupanca(0.003)));
			
			 ContaCorrente(Long id, double saldoInicial, double limiteCredito)*/
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
