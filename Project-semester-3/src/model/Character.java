package model;


import java.awt.Rectangle;
import java.io.Serializable;

public class Character implements Serializable{
    private int width = 50;
    private int height = 50;
    private int posX;
    private int posY;
    private int maxX;
    private int maxY;
    //the sprite of the player would be nice to store here
    private int movementSpeed ;
    private int damage ;
    private int lives ;
    private int bulletsPerMinute;
    private long score = 0;
    private int numberOfGeoms = 0;
    private long lastBulletFired = 0;
    private int bulletspeed;
    private String sprite;
    
   
    
    
    public Character(int maxX, int maxY,int lives,String sprite,int firerate,int movementspeed,int height,int width,int bulletdamage,int bulletspeed){
        //where are we going to start?
        //middle of the screen? -- variables in parameter of construcor
        this.maxX = maxX;
        this.maxY = maxY;
        posX = maxX/2;
        posY = maxY/2;
        this.lives = lives;
        this.bulletsPerMinute = firerate;
        this.movementSpeed = movementspeed;
        this.height = height;
        this.width = width;
        this.damage = bulletdamage;
        this.bulletspeed = bulletspeed;
        this.sprite = sprite;
        
        lastBulletFired = System.currentTimeMillis();
        
    }
    
    
    
    public void moveUp(){
        if(posY-movementSpeed>(40+movementSpeed+(height/2))){
            posY -= movementSpeed;
            
        }
    }
    public void moveDown(){
        if(posY+movementSpeed<(maxY-movementSpeed-(height/2))){
            posY += movementSpeed;
            
        }
    }
    public void moveLeft(){
        if(posX-movementSpeed>(movementSpeed+(width/2))){
            posX -= movementSpeed;
            
        }
    }
    public void moveRight(){
        if(posX-movementSpeed<(maxX-movementSpeed-(width/2))){
            posX += movementSpeed;
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
        return posX;
    }
    
    public int getPosY(){
        return posY;
    }
    public int getBulletsPerMinute(){
        return bulletsPerMinute;
    }
    public Rectangle getBounds(){
        return new Rectangle(posX-(width/2),posY-(height/2),width,height);
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
    public int getBulletSpeed(){
        return bulletspeed;
    }
    
    public void increaseSpeed(int speed){
        this.movementSpeed += speed;
    }
    
    public void decreaseSpeed(int speed){
        this.movementSpeed -= speed;
    }
    
    
}
