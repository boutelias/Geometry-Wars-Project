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
public class testEnemy {
    calculateForEnemy calc = new calculateForEnemy();
    
    @Test
    public void updatePos(){
        float iniposX = calc.getInitialPosX();
        float iniposY = calc.getInitialPosY();
        calc.updatePos(30,30);
        float deltaX = calc.getDeltaX();
        float deltaY = calc.getDeltaY();
        float newposX = calc.getInitialPosX();
        float newposY = calc.getInitialPosY();
        assertEquals(iniposX + deltaX ,newposX,0.0);
        assertEquals(iniposX + deltaY ,newposY,0.0);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
