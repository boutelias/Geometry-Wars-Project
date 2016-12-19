/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.companions;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.List;
import model.InputHandler;
import model.Mine;
import model.Character;

/**
 *
 * @author Tobias
 */
public class Miner implements Companion{
    List<Mine> mines;
    Character player;
    int movementSpeed;
    int posX;
    int posY;
    int width;
    int height;
    InputHandler handler;
    
    int minesPerMinute;
    int damage;
    long timeBetween2Mines;
    long lastMineFired;
    
    public Miner(Character player, List<Mine> mines, InputHandler handler,int width, int height, int minesPerMinute, int damage){
        this.mines = mines;
        this.player= player;
        this.movementSpeed = movementSpeed;
        
        this.width = width;
        this.height = height;
        this.handler = handler;
        this.damage = damage;
        this.minesPerMinute = minesPerMinute;
        
        this.posX = player.getPosX();
        this.posY = player.getPosY();
        this.movementSpeed = player.getMovementSpeed();
        
    }

    @Override
    public void doMove() {
        if(!player.getBounds().intersects(this.getBounds())){       
            moveCompanion(player.getPosX(),player.getPosY());
        }
    }

    public void moveCompanion(int targetX,int targetY){
       float deltaX = targetX - posX;
       float deltaY = targetY - posY;
       
       int absDeltaX = (int) Math.abs(deltaX);
       int absDeltaY = (int) Math.abs(deltaY);
       
       if(absDeltaX>absDeltaY){
           deltaY = (float) deltaY/absDeltaX;
           deltaX = deltaX/absDeltaX;
       }else{
           deltaX = (float) deltaX/absDeltaY;
           deltaY = deltaY/absDeltaY;
       }
       
       posX += deltaX*movementSpeed;
       posY += deltaY*movementSpeed;
        
    }
    
    @Override
    public void doSpecialAction() {
        if(handler.isMouseDown(MouseEvent.BUTTON3)){
            if(this.lastMineFired+ (60.0/this.minesPerMinute*1000)<System.currentTimeMillis()){
                System.out.println("Dit mag maar 6 keer per minuut");
                Mine newmine = new Mine(this.posX,this.posY,this.damage);
                mines.add(newmine);
                this.lastMineFired = System.currentTimeMillis();
            }
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(posX-(width/2),posY-(height/2),width,height);
    }
    
    
    /* Getters and Setters */

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
}
