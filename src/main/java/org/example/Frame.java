package org.example;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Frame extends JFrame {
    Renderer panel;

    Frame(){
        panel=new Renderer();
        panel.lives=3;
        panel.score=0;
        panel.frame=this;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_A){
                    panel.playerx-=20;
                }
                if(e.getKeyCode()==KeyEvent.VK_D){
                    panel.playerx+=20;
                }
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    panel.fire();
                }
            }
        });
    }

}
