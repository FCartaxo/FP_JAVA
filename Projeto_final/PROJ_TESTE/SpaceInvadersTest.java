import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.swing.Timer;

public class SpaceInvadersTest extends JPanel implements KeyListener, ActionListener {
    private int playerX = 600;
    private List<Enemy> enemies;
    private boolean isGameOver = false;
    private boolean isFiring = false;
    private List<Missile> missiles;
    private Timer timer;
    private Timer missileTimer;
    private int level = 1;
    private Random random;
    private int maxEnemies = 6;
    private double enemySpeed = 1.00;
    private int score = 0;
    private boolean isPaused = false;
    private JButton pauseButton;

    public SpaceInvadersTest() {
        setPreferredSize(new Dimension(1500, 800));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        enemies = new ArrayList<>();
        missiles = new ArrayList<>();
        random = new Random();

        timer = new Timer(5, this);
        timer.start();

        missileTimer = new Timer(500, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isFiring && missiles.size() < 10) {
                    missiles.add(new Missile(playerX + 50, 770));
                }
            }
        });
        missileTimer.start();

        createEnemies();

        pauseButton = new JButton("Pause");
        pauseButton.setFocusable(false);
        pauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isGameOver) {
                    return;
                }
                isPaused = !isPaused;
                if (isPaused) {
                    pauseButton.setText("Resume");
                    timer.stop();
                    missileTimer.stop();
                } else {
                    pauseButton.setText("Pause");
                    timer.start();
                    missileTimer.start();
                }
            }
        });
        add(pauseButton);
    }

    private void createEnemies() {
        enemies.clear();
        int numEnemies;

        if (level <= 4) {
            numEnemies = 1;
        } else {
            numEnemies = random.nextInt(maxEnemies) + 1;
        }

        int startX = 50;
        int startY = 50;
        int spacingX = 150;
        int spacingY = 80;

        int enemyCount = 0;

        for (int i = 0; i < numEnemies; i++) {
            int x = startX + i * spacingX;
            int y = startY;
            if (enemyCount >= 7) {
                y += spacingY;
            }
            enemies.add(new Enemy(x, y));
            enemyCount++;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        if (isGameOver) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            String gameOverMsg = "Game Over";
            g.drawString(gameOverMsg, getWidth() / 2 - g.getFontMetrics().stringWidth(gameOverMsg) / 2, getHeight() / 2);
        } else {
            g.setColor(Color.GREEN);
            g.fillRect(playerX, 770, 100, 20);

            for (Enemy enemy : enemies) {
                g.setColor(Color.RED);
                g.fillRect(enemy.getX(), enemy.getY(), 40, 20);
            }

            for (Missile missile : missiles) {
                g.setColor(Color.YELLOW);
                g.fillRect(missile.getX(), missile.getY(), 4, 20);
            }

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 16));
            String levelMsg = "Level: " + level;
            g.drawString(levelMsg, 10, getHeight() - 30);
        }
    }

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_LEFT) {
            if (playerX > 0) {
                playerX -= 10;
            }
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            if (playerX < 1400) {
                playerX += 10;
            }
        } else if (keyCode == KeyEvent.VK_SPACE) {
            isFiring = true;
        }

        repaint();
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            isFiring = false;
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (isGameOver || isPaused) {
            return;
        }

        for (Enemy enemy : enemies) {
            enemy.update();

            if (enemy.getY() >= 770) {
                isGameOver = true;
                break;
            }
        }

        if (isGameOver) {
            return;
        }

        if (isFiring && missiles.size() >= 10) {
            isFiring = false;
        }

        Iterator<Missile> missileIterator = missiles.iterator();
        while (missileIterator.hasNext()) {
            Missile missile = missileIterator.next();
            missile.update();

            if (missile.getY() < 0) {
                missileIterator.remove();
            } else {
                Iterator<Enemy> enemyIterator = enemies.iterator();
                while (enemyIterator.hasNext()) {
                    Enemy enemy = enemyIterator.next();
                    if (missile.getX() >= enemy.getX() && missile.getX() <= enemy.getX() + 40 &&
                            missile.getY() <= enemy.getY() + 20) {
                       
                                enemyIterator.remove();
                                missileIterator.remove();
                                score++;
                                break;
                            }
                        }
                    }
                }
        
                if (enemies.isEmpty()) {
                    level++;
                    if (level % 6 == 0) {
                        maxEnemies++;
                    }
                    enemySpeed += 0.15;
                    createEnemies();
                }
        
                repaint();
            }
        
            public static void main(String[] args) {
                JFrame frame = new JFrame("Space Invaders");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
                SpaceInvadersTest game = new SpaceInvadersTest();
                frame.add(game);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        
            private class Enemy {
                private int x;
                private int y;
                private boolean moveRight = true;
        
                public Enemy(int x, int y) {
                    this.x = x;
                    this.y = y;
                }
        
                public int getX() {
                    return x;
                }
        
                public int getY() {
                    return y;
                }
        
                public void update() {
                    if (moveRight) {
                        x += enemySpeed;
                    } else {
                        x -= enemySpeed;
                    }
        
                    if (x >= 1420 || x <= 0) {
                        y += 40;
                        moveRight = !moveRight;
                    }
                }
            }
        
            private class Missile {
                private int x;
                private int y;
        
                public Missile(int x, int y) {
                    this.x = x;
                    this.y = y;
                }
        
                public int getX() {
                    return x;
                }
        
                public int getY() {
                    return y;
                }
        
                public void update() {
                    y -= 7;
                }
            }
}