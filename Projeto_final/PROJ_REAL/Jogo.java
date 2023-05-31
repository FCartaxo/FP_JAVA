//import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
//import java.awt.Color;
//import java.awt.DefaultFocusTraversalPolicy;
//import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//import javax.swing.JFrame;
import javax.swing.JPanel;

public class Jogo extends JPanel{
    
    boolean seta_direita;
    boolean seta_esquerda;
    boolean espaco;
    boolean disparar;
    Fundo fundo1;
    Fundo fundo2;
    Aliens [][] listaAlien = new Aliens [6][8];
    Spaceship nave;
    Disparo disparo;

    public Jogo() {

        int x = 165;   //posição x inicial do primeiro alien
        int y = 20;    //posição y inicial da primeira clonua de aliens
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 8; j++){
                Aliens alien = new Aliens();
                alien.posX = x;
                alien.posY = y;
                listaAlien[i][j] = alien;
                x += 60;  //cada alien é separado por 60 pixeis
            }
            x = 165;
            y += 45;   //cada coluna de aliens é seperada por 45 pixeis
        }
        addKeyListener(new KeyListener() {
            
            public void keyTyped(KeyEvent e) {
                
            }

            public void keyReleased(KeyEvent e){
                switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    seta_esquerda = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    seta_direita = false;
                    break;
                case KeyEvent.VK_SPACE:
                    espaco = false;
                    break;
                }
            }

            public void keyPressed(KeyEvent e){
                switch(e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    seta_esquerda = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    seta_direita = true;
                    break;
                case KeyEvent.VK_SPACE:
                    espaco = true;
                    break;
                }
            }

        });

        fundo2 = new Fundo();
        fundo1 = new Fundo();
        nave = new Spaceship();
        disparo = new Disparo();
        fundo1.posX = 0;
        fundo1.posY = 0;
        fundo2.posX = 0;
        fundo2.posY = -1200;
        setFocusable(true);
        setLayout(null);
        new Thread(new Runnable() {
            
            public void run(){
                gameloop();
            }
        }).start();
    }

    public void gameloop(){

        while(true) {

            handlerEvents();
            update();
            render();

            try{
                Thread.sleep(17);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    public void handlerEvents(){

        nave.velX = 0;
        if(seta_esquerda==true){
            nave.velX = -5;
        }else if(seta_direita==true){
            nave.velX = 5;
        }
        if (espaco == true && disparar == true){
            disparar = true;
            disparo.posX = nave.posX + (nave.largura/2);
            disparo.posY = nave.posY;
            disparo.active = true;
            try{
                disparo.disparo = ImageIO.read(getClass().getResource("Space_Invaders_Bullet.png"));
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void update(){

        if(fundo1.posY == 1200){
            fundo1.posY = -1200;
        }
        if(fundo2.posY == 1200){
            fundo2.posY = -1200;
        }
        fundo1.posY += 8;
        fundo2.posY += 8;
        nave.posX += nave.velX;
        if(disparar == true){
            disparo.posY += disparo.velY; 
        }
        for(int i = 0;i < 6;i++){
            for(int j = 0;j < 8;j++){
                listaAlien[i][j].posX += listaAlien[i][j].velX;
                }
            }
        testeColisao();
    }

    public void render(){
        repaint();
    }

    public void testeColisao(){
        if(nave.posX+(nave.largura) > Tela.LARGURA_TELA || nave.posX <0){
            nave.posX -= nave.velX;
        }
        for(int i = 0;i < 6;i++){
            for(int j = 0;j < 8;j++){
                Aliens atual = listaAlien[i][j];
                if(atual.isVisible == false){
                    continue;
                }
                if(disparo.posX <= atual.posX + atual.largura && disparo.posX <= atual.posX && disparo.posY <= atual.posY + atual.altura && disparo.posY <= atual.posY && disparo.active == true){

                    atual.isVisible = false;
                    atual.inimigo = null;
                    disparo.active = false;
                    disparo.disparo = null;
                    disparar = false;
                    }
                }
            }
        for(int i = 0;i < 6;i++){
            for(int j = 0;j < 8;j++){
                Aliens atual = listaAlien[i][j];
                if (atual.posX + atual.largura > Tela.LARGURA_TELA){
                    atual.posY += atual.altura;
                    atual.velX *= -1.2;
                }
                if(atual.posX <= 0){
                    atual.posY += listaAlien[i][j].altura;
                    atual.velX *= -1.2;
                }
            }
            }
        if(disparar == true && disparo.posY < 0){
            disparar = false;
        }
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(fundo1.fundo1, fundo1.posX, fundo1.posY ,null);
        g.drawImage(fundo2.fundo1, fundo2.posX, fundo2.posY ,null);
        for(int i = 0;i < 6;i++){
            for(int j = 0;j < 8;j++){
                g.drawImage(listaAlien[i][j].inimigo, listaAlien[i][j].posX, listaAlien[i][j].posY, null);
                }
            }
        g.drawImage(nave.ship, nave.posX, nave.posY, null);
        if(disparar == true){
            g.drawImage(disparo.disparo, disparo.posX, disparo.posY, null);
        }
    }
}
