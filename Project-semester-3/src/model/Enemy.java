package model;

import java.awt.Rectangle;

public class Enemy {
    private int width = 20;
    private int height = 20;
    private float posX;
    private float posY;
    private float deltaX;
    private float deltaY;
    
    public Enemy(){
        posX = 300;
        posY = 300;
    }



    private void calculateDirection(int playerX, int playerY){
       deltaX = playerX - posX;
       deltaY = playerY - posY;
       
       int absDeltaX = (int) Math.abs(deltaX);
       int absDeltaY = (int) Math.abs(deltaY);
       //TODO make sure that the speed for every bullet is the same
       if(absDeltaX>absDeltaY){
           deltaY = (float) deltaY/absDeltaX;
           deltaX = deltaX/absDeltaX;
       }else{
           deltaX = (float) deltaX/absDeltaY;
           deltaY = deltaY/absDeltaY;
       }
       
    }
    
    public void updatePos(int playerX,int playerY){
        calculateDirection(playerX,playerY);
        posX+= deltaX;
        posY+= deltaY;
    }
    
    
    /* Getters and Setters */
    public int getPosX() {
        return Math.round(posX);
    }

    public int getPosY() {
        return Math.round(posY);
    }
    public Rectangle getBounds(){
        return new Rectangle(getPosX()-(width/2),getPosY()-(height/2),width,height);
    }
}
