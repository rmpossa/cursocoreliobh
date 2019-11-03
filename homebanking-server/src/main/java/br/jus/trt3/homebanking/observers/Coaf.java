package br.jus.trt3.homebanking.observers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;

import br.jus.trt3.homebanking.models.Conta;
import br.jus.trt3.homebanking.models.TipoOperacao;
import br.jus.trt3.homebanking.properties.AppProperties;

public class Coaf implements Observador{
	
	private AppProperties appProperties;
	
	private Coaf(){}
    
    private static class CoafHelper{
        private static final Coaf INSTANCE = new Coaf();
    }
    
    public static Coaf getInstance(){
        return CoafHelper.INSTANCE;
    }	
    
    public void setAppProperties(AppProperties appProperties) {
    	this.appProperties = appProperties;
    }

    @Override
	public void registraEvento(Conta conta, double valor, LocalDate data, TipoOperacao tipoOperacao) {
		System.out.println("COAF notificado");

        // Criando o HttpClient
        HttpClient client = HttpClient.newHttpClient();
        // String no formato Json que ir� conter o corpo da requisi��o POST
        String body = "{ \"nomeCliente\": \"" + conta.getCliente().getNomeCompleto() + "\", \"codigoConta\": \"" +
        		conta.getCodigoConta() + "\", \"valor\": \"" + valor + "\", \"data\": \"" + 
        		data.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "\" }";
        //Criando um HttpRequest do tipo Post, especificando sua URI e atribuindo ao m�todo Post o corpo da requisi��o
        
        String port = "";
        
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(body))
        		.uri(URI.create("http://"+appProperties.getHostnameGateway()+ ":8080" + "/auditorias")).header("Content-Type", "application/json").version(HttpClient.Version.HTTP_1_1).build();

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
