/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.power;

import data.GameDA;
import java.awt.Rectangle;
import java.io.Serializable;

/**
 *
 * @author Tobias
 */
public class SpeedNerf implements Power,Serializable{
    private model.Character c;
    private int speed;
    private long startTime;
    private int duration;
    private boolean pickedUp;
    private int posX;
    private int posY;
    private String name = "Speed nerf";
    private GameDA db = GameDA.getInstance();

    public SpeedNerf(int posX, int posY) {
        //speed = db.getPower("movementspeednerf");
        speed = 1;
        this.posX = posX;
        this.posY = posY;
        this.duration = 20000;
    }

    @Override
    public void start(model.Character c) {
        this.pickedUp = true;
        startTime = System.currentTimeMillis();
        this.c = c;
        this.c.decreaseSpeed(speed);

    }

    private void end() {

        c.increaseSpeed(speed);
    }

    @Override
    public boolean isTheEnd() {
        if (pickedUp) {
            if ((startTime + duration) <= System.currentTimeMillis()) {
                end();
                return true;
            } else {
                return false;
            }
        }else{
            return false;
        }
    }

    public boolean isPickedUp() {
        return this.pickedUp;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(posX - (20 / 2), posY - (20 / 2), 20, 20);
    }
    
    @Override
    public String getName(){
        return this.name;
    }
    
    @Override
    public int getTimeLeft(){
        return (int) (startTime + duration - System.currentTimeMillis() )/1000;
    }
}
