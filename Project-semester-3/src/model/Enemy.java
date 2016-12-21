package model;

import java.awt.Rectangle;
import java.io.Serializable;

public class Enemy implements Serializable{
    private int width ;
    private int height;
    private float posX;
    private float posY;
    private float deltaX;
    private float deltaY;
    private double dropRateGeom ; //TODO dit uit database 
    private double dropratepowerups;
    private double dropratepowerdowns; 
    private int value = 15;
    private int hp = 15;
    private String sprite;
    private int movementspeed;
    
    
    
    public Enemy(int posX,int posY,String sprite,double dropratepowerups,double dropratepowerdowns,double droprategeoms,int width, int height,int hp , int movementspeed,int value){
        this.posX = posX;
        this.posY = posY;
        this.sprite = sprite;
        this.dropratepowerups = dropratepowerups;
        this.dropratepowerdowns = dropratepowerdowns;
        this.dropRateGeom = droprategeoms;
        this.width = width;
        this.height = height;
        this.hp = hp;
        this.movementspeed = movementspeed;
        this.value = value;
        
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
        posX+= deltaX * movementspeed;
        posY+= deltaY * movementspeed;
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
    
    public float getDeltaX() {
        return deltaX;
    }

    public float getDeltaY() {
        return deltaY;
    }

    public double getDropratepowerups() {
        return dropratepowerups;
    }

    public double getDropratepowerdowns() {
        return dropratepowerdowns;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    
    
}
