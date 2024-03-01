package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOver extends JFrame {
    public JFrame frame;
    GameOver(){
        //JPanel jPanel=new JPanel();
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.setBackground(Color.black);
        buttonPanel.setSize(new Dimension(200,200));
        JLabel text=new JLabel("                      GameOver!");
        buttonPanel.add(text,BorderLayout.NORTH);
        text.setForeground(Color.white);

        text.setFont(new java.awt.Font("Wide Latin", 2, 36));
        Button btn =new Button("Restart!");
        btn.setFont(new java.awt.Font("Cursive", 1, 24));
        buttonPanel.add(btn,BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(buttonPanel,BorderLayout.CENTER );
        this.pack();
        this.setSize(new Dimension(800,500));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Restarting!");
                new Frame();
                frame.dispose();
            }
        });
    }
}
