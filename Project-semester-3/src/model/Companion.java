
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
    private long timeExtraLife;
    private long getExtraLife = 5000;
    
    
    public Companion(int width, int height, String type,Player player){
        this.width = width;
        this.height = height;
        this.type = type;
        this.player = player;
        this.movementSpeed = player.getMovementSpeed();
        initialPos();
        timeExtraLife =0;
    }
    
    public void initialPos(){
        posX = player.getPosX()-player.getWidth()-width;
        posY = player.getPosY()-player.getHeight()-height;
    }
    
    public void updateCompanion(int playerX , int playerY,long time,int movementspeed){
       movementSpeed = movementspeed; 
       if(!type.equals("Collector")){
           moveCompanion(playerX,playerY);
       }
       timeExtraLife += time;

        switch(type){
            case "Shield" : 
                break;
            case "LifeSaver" :
                if(timeExtraLife > getExtraLife){  
                    player.lifeExtra();
                    timeExtraLife = 0;
                } 
                    
                break;
            case "Collector" :
                break;
               
                
                
        }
    }
    
    public void moveCompanion(int targetX,int targetY){
       float deltaX = targetX - posX;
       float deltaY = targetY - posY;
       
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
