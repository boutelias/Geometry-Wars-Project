package model;

import java.awt.Rectangle;

public class Character {
    private int width = 50;
    private int height = 50;
    private int posX;
    private int posY;
    //the sprite of the character would be nice to store here
    private int movementSpeed = 2;
    private int damage;
    private int lives;
    private int bulletsPerMinute;
   
    
    
    public Character(){
        //where are we going to start?
        //middle of the screen? -- variables in parameter of construcor
        posX = 100;
        posY = 100;
        bulletsPerMinute = 800;
    }
    
    public void moveUp(){
        posY -= movementSpeed;
    }
    public void moveDown(){
        posY += movementSpeed;
    }
    public void moveLeft(){
        posX -= movementSpeed;
    }
    public void moveRight(){
        posX += movementSpeed;
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
}
