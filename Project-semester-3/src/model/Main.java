package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class Main {

    int fps = 60;
    int gameHeight= 400;
    int gameWidth = 600;
    
    Character character;
    InputHandler handler;
    
    JFrame frame;
    Graphics g;
    Graphics g2;
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
        
        handler = new InputHandler(frame);
        character = new Character();
        //graphic stuff
        
        i = new BufferedImage(gameWidth,gameHeight, BufferedImage.TYPE_INT_RGB);
        g = i.getGraphics();
        g2 = frame.getGraphics();
        
        
    }
    
    void update(){
        if(handler.isKeyDown(KeyEvent.VK_RIGHT)){
            character.moveRight();
        }
        if(handler.isKeyDown(KeyEvent.VK_LEFT)){
            character.moveLeft();
        }
        if(handler.isKeyDown(KeyEvent.VK_UP)){
            character.moveUp();
        }
        if(handler.isKeyDown(KeyEvent.VK_DOWN)){
            character.moveDown();
        }        
        if(handler.isMouseDown(1)){
            System.out.println(handler.getEvent(1).getX());
            System.out.println(handler.getEvent(1).getY());
        }
    }
    
    void draw(){
        

        g.setColor(Color.black);
        g.fillRect(0, 0, gameWidth, gameHeight);
        
        g.setColor(Color.YELLOW);
        g.fillOval(character.getPosX(), character.getPosY(), 50, 50);
        
        g2.drawImage(i,0,0,frame);
    }
    
}
