package model.power;

import java.awt.Rectangle;
import java.io.Serializable;
import model.Character;

public class SpeedBoost implements Power, Serializable {

    private Character c;
    private int speed;
    private long startTime;
    private int duration;
    private boolean pickedUp;
    private int posX;
    private int posY;
    private String name = "Speedboost";

    public SpeedBoost(int posX, int posY) {
        speed = 2;
        this.posX = posX;
        this.posY = posY;
        this.duration = 20000;
    }

    @Override
    public void start(Character c) {
        this.pickedUp = true;
        startTime = System.currentTimeMillis();
        this.c = c;
        this.c.increaseSpeed(speed);

    }

    private void end() {

        c.decreaseSpeed(speed);
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
        return (int) (startTime + duration - System.currentTimeMillis())/1000;
    }
}
