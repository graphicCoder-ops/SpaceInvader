package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class Renderer extends JPanel implements ActionListener {
    final int WINDOW_WIDTH=800;
    final int WINDOW_HEIGHT=500;
    LinkedList<Bullet> bullets= new LinkedList<Bullet>();
    LinkedList<Enemy> Enemies = new LinkedList<Enemy>();
    LinkedList<enemyBullet> EnemyBullets = new LinkedList<enemyBullet>();
    Timer timer;
    Image playerImage, enemyImage;
    public static int playerx=20,playery=420;
    public static int score=0;
    public static int lives=3;
    public Frame frame;
    int counter=0;
    Renderer(){
        int xpos;
        int ypos=0;
        for(int i =0;i<3;i++) {
            ypos+=50;
            xpos=50;
            for (int j = 0; j < 7; j++) {
                Enemies.add(new Enemy(xpos, ypos));
                xpos += 100;
            }
        }

        this.setPreferredSize(new Dimension(WINDOW_WIDTH,WINDOW_HEIGHT));
        this.setBackground(Color.black);

        playerImage = new ImageIcon("src/main/Player.png").getImage();
        enemyImage = new ImageIcon("src/main/Enemy.jpg").getImage();
        //Timer that loops after every 15  ms
        timer =new Timer(15,this);
        timer.start();
    }

    public void paint(Graphics g){
        super.paint(g);
        Graphics2D graphics2D=(Graphics2D) g;
        if(score<420) {
            // Score
            graphics2D.setPaint(Color.white);
            graphics2D.drawString("Score: " + score, 20, 20);
            // Lives
            graphics2D.setPaint(Color.white);
            graphics2D.drawString("Lives: " + lives, 750, 20);
            // Bullets
            for (int i = 0; i < bullets.size(); i++) {
                Bullet bullet = bullets.get(i);
                if (bullet.update(Enemies)) {
                    graphics2D.fillRect(bullet.x, bullet.y, 3, 25);
                } else {
                    bullets.remove(i);

                }
            }
            // Enemy
            for (int i = 0; i < Enemies.size(); i++) {
                Enemy enemy = Enemies.get(i);
                graphics2D.drawImage(enemyImage, enemy.x, enemy.y, null);
            }
            // Player
            graphics2D.drawImage(playerImage, playerx, playery, null);

            // Fucking Bullets
            for(int i=0; i<EnemyBullets.size();i++){
                enemyBullet eb=EnemyBullets.get(i);
                if(eb.update()) {
                    graphics2D.setPaint(Color.red);
                    graphics2D.fillRect(eb.x, eb.y, 3, 25);
                }else {
                    EnemyBullets.remove(i);
                }
                }
        }
        else{
            graphics2D.drawString("You Won!",400,200);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(lives <=0){
            GameOver g= new GameOver();
            g.frame=g;
            setVisible(false);
            timer.stop();
            frame.dispose();
        }
        counter++;
        if(counter>10) {
            EnemyBullets.add(new enemyBullet((int) (Math.random() * 700 + 50), (int) (Math.random() * 150 + 50)));
            counter=0;
        }
    repaint();
    }

    public void fire(){
    bullets.add(new Bullet(playerx+25,playery-25));
    }
}

class Enemy{
    public int x,y;
    Enemy(int x, int y){
        this.x=x;
        this.y=y;
    }

}

class enemyBullet{
    public int x,y;
    enemyBullet(int x,int y){
        this.x=x;
        this.y=y;
    }

    boolean update(){
        this.y+=2;
        if(this.y>Renderer.playery){
            if(this.x >Renderer.playerx){
                if(this.x < Renderer.playerx+50){
                    Renderer.lives--;
                    return false;
                }
            }
        }
        if(y>500) {
            return false;
        }else{
            return true;
        }
    }
}

class Bullet{
    public int x,y;
    Bullet(int x,int y){
        this.x=x;
        this.y=y;
    }

    boolean update(LinkedList<Enemy> Enemies){
        this.y-=2;
        for(int i =0;i < Enemies.size();i++){
            Enemy enemy=Enemies.get(i);
            if(this.y < enemy.y+50){
                if(this.x >enemy.x){
                    if(this.x < enemy.x+100){
                        Enemies.remove(i);
                        Renderer.score+=20;
                        return false;
                    }
                }
            }
        }
        if(y<0){
            return false;
        }else {
            return true;
        }
    }

}
