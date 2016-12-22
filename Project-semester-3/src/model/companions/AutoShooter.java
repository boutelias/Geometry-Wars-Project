package model.companions;

import java.awt.Rectangle;
import java.util.List;
import model.Bullet;
import model.Character;
import model.Enemy;


/**
 *
 * @author Arne
 */
public class AutoShooter implements Companion {
    
    int posX;
    int posY;
    double EnemyPosX;
    double EnemyPosY;
    int width;
    int height;
    Character character;
    List<Bullet> bullets;
    List<Enemy> enemies;
    double closestEnemyDistance = width;
    double PreviousClosestEnemyDistance;
    Enemy closestEnemy ;
    int damage;
    int movementSpeed;
    int bulletsPerMinute;
    long lastBulletFired;
    int bulletspeed;
    
    public AutoShooter(Character character, List<Bullet> bullets, List<Enemy> enemies, int height, int width, int damage, int bulletsPerMinute, int bulletspeed) {
        this.character = character;
        this.height = height;
        this.width = width;
        this.bullets = bullets;
        this.enemies = enemies;
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
        if(!enemies.isEmpty() ){
            closestEnemy = enemies.get(0);
            closestEnemyDistance = (double) Math.sqrt((Math.pow(character.getPosX() - closestEnemy.getPosX(),2)) + (Math.pow(character.getPosY() - closestEnemy.getPosY(),2)));
            for(Enemy e : enemies){
                EnemyPosX = e.getPosX();
                EnemyPosY = e.getPosY();
                
                double distance = (double) Math.sqrt((Math.pow(character.getPosX() - EnemyPosX,2)) + (Math.pow(character.getPosY() - EnemyPosY,2)));
                if(distance < closestEnemyDistance){

                    closestEnemyDistance = distance;
                    closestEnemy = e;
                    
                }
            }
            if(lastBulletFired + (60.0 / bulletsPerMinute * 1000) < System.currentTimeMillis()) {
                Bullet newbullet = new Bullet(posX, posY, closestEnemy.getPosX(), closestEnemy.getPosY(), this.damage, 1080, 1920,bulletspeed,character);
                bullets.add(newbullet);
                lastBulletFired = System.currentTimeMillis();
            }
    }}
    
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
