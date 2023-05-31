package PROJ_TESTE;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//import javax.swing.JLabel;
//import javax.swing.ImageIcon;

public class SpaceInvadersTest extends JFrame {
    
    private final int WIDTH = 1500;     //dimensões da janela
    private final int HEIGHT = 800;

    private SpaceShip spaceShip;
    private Alien alien;
    private Timer timer;

    public SpaceInvadersTest() {
        setTitle("Space Invaders");
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        spaceShip = new SpaceShip();
        alien = new Alien();

        timer = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                spaceShip.update();
                alien.update();
                repaint();
            }
        });

        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                spaceShip.keyPressed(e);
            }

            public void keyReleased(KeyEvent e) {
                spaceShip.keyReleased(e);
            }
        });

        setFocusable(true);
        requestFocusInWindow();

        setVisible(true);
        timer.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        spaceShip.draw(g2d);
        alien.draw(g2d);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SpaceInvadersTest();
            }
        });
    }

    private class SpaceShip {

        private int x;
        private int y;
        private int speed;

        private boolean leftPressed;
        private boolean rightPressed;

        public SpaceShip() {
            x = WIDTH / 2;
            y = HEIGHT - 50;
            speed = 7;
        }

        public void update() {
            if (leftPressed && x > 0) {
                x -= speed;
            }

            if (rightPressed && x < WIDTH - 50) {
                x += speed;
            }
        }

        public void draw(Graphics2D g2d) {
            g2d.setColor(Color.BLUE);
            g2d.fillRect(x, y, 50, 30);
        }

        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                leftPressed = true;
            }

            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                rightPressed = true;
            }
        }

        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                leftPressed = false;
            }

            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                rightPressed = false;
            }
        }
    }

    private class Alien {

        private int x;
        private int y;
        private int speed;

        public Alien() {
            x = WIDTH / 2;
            y = 50;
            speed = 2;
        }

        public void update() {
            if (x < 0 || x > WIDTH - 50) {
                speed = -speed;
            }

            x += speed;
        }

        public void draw(Graphics2D g2d) {
            g2d.setColor(Color.GREEN);
            g2d.fillRect(x, y, 50, 30);
        }
    }
}
