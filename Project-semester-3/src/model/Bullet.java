/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Tobias
 */
public class Bullet {
    private float posX;
    private float posY;
    
    private int maxX;
    private int maxY;

    private float deltaX;
    private float deltaY;
    
    private int bulletSpeed = 5;
    
    private boolean isOutOfScreen = false;
    //how to implement speed?
    
    public Bullet(int startX, int startY, int clickX,int clickY, int maxY, int maxX){
        this.posX = startX;
        this.posY = startY;
        this.maxY = maxY;
        this.maxX = maxX;
        
        calculateDirection(clickX,clickY);
    }
    
    private void calculateDirection(int clickX, int clickY){
       deltaX = clickX - posX;
       deltaY = clickY - posY;
       
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
    
    public void updatePos(){
        posX += deltaX * bulletSpeed;
        posY += deltaY * bulletSpeed;
        
        if(posX>maxX || posX<0 || posY>maxY || posY<0){
            isOutOfScreen = true;
        }
    }
    

    
    /*GETTERS AND SETTERS*/
    public int getPosX() {
        return Math.round(posX);
    }

    public int getPosY() {
        return Math.round(posY);
    }
    
    public boolean getIsOutOfScreen(){
        return isOutOfScreen;
    }
    
}
