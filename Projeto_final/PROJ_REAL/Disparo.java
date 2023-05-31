import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Disparo {
    
    public int posX;
    public int posY;
    public int velY;
    public int altura;
    public int largura;
    public BufferedImage disparo;
    boolean active;

    public Disparo(){

        largura = 1;
        altura = 5;
        posX = 0;
        posY = 0;
        velY = -15;
        active = true;
        try{
            disparo = ImageIO.read(getClass().getResource("Space_Invaders_Bullet.png"));
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
