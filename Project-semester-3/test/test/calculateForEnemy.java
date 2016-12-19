/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.List;
import model.Enemy;
import model.Game;

/**
 *
 * @author Elias
 */
public class calculateForEnemy {
    Enemy enemy = new Enemy(0,0);
    
   public void updatePos(int x , int y){
       enemy.updatePos(x,y);
   }
   public float getDeltaX(){
       return enemy.getDeltaX();
   }
   public float getDeltaY(){
       return enemy.getDeltaY();
   }
   public float getInitialPosX(){
       return enemy.getPosX();
   }
   public float getInitialPosY(){
       return enemy.getPosY();
   }
   
}
