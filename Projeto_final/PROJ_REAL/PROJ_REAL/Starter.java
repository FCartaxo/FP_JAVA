package PROJ_REAL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Starter extends JFrame {
    
    public Starter()
    {
        add(new Board());
        setTitle("Space Invaders");                  //Dá um nome à janela do jogo
        setDefaultCloseOperation(EXIT_ON_CLOSE);  //Fecha a janela quando se clica no X
        setExtendedState(MAXIMIZED_BOTH);          //Dimensões da tela
        setLocationRelativeTo(null);            //Inicia no meio da tela
        setVisible(true);                       //Faz o objeto aparecer
        setResizable(false);  //Permite não redimensionar a tela
        setBackground(Color.BLACK);

        SpaceShip spaceship = new SpaceShip();
    }

    public class SpaceShip{

        ImageIcon imagem = new ImageIcon(getClass().getResource("Space_Invaders_Ship.png"));
        JLabel label = new JLabel(imagem);
    
        public SpaceShip() {
        
            add(label);
    
            setExtendedState(MAXIMIZED_BOTH);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setResizable(false);
            setVisible(true);
        }
    }
        

    private void add(Board board)
    {
        
    }

    public static void main(String[] args)
    {
        new Starter();
    }
}
