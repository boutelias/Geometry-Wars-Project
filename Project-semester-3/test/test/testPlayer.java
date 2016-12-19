/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Elias
 */
public class testPlayer {
    calculateForPlayer calc = new calculateForPlayer();
    int movementspeed = calc.getMovementSpeed();
    int iniposX = calc.getInitialPosX();
    int iniposY = calc.getInitialPosY();
    int iniLife = calc.getInitialLives();
    long iniScore = calc.getInitialScore();
    int iniGeoms = calc.getInitialGeoms();
    @Test
    public void testMoveUp(){
        int moveUp = calc.moveUp();
        assertEquals(iniposY-movementspeed,moveUp);
    }
    @Test
    public void testMoveDown(){
        int moveDown = calc.moveDown();
        assertEquals(iniposY + movementspeed,moveDown);
    }
    @Test
    public void testMoveRight(){
        int moveRight = calc.moveRight();
        assertEquals(iniposX + movementspeed,moveRight);
    }
    @Test
    public void testMoveLeft(){
        int moveLeft = calc.moveLeft();
        assertEquals(iniposX - movementspeed,moveLeft);
    }
    @Test
    public void testAddLife(){
        int addlife = calc.addLife();
        assertEquals(iniLife + 1,addlife);
    }
    @Test
    public void testRemoveLife(){
        int removelife = calc.removeLife();
        assertEquals(iniLife -1 ,removelife);
    }
    @Test
    public void addPoints(){
        long addpoints = calc.addPoints(15);
        assertEquals(iniScore + 15,addpoints);
    }
    @Test
    public void addGeom(){
        int addgeom = calc.addGeom();
        assertEquals(iniGeoms + 1 , addgeom);
    }
    

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
