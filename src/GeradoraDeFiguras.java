import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradoraDeFiguras {
    
    public void Cira(InputStream input,String nomeArquivo ) throws IOException{
       //leitura da imagem

       //Buscando imagem em qualquer URL (Lembrar de passar a variavel no READ)
           // InputStream inputurl = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_4.jpg").openStream();
       //Buscando qualquele tipo de arquivo (lembrar de passa a variavel no parametro do READ)
            //InputStream inputStram = new FileInputStream("imagens/filme.jpg");
       //Buscando a imagem e um arquivo especifico
            BufferedImage imagemOriginal = ImageIO.read(input);
       
       //criar nova imamegem em memoria com transpareência e com tamanho
            int largura = imagemOriginal.getWidth();
            int altura = imagemOriginal.getHeight();
            int novaAltura = altura+200;
            BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);
          

       // copiar a imagem original para nova imagem (em memoria)
       Graphics2D graphics = (Graphics2D)novaImagem.getGraphics();
       graphics.drawImage(imagemOriginal, null, 0, 0 );

        //configurar o tamanho e cor da fonte
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 100);
        graphics.setColor(Color.BLUE);
       
        graphics.setFont(fonte);
       
       // escrever uma frase na nova imagem
        graphics.drawString("TopFilmes", 0  , novaAltura-100);

       
       //escrever a nova umagem em um arquivo
       ImageIO.write(novaImagem, "png", new File(nomeArquivo));
                //Quando for usar imagens local
       ImageIO.write(novaImagem, "png", new File("imagens/figurinha-2.png"));
    }
    
}
