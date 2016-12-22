/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.companions;

import java.awt.Rectangle;
import java.util.List;
import model.Bullet;
import model.InputHandler;
import model.Character;

/**
 *
 * @author Tobias
 */
public class Shooter implements Companion {

    int posX;
    int posY;
    int width;
    int height;
    Character character;
    InputHandler handler;
    List<Bullet> bullets;
    int damage;
    int movementSpeed;
    int bulletsPerMinute;
    long lastBulletFired;
    int bulletspeed;

    public Shooter(Character character, InputHandler handler, List<Bullet> bullets, int height, int width, int damage, int bulletsPerMinute,int bulletspeed) {
        this.character = character;
        this.handler = handler;
        this.height = height;
        this.width = width;
        this.bullets = bullets;
        this.damage = damage;
        this.bulletsPerMinute = bulletsPerMinute;
        this.bulletspeed = bulletspeed;

        this.movementSpeed = character.getMovementSpeed();
        posX = character.getPosX();
        posY = character.getPosY();

        this.lastBulletFired = System.currentTimeMillis();

    }

    @Override
    public void doMove() {
        this.movementSpeed = character.getMovementSpeed();
        if(!character.getBounds().intersects(this.getBounds())){       
            moveCompanion(character.getPosX(),character.getPosY());
        }
    }

    @Override
    public void doSpecialAction() {
        if (handler.isMouseDown(1)) {
            if (this.lastBulletFired + (60.0 / this.bulletsPerMinute * 1000) < System.currentTimeMillis()) {
                Bullet newBullet = new Bullet(posX, posY, handler.getEvent(1).getX(), handler.getEvent(1).getY(), this.damage, 1080, 1920,bulletspeed,character);
                bullets.add(newBullet);
                this.lastBulletFired = System.currentTimeMillis();
            }
        }
    }
    
    public void moveCompanion(int targetX,int targetY){
       float deltaX = targetX - posX;
       float deltaY = targetY - posY;
       
       int absDeltaX = (int) Math.abs(deltaX);
       int absDeltaY = (int) Math.abs(deltaY);
       
       if(absDeltaX>absDeltaY){
           deltaY = (float) deltaY/absDeltaX;
           deltaX = deltaX/absDeltaX;
       }else{
           deltaX = (float) deltaX/absDeltaY;
           deltaY = deltaY/absDeltaY;
       }
       
       posX += deltaX*movementSpeed;
       posY += deltaY*movementSpeed;
        
    }
    
    public Rectangle getBounds() {
        return new Rectangle(posX-(width/2),posY-(height/2),width,height);
    }

    @Override
    public int getPosX() {
        return posX;
    }

    @Override
    public int getPosY() {
        return posY;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

}
