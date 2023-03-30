import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoNasa implements ExtratorDeConteudo {
    
    public List<Conteudo>ExtraiConteudos(String json){

        // 2 - Separar os dados vindos da API(titulo, poster, classificação)
        JsonParse parse = new JsonParse();
        List<Map<String, String>> listaDeAtributos = parse.parse(json);
        
        List<Conteudo> conteudos = new ArrayList<>();


//popular a lista

        for (Map<String, String>atributos: listaDeAtributos) {
            String titulo = atributos.get("title").replaceAll("(@+)(.*).jpg$", "$1.jpg");
            
            String urlImagem = atributos.get("url")
            .replaceAll("(@+)(.*).jpg$", "$1.jpg");

            Conteudo conteudo = new Conteudo(titulo , urlImagem);
           
            conteudos.add(conteudo);

        }
        
        return conteudos;
    }

}
