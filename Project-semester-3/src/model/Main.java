package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class Main {

    int fps = 60;
    JFrame frame;
    int gameHeight= 400;
    int gameWidth = 600;
    
    Graphics g;
    BufferedImage i;
    
    public static void main(String[] args) {
        new Main();
    }
    
    public Main(){
        run();
        System.exit(-1);
    }
    
    void run(){
        init();
        
        while(true){
            long time = System.currentTimeMillis();
            
            update();
            draw();
            
            time = (1000 / fps) - (System.currentTimeMillis() - time);
            
            if(time > 0){
                try {
                    Thread.sleep(time);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    void init(){
        frame = new JFrame("Crazy Scientist Game");
        frame.setSize(new Dimension(gameWidth,gameHeight));
        //frame.setLocation(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    void update(){
    
    }
    
    void draw(){
        

        g.setColor(Color.black);
        g.fillRect(0, 0, gameWidth, gameHeight);
        
        g.setColor(Color.CYAN);
        g.fillOval(250, 250, 50, 50);
    }
    
}
