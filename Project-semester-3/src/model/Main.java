package model;

import gui.GameGui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class Main {
    
    int fps = 60;
    int gameHeight= 800;
    int gameWidth = 1000;
    
    Character character;
    InputHandler handler;
    
    List<Bullet> bullets = new ArrayList();
    long lastBulletFired = 0;
    
    GameGui gameGui;
    
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

        gameGui = new GameGui(gameWidth,gameHeight);
        
        character = new Character();
       
        handler = new InputHandler(gameGui.getFrame());   
    }
    
    void update(){
        updateCharacterPos();
        updateBullets();
        /*update enemys*/
        /*collision detection*/
    }
    
    void draw(){
        gameGui.draw(character, bullets);
    }

    private void updateCharacterPos() {
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
    }
    
    private void updateBullets(){
        List<Bullet> needToRemove = new ArrayList();
        
        if(handler.isMouseDown(1)){            
            if(lastBulletFired + (60.0/character.getBulletsPerMinute()*1000)<System.currentTimeMillis()){
                //Moeten we echt height en witdh meegeven of kunnen we er anders aan?
                Bullet newBullet = new Bullet(character.getPosX(),character.getPosY(),handler.getEvent(1).getX(),handler.getEvent(1).getY(),gameHeight,gameWidth);
                bullets.add(newBullet);
                lastBulletFired = System.currentTimeMillis();
            }
        }
        
        for(Bullet bullet: bullets){
            bullet.updatePos();
            
            if(bullet.getIsOutOfScreen()==true){
                needToRemove.add(bullet);
            }
        }
        
        for(Bullet bullet: needToRemove){
            bullets.remove(bullet);
        }
    }
    
}
