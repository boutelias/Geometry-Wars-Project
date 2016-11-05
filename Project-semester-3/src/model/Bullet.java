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
    private int posX;
    private int posY;
    private int maxY;
    private int maxX;
    private int deltaX;
    private int deltaY;
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
       
       //TODO make sure that the speed for every bullet is the same
   }
    
    public void updatePos(){
        posX += deltaX;
        posY += deltaY;
        
        if(posX>maxX || posX<0 || posY>maxY || posY<0){
            isOutOfScreen = true;
        }
    }
    

    
    /*GETTERS AND SETTERS*/
    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
    
    public boolean getIsOutOfScreen(){
        return isOutOfScreen;
    }
    
}
