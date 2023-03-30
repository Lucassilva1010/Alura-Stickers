import java.io.InputStream;
import java.net.URL;
import java.util.List;


public class App {
    public static void main(String[] args) throws Exception {
       
            // 1- Fazer uma conexão Http para manipular os dados
            //ApI da MDB
            String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";
            ExtratorDeConteudo extrator = new ExtratorDeConteudoImdb();    
            //API da NASA
            //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/NASA-APOD.json";
            //ExtratorDeConteudo extrator = new ExtratorDeConteudoNasa();
           
            var http = new ClienteHttp();
            
            String json = http.buscaDados(url);
               
            // Exibir e manipular os dados 
            
            List<Conteudo> conteudos = extrator.ExtraiConteudos(json);

            var geradora = new GeradoraDeFiguras();
            
            for (int i=0; i<conteudos.size();i++) {
               Conteudo conteudo = conteudos.get(i);

                InputStream inputurl = new URL(conteudo.getUrlImagem()).openStream();
                String nomeArquivo = "imagens/"+conteudo.getTiulo()+".png";
                              
                geradora.Cira(inputurl,nomeArquivo);

                System.out.println(conteudo.getTiulo());
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

