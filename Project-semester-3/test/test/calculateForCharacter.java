/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import model.Character;

/**
 *
 * @author Elias
 */
public class calculateForCharacter {
    Character character = new Character(1000,1000,3,"iets",1,1,1,1,1,1);
    public int moveUp(){
        
        character.moveUp();
        return character.getPosY();
    }
    public int moveDown(){
        character.moveDown();
        return character.getPosY();
    }
    public int moveRight(){
        character.moveRight();
        return character.getPosX();
    }
    public int moveLeft(){
        character.moveLeft();
        return character.getPosX();
    }
    public int addLife(){
        character.addLife();
        return character.getLives();
    }
    public int removeLife(){
        character.removeLife();
        return character.getLives();
    }
    public long addPoints(int extraPoints){
        character.addPoints(extraPoints);
        return character.getScore();
    }
    public int addGeom(){
        character.addGeom();
        return character.getNumberOfGeoms();
    }
    public int getMovementSpeed(){
        return character.getMovementSpeed();
    }
    
    public int getInitialPosX(){
        return character.getPosX();
    }
    public int getInitialPosY(){
        return character.getPosY();
    }
    public int getInitialLives(){
        return character.getLives();
    }
    public long getInitialScore(){
        return character.getScore();
    }
    public int getInitialGeoms(){
        return character.getNumberOfGeoms();
    }
    
}
