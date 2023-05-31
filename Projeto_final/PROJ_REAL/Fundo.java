import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Fundo {
    
    public BufferedImage fundo1;
    public BufferedImage fundo2;
    public int posX;
    public int posY;
    public int velY;

    public Fundo() {

        posX = 0;
        posY = 0;

        try{
            fundo1 = ImageIO.read(getClass().getResource("Space_Invaders_Background.png"));
            fundo2 = ImageIO.read(getClass().getResource("Space_Invaders_Background.png"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
