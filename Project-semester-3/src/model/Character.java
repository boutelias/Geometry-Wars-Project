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
public class Character {
    private int posX;
    private int posY;
    
    public Character(){
        //where are we going to start?
        //middle of the screen? -- variables in parameter of construcor
        posX = 100;
        posY = 100;
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
}
