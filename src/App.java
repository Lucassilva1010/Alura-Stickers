import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
       
        
            // 1- Fazer uma conexão Http para manipular os dados
            //ApI da MDB
                //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
            //API da NASA
                //String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY";
            String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/NASA-APOD.json";
            URI endereco =URI.create(url);
            HttpClient Client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(endereco).GET().build(); 
            HttpResponse<String> response = Client.send(request, BodyHandlers.ofString());
                String body = response.body();
               

            // 2 - Separar os dados vindos da API(titulo, poster, classificação)
                JsonParse parse = new JsonParse();
             List<Map<String, String>> listaDeFilmes = parse.parse(body);
              
            // Exibir e manipular os dados 
            var geradora = new GeradoraDeFiguras();
            for (int i=0; i<listaDeFilmes.size();i++) {
                Map<String,String> filme = listaDeFilmes.get(i);

                var urlImagem =
                    //filme.get("image") // Esse é usado para pegar o do MDB, esse são as referencias dos LINK
                   filme.get("url")// Esse usa as referencias da API da nasa e Busca as Imagens
                    .replaceAll("(@+)(.*).jpg$", "$1.jpg");

                var titulo = filme.get("title");

                InputStream inputurl = new URL(urlImagem).openStream();
                String nomeArquivo = "imagens/"+titulo.replace(":", "-")+".png";
                              
                geradora.Cira(inputurl,nomeArquivo);

                System.out.println(titulo);
                System.out.println();
 


                
                   /*  System.out.println("\u001b[1m TOP: \u001b[m"+filme.get("rank"));
                    System.out.println("\u001b[1m Titulo: \u001b[m"+filme.get("title"));
                    System.out.println("\u001b[1m Subtitulo: \u001b[m"+filme.get("fullTitle"));
                    System.out.println("\u001b[1m Ano do Filme: \u001b[m"+filme.get("year"));
                    System.out.println("\u001b[1m Imagem: \u001b[m"+filme.get("image"));
                    System.out.println("\u001b[1m Atores: \u001b[m"+filme.get("crew"));
                    System.out.println("\u001b[1m Classificação: \u001b[m"+filme.get("imDbRating"));
                    System.out.println("\u001b[1m Numero de pessoas que votaram: \u001b[m"+filme.get("imDbRatingCount"));
                    System.out.println();*/
                } 
            }  

    }

