package laboratorioDia02;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Coaf implements Observador{
//	Notice the private inner static class that contains the instance of the singleton class. 
//	When the singleton class is loaded, SingletonHelper class is not loaded into memory and 
//	only when someone calls the getInstance method, this class gets loaded and creates the 
//	Singleton class instance. This is the most widely used approach for Singleton class as 
//	it doesn’t require synchronization.
	
	private Coaf(){}
    
    private static class CoafHelper{
        private static final Coaf INSTANCE = new Coaf();
    }
    
    public static Coaf getInstance(){
        return CoafHelper.INSTANCE;
    }	

    public static void requisicaoPostSincrona() throws IOException, InterruptedException {
        // Criando o HttpClient
        HttpClient client = HttpClient.newHttpClient();
        // String no formato Json que irá conter o corpo da requisição POST
        String body = "{ \"title\": \"Livro 3\", \"author\": \"Jose\"  }";
        //Criando um HttpRequest do tipo Post, especificando sua URI e atribuindo ao método Post o corpo da requisição
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .uri(URI.create("http://localhost:3000/posts")).header("Content-Type", "application/json").build();

        // Enviando a requisição e recebendo o Objeto de resposta da mesma.
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        // Extraindo status de resposta da requisição Post
        int statusCode = response.statusCode();
        // Imprimindo resultado no console
        System.out.println(String.format("Status code: %s", statusCode));
    }

	@Override
	public void registraEvento(Conta conta, double valor, LocalDate data, TipoMovimentacao tipoMovimentacao) {
		System.out.println("COAF notificado");

        // Criando o HttpClient
        HttpClient client = HttpClient.newHttpClient();
        // String no formato Json que irá conter o corpo da requisição POST
        String body = "{ \"cliente\": \"" + conta.getCliente().getNomeCompleto() + "\", \"idConta\": \"" +
        		conta.getId() + "\", \"valor\": \"" + valor + "\", \"data\": \"" + 
        		data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\" }";
        //Criando um HttpRequest do tipo Post, especificando sua URI e atribuindo ao método Post o corpo da requisição
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .uri(URI.create("http://localhost:3000/auditorias")).header("Content-Type", "application/json").build();

        // Enviando a requisição e recebendo o Objeto de resposta da mesma.
        HttpResponse<String> response;
		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
	        // Extraindo status de resposta da requisição Post
	        int statusCode = response.statusCode();
	        // Imprimindo resultado no console
	        System.out.println(String.format("Status code: %s", statusCode));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
