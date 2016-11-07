package model;

import java.awt.Rectangle;

public class Character {
    private int width = 50;
    private int height = 50;
    private int posX;
    private int posY;
    private int maxX;
    private int maxY;
    //the sprite of the character would be nice to store here
    private int movementSpeed = 2;
    private int damage;
    private int lives = 3;
    private int bulletsPerMinute;
   
    
    
    public Character(int maxX, int maxY){
        //where are we going to start?
        //middle of the screen? -- variables in parameter of construcor
        this.maxX = maxX;
        this.maxY = maxY;
        posX = 100;
        posY = 100;
        bulletsPerMinute = 80;
    }
    
    public void moveUp(){
        if(posY-movementSpeed>(34+movementSpeed+(height/2))){
            posY -= movementSpeed;
        }
    }
    public void moveDown(){
        if(posY+movementSpeed<(maxY-6-movementSpeed-(height/2))){
            posY += movementSpeed;
            
        }
    }
    public void moveLeft(){
        if(posX-movementSpeed>(6+movementSpeed+(width/2))){
            posX -= movementSpeed;
            
        }
    }
    public void moveRight(){
        if(posX-movementSpeed<(maxX-10-movementSpeed-(width/2))){
            posX += movementSpeed;
        }
    }
    
    public void lifeLess(){
        lives -= 1;
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
    
}
