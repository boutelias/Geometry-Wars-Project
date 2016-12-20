/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.power;

import model.Character;

/**
 *
 * @author Maxime
 */
public class SpeedBoost implements Power {

    private Character c;
    private int speed;
    private long startTime;
    private int duration;

    public SpeedBoost() {
        speed = 2;
    }

    @Override
    public void start(Character c) {
        startTime = System.currentTimeMillis();
        this.c = c;
        this.c.increaseSpeed(speed);
    }

    private void end() {

        c.decreaseSpeed(speed);
    }

    @Override
    public boolean isTheEnd() {
        if (startTime + duration <= System.currentTimeMillis()) {
            end();
            return true;
        } else {
            return false;
        }
    }

}
