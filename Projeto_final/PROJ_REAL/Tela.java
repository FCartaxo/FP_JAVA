import java.awt.Dimension;
import javax.swing.JFrame;

public class Tela{

    public static final int LARGURA_TELA = 1100;
    public static final int ALTURA_TELA = 600;

    public Tela(){

        JFrame janela = new JFrame("Space Invaders");
        Jogo jogo = new Jogo();
        jogo.setPreferredSize(new Dimension(LARGURA_TELA, ALTURA_TELA));
        janela.getContentPane().add(jogo);
        janela.setResizable(false);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocation(100, 100);
        janela.setVisible(true);
        janela.pack();
    }

    public static void main(String[] args) {
        new Tela();
    }
}