package br.jus.trt3.observers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.jus.trt3.models.Conta;
import br.jus.trt3.models.TipoOperacao;

public class Coaf implements Observador{

	
	private Coaf(){}
    
    private static class CoafHelper{
        private static final Coaf INSTANCE = new Coaf();
    }
    
    public static Coaf getInstance(){
        return CoafHelper.INSTANCE;
    }	

    @Override
	public void registraEvento(Conta conta, double valor, LocalDate data, TipoOperacao tipoOperacao) {
		System.out.println("COAF notificado");

        // Criando o HttpClient
        HttpClient client = HttpClient.newHttpClient();
        // String no formato Json que ir� conter o corpo da requisi��o POST
        String body = "{ \"cliente\": \"" + conta.getCliente().getNomeCompleto() + "\", \"idConta\": \"" +
        		conta.getCodigoConta() + "\", \"valor\": \"" + valor + "\", \"data\": \"" + 
        		data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\" }";
        //Criando um HttpRequest do tipo Post, especificando sua URI e atribuindo ao m�todo Post o corpo da requisi��o
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
//                .uri(URI.create("http://localhost:3000/auditorias")).header("Content-Type", "application/json").build();
        		.uri(URI.create("http://localhost:3000/auditorias")).header("Content-Type", "application/json").version(HttpClient.Version.HTTP_1_1).build();

        // Enviando a requisição recebendo o Objeto de resposta da mesma.
        HttpResponse<String> response;
		try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
	        // Extraindo status de resposta da requisi��o Post
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
