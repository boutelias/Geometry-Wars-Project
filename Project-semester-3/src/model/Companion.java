
package model;

import java.awt.Rectangle;


public class Companion {
    private int posX;
    private int posY;
    private int width;
    private int height;
    private String type;
    private Player player;
    private int movementSpeed;
    
    public Companion(int width, int height, String type,Player player){
        this.width = width;
        this.height = height;
        this.type = type;
        this.player = player;
        this.movementSpeed = player.getMovementSpeed();
        initialPos();
        
    }
    
    public void initialPos(){
        posX = player.getPosX()-player.getWidth()-width;
        posY = player.getPosY()-player.getHeight()-height;
    }
    
    public void updateCompanion(int playerX , int playerY){
       float deltaX = playerX - posX;
       float deltaY = playerY - posY;
       
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
       
       posX += deltaX*movementSpeed;
       posY += deltaY*movementSpeed;
        
        switch(type){
            case "shield" : 
                break;
            case "lifesaver" :
                break;
                
        }
    }
    
    
    
    public int getPosX(){
        return posX;
    }
    
    public int getPosY(){
        return posY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public Rectangle getBounds(){
        return new Rectangle(getPosX()-(width/2),getPosY()-(height/2),width,height);
    }
    
    
    
    
    
}
