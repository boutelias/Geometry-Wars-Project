package model;

import java.awt.Rectangle;
import java.io.Serializable;

public class Player implements Serializable{
    private int width = 50;
    private int height = 50;
    private Position pos;
    private int maxX;
    private int maxY;
    //the sprite of the player would be nice to store here
    private int movementSpeed = 4;
    private int damage = 30;
    private int lives = 300;
    private int bulletsPerMinute;
    private long score = 0;
    private int numberOfGeoms = 0;
    private long lastBulletFired = 0;
    
   
    
    
    public Player(int maxX, int maxY){
        //where are we going to start?
        //middle of the screen? -- variables in parameter of construcor
        this.maxX = maxX;
        this.maxY = maxY;
        pos = new Position(200,200);
        bulletsPerMinute = 180;
        lastBulletFired = System.currentTimeMillis();
    }
    
    public void moveUp(){
        if(this.pos.getY()-movementSpeed>(40+movementSpeed+(height/2))){
            pos.setY(pos.getY() - movementSpeed);
            
        }
    }
    public void moveDown(){
        if(pos.getY()+movementSpeed<(maxY-movementSpeed-(height/2))){
            pos.setY(pos.getY() + movementSpeed);
            
        }
    }
    public void moveLeft(){
        if(pos.getX()-movementSpeed>(movementSpeed+(width/2))){
            pos.setX(pos.getX() - movementSpeed);
            
        }
    }
    public void moveRight(){
        if(pos.getX()-movementSpeed<(maxX-movementSpeed-(width/2))){
            pos.setX(pos.getX() + movementSpeed);
        }
    }
    
    public void removeLife(){
        lives --;
    }
    
    public void addLife(){
        lives ++;
    }
    
    public void addPoints(int numberOfPoints){
        score += numberOfPoints;
    }
    
    public void addGeom(){
        numberOfGeoms ++;
    }
    

    /*GETTERS AND SETTERS*/
    public int getWidth(){
        return width;
    }
    public int getHeight() {
        return height;
    }
    public int getPosX() {
        return (int) pos.getX();
    }
    
    public int getPosY(){
        return (int) pos.getY();
    }
    public int getBulletsPerMinute(){
        return bulletsPerMinute;
    }
    public Rectangle getBounds(){
        return new Rectangle(this.getPosX()-(width/2),this.getPosY()-(height/2),width,height);
    }
    public int getLives(){
        return lives;
    }
    public long getScore(){
        return score;
    }
    public int getDamage(){
        return damage;
    }

    public int getNumberOfGeoms() {
        return numberOfGeoms;
    }
    public int getMovementSpeed(){
        return movementSpeed;
    }
    public void setMovementSpeedHigher(){
        movementSpeed ++;
    }
    
    public void setMovementSpeedLower(){
        if(movementSpeed>1){
            movementSpeed --;
        }
        
    }
    
    public void setBPM(){
        bulletsPerMinute += 5;
    }

    public long getLastBulletFired() {
        return lastBulletFired;
    }

    public void setLastBulletFired(long lastBulletFired) {
        this.lastBulletFired = lastBulletFired;
    }
    
    
}
