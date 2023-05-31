import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Imagem extends JFrame{

    ImageIcon imagem = new ImageIcon(getClass().getResource("Space_Invaders_Ship.png"));
    JLabel label = new JLabel(imagem);

    public Imagem() {
    
        add(label);

        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Imagem();    
    }

}


