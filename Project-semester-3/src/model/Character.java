package model;

public class Character {
    private int posX;
    private int posY;
    //the sprite of the character would be nice to store here
    private int damage;
    private int lives;
    private int bulletsPerMinute;
    
    
    public Character(){
        //where are we going to start?
        //middle of the screen? -- variables in parameter of construcor
        posX = 100;
        posY = 100;
        bulletsPerMinute = 300;
    }
    
    public void moveUp(){
        posY --;
    }
    public void moveDown(){
        posY ++;
    }
    public void moveLeft(){
        posX --;
    }
    public void moveRight(){
        posX ++;
    }
    
    
    /*GETTERS AND SETTERS*/
    public int getPosX(){
        return posX;
    }
    
    public int getPosY(){
        return posY;
    }
    public int getBulletsPerMinute(){
        return bulletsPerMinute;
    }
}
