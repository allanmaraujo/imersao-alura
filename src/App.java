import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
   
    public static void main(String[] args) throws Exception {

        // conexão HTTP top 250 filmes
        // String url = "https://imdb-api.com/en/API/Top250Movies/k_0ojt0yvm";
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // titulo, poster e classificação
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        
        // exibir e manipular dados
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println("Título: " + filme.get("title"));
            System.out.println("Poster: " + filme.get("image"));
            System.out.println("\u001b[30m\u001b[47m Classificação: \u001b[m" + "\u001b[30m\u001b[47m" + filme.get("imDbRating") + " \u001b[m");
            double Classificação = Double.parseDouble(filme.get("imDbRating"));
            int numEstrelas = (int)Classificação;
            for (int i = 0; i < numEstrelas ; i++) {
                System.out.print("⭐");
            }
            System.out.println();
        }
    }
}
