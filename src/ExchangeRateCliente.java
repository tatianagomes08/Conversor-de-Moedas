import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import modelos.ConversaoExchangeRate;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class ExchangeRateCliente {
    public Map<String, Double> consultaConversoes(String moeda) throws IOException, InterruptedException {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setPrettyPrinting()
                .create();

        String endereco = "https://v6.exchangerate-api.com/v6/86176ee28efd8467b2e9b472/latest/" + moeda;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        var json = response.body();
        ConversaoExchangeRate conversaoExchangeRate = gson.fromJson(json, ConversaoExchangeRate.class);

        return conversaoExchangeRate.conversion_rates();
    }
}
