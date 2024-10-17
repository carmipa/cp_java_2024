package br.com.fiap.ExternalInterface;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import br.com.fiap.model.Enderecos;
import com.google.gson.Gson;

public class Cep {

    private static final String VIACEP_URL = "https://viacep.com.br/ws/";

    // Método para buscar o endereço com base no CEP informado
    public static Enderecos buscarEnderecoPorCep(String cep) throws IOException, InterruptedException {
        // Cria a URL da API ViaCEP
        String url = VIACEP_URL + cep + "/json/";

        // Cria o cliente HTTP
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        // Faz a requisição e obtém a resposta
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Converte a resposta JSON para um objeto de Endereço
        return parseEnderecos(response.body());
    }

    // Método para converter a resposta JSON para um objeto de Endereço usando Gson
    private static Enderecos parseEnderecos(String responseBody) {
        Gson gson = new Gson();
        Enderecos enderecos = gson.fromJson(responseBody, Enderecos.class);

        // Verifica se o CEP retornou erro
        if (enderecos.getLogradouro() == null) {
            System.out.println("CEP não encontrado.");
            return null;
        }

        return enderecos;
    }
}
