/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import model.Player;

/**
 *
 * @author Elias
 */
public class calculateForPlayer {
    Player player = new Player(1000,1000);
    public int moveUp(){
        
        player.moveUp();
        return player.getPosY();
    }
    public int moveDown(){
        player.moveDown();
        return player.getPosY();
    }
    public int moveRight(){
        player.moveRight();
        return player.getPosX();
    }
    public int moveLeft(){
        player.moveLeft();
        return player.getPosX();
    }
    public int addLife(){
        player.addLife();
        return player.getLives();
    }
    public int removeLife(){
        player.removeLife();
        return player.getLives();
    }
    public long addPoints(int extraPoints){
        player.addPoints(extraPoints);
        return player.getScore();
    }
    public int addGeom(){
        player.addGeom();
        return player.getNumberOfGeoms();
    }
    public int getMovementSpeed(){
        return player.getMovementSpeed();
    }
    
    public int getInitialPosX(){
        return player.getPosX();
    }
    public int getInitialPosY(){
        return player.getPosY();
    }
    public int getInitialLives(){
        return player.getLives();
    }
    public long getInitialScore(){
        return player.getScore();
    }
    public int getInitialGeoms(){
        return player.getNumberOfGeoms();
    }
    
}
