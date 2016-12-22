package model.power;

import java.awt.Rectangle;
import model.Character;

public class SpeedBoost implements Power {

    private Character c;
    private int speed;
    private long startTime;
    private int duration;
    private boolean pickedUp;
    private int posX;
    private int posY;

    public SpeedBoost(int posX, int posY) {
        speed = 2;
        this.posX = posX;
        this.posY = posY;
        this.duration = 20000;
        System.out.println("power up dropped");
    }

    @Override
    public void start(Character c) {
        this.pickedUp = true;
        startTime = System.currentTimeMillis();
        this.c = c;
        this.c.increaseSpeed(speed);

        System.out.println("power up started");
    }

    private void end() {

        c.decreaseSpeed(speed);
    }

    @Override
    public boolean isTheEnd() {
        System.out.println("time to reach " + startTime + duration);
        System.out.println("curr time " + System.currentTimeMillis());
        if (pickedUp) {
            if ((startTime + duration) <= System.currentTimeMillis()) {
                end();
                System.out.println("ended");
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
}
