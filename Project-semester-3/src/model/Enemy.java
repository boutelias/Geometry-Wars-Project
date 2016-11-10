package model;

import java.awt.Rectangle;
import java.io.Serializable;

public class Enemy implements Serializable{
    private int width = 20;
    private int height = 20;
    private float posX;
    private float posY;
    private float deltaX;
    private float deltaY;
    private double dropRateGeom = 0.20; //TODO dit uit database 
    private double dropRateSpeedBoost = 0.00001; // TODO dit ook uit database
    private double dropRateBPM = 0.00001;
    private double dropRateExtraEnemy = 0.00001;
    private double dropRateSpeedDown = 0.00001;
    private int value = 15;
    private int hp = 15;
    public Enemy(int posX,int posY){
        this.posX = posX;
        this.posY = posY;
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
    public int getValue(){
        return value;
    }
    public int getHp() {
        return hp;
    }
    public void setHp(int hp){
        this.hp = hp;
    }
    public double getDropRateGeom(){
        return dropRateGeom;
    }
    
    public double getDropRateSpeedBoost(){
        return dropRateSpeedBoost;
    }
    
    public double getDropRateBPM(){
        return dropRateBPM;
    }
    public double getDropRateExtraEnemy(){
        return dropRateExtraEnemy;
    }
    public double getDroprateSpeedDown(){
        return dropRateSpeedDown;
    }
    

    
    
}
