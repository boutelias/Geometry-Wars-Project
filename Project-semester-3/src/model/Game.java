package model;

import gui.GameGui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class Game {
    Random randomGenerator = new Random();
    int fps = 60;
    int gameHeight;
    int gameWidth;
    int spawnEnemyX;
    int spawnEnemyY;
           
    
    Character character;
    InputHandler handler;
    
    List<Bullet> bullets = new ArrayList();
    List<Enemy> enemies = new ArrayList();
    List<Geom> geoms = new ArrayList();
    
    long lastBulletFired = 0;
    
    GameGui gameGui;
    
    
    public static void main(String[] args) {
        new Game();
    }
    
    public Game(){
        run();
        System.exit(-1);
    }
    
    void run(){
        init();
        
        while(character.getLives()>0){
            long time = System.currentTimeMillis();
            
            update();
            draw();
            
            time = (1000 / fps) - (System.currentTimeMillis() - time);
            if(time > 0){
                try {
                    Thread.sleep(time);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    void init(){
        gameGui = new GameGui();
        gameWidth = gameGui.getGameWidth();
        gameHeight = gameGui.getGameHeight();
        
        character = new Character(gameWidth,gameHeight);
       
        handler = new InputHandler(gameGui.getFrame());   
    }
    
    void update(){
        updateCharacterPos();
        updateBullets();
        updateEnemies();
        collisionDetection();
    }
    
    void draw(){
        gameGui.draw(character, bullets, enemies, geoms);
    }
    
    void randomSpawnGenerator(){
       int randomInt = randomGenerator.nextInt(4);
       switch(randomInt){
           case 0 : spawnEnemyX = 0;
               spawnEnemyY = 40 + randomGenerator.nextInt(gameHeight-40);
               break;
           case 1 :  spawnEnemyY = 0;
                spawnEnemyX = randomGenerator.nextInt(gameWidth);
                break;
           case 2 : spawnEnemyX = gameWidth;
                spawnEnemyY = 40 + randomGenerator.nextInt(gameHeight-40);
                break;
           case 3 : spawnEnemyY = gameHeight;
                spawnEnemyX = randomGenerator.nextInt(gameWidth);
                break;
               
       }
    }

    private void updateCharacterPos() {
        if(handler.isKeyDown(KeyEvent.VK_RIGHT)){
            character.moveRight();
        }
        if(handler.isKeyDown(KeyEvent.VK_LEFT)){
            character.moveLeft();
        }
        if(handler.isKeyDown(KeyEvent.VK_UP)){
            character.moveUp();
        }
        if(handler.isKeyDown(KeyEvent.VK_DOWN)){
            character.moveDown();
        }
    }
    
    private void updateBullets(){
        List<Bullet> needToRemove = new ArrayList();
        
        addBullets();
        
        for(Bullet bullet: bullets){
            bullet.updatePos();
            
            if(bullet.getIsOutOfScreen()){
                needToRemove.add(bullet);
            }
        }
        
        for(Bullet bullet: needToRemove){
            bullets.remove(bullet);
        }
    }
    
    private void addBullets(){
        if(handler.isMouseDown(1)){            
            if(lastBulletFired + (60.0/character.getBulletsPerMinute()*1000)<System.currentTimeMillis()){
                //Moeten we echt height en witdh meegeven of kunnen we er anders aan?
                Bullet newBullet = new Bullet(character.getPosX(),character.getPosY(),handler.getEvent(1).getX(),handler.getEvent(1).getY(),character.getDamage(),gameHeight,gameWidth);
                bullets.add(newBullet);
                lastBulletFired = System.currentTimeMillis();
                randomSpawnGenerator();
                enemies.add(new Enemy(spawnEnemyX,spawnEnemyY));
            }
        }
    }
    
    private void updateEnemies(){
        boolean mayMove = true;
        
        for(Enemy currentEnemy: enemies){
            mayMove = true;
            for(Enemy otherEnemy: enemies){
                if(currentEnemy.getBounds().intersects(otherEnemy.getBounds())){
                double distanceCurrentEnemy = Math.sqrt((currentEnemy.getPosX() - character.getPosX())^2 + (currentEnemy.getPosY() - character.getPosY())) ;
                double distanceOtherEnemy = Math.sqrt((otherEnemy.getPosX() - character.getPosX())^2 + (otherEnemy.getPosY() - character.getPosY())) ;
                
                if(distanceCurrentEnemy>distanceOtherEnemy){
                    mayMove = false;
                }
                }
                }
        if(mayMove){
            currentEnemy.updatePos(character.getPosX(),character.getPosY());
        }
        }
        
        
        
//        for(Enemy enemy: enemies){
//            enemy.updatePos(character.getPosX(),character.getPosY());
//        }
        
        
        
    }
    
    private void collisionDetection(){
        /*bullets vs enemiesdetection*/
        List<Bullet> bulletsToRemove = new LinkedList();
        List<Enemy> enemiesToRemove = new LinkedList();
        
        for(Bullet bullet: bullets){
            for(Enemy enemy: enemies){
                if(bullet.getBounds().intersects(enemy.getBounds())){
                    
                    checkdamage(bullet, enemy);
                    
                    if(enemy.getHp()==0){
                        if(dropGeom(enemy.getDroprate())){
                            Geom geom = new Geom(enemy.getPosX(),enemy.getPosY());
                            geoms.add(geom);
                        }
                        enemiesToRemove.add(enemy);
                        character.addPoints(enemy.getValue());
                    }
                    if(bullet.getDamage()==0){
                        bulletsToRemove.add(bullet);
                        
                    }
                    
                }
            }
        }
        
        for(Bullet bullet: bulletsToRemove){
            bullets.remove(bullet);
        }
        for(Enemy enemy: enemiesToRemove){
                enemies.remove(enemy);
        }
        
        /*enemies vs character detection*/
        Enemy hittedChar = null;
        for(Enemy enemy: enemies){
            if(character.getBounds().intersects(enemy.getBounds())){
                character.lifeLess();
                hittedChar = enemy;
            }
        }
        if(hittedChar!= null){
            enemies.remove(hittedChar);
        }
        
        
        /* character vs geoms */
        List<Geom> hittedGeoms = new ArrayList();
        for(Geom geom: geoms){
            if(character.getBounds().intersects(geom.getBounds())){
                character.addGeom();
                System.out.println(character.getNumberOfGeoms());
                hittedGeoms.add(geom);
            }
        }
        for(Geom geom: hittedGeoms){
            geoms.remove(geom);
        }
    }
    
    private void checkdamage(Bullet bullet, Enemy enemy) {
        int bulletdamage = bullet.getDamage();
        int enemyhp = enemy.getHp();
        
        
        if(bulletdamage>enemyhp){
            bullet.setDamage(bulletdamage - enemyhp);
            enemy.setHp(0);
            
        }else if(bulletdamage<enemyhp){
            enemy.setHp(enemyhp - bulletdamage);
            bullet.setDamage(0);
        }else{
            enemy.setHp(0);
            bullet.setDamage(0);
        }
    }

    private boolean dropGeom(double droprate){
        int maxint = (int) (droprate * 100);
        
        int randomInt = randomGenerator.nextInt(100);
        
        if(randomInt<maxint){
            return true;
        }
        else{
            return false;
        }
        
    }

    
}
