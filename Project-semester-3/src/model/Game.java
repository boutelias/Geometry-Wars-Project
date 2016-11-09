package model;

import gui.GameGui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
/*import multiplayer.Server;*/

public class Game implements Serializable{
    private Random randomGenerator = new Random();
    private int fps = 60;
    private int gameHeight;
    private int gameWidth;
    private int spawnEnemyX;
    private int spawnEnemyY;
    private int waveCounter;
    private long spawnTimer;
    private Companion companion;
    private Player player;
    private InputHandler handler;
    private List<Bullet> bullets = new ArrayList();
    private List<Enemy> enemies = new ArrayList();
    private List<Geom> geoms = new ArrayList();
    private List<Wave> waves = new ArrayList<>(); 
    private long lastBulletFired = 0;
    private GameGui gameGui;
    /*private Server server;*/
    
    public static void main(String[] args) {
        new Game();
    }
    
    public Game(){
        run();
        System.exit(-1);
    }
    
    private void run(){
        init();
        makeWaves(waves);
            
        long spawnTimer = System.currentTimeMillis();
        waveCounter = 0;
        while(player.getLives()>0){
            
            long time = System.currentTimeMillis();
            
            update();
            
            draw();
            
            /*try {
                sendDataToClient();
            } catch (IOException ex) {
                System.out.println("failed to send data");
            }*/
            
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
    
    private void init(){
        /*try {
            server = new Server();
        } catch (IOException ex) {
            System.out.println("failed to make server");
        }*/
        
        gameGui = new GameGui();
        gameWidth = gameGui.getGameWidth();
        gameHeight = gameGui.getGameHeight();
        
        player = new Player(gameWidth,gameHeight);
       
        handler = new InputHandler(gameGui.getFrame());  
        companion = new Companion(30,30,"Shield",player,gameWidth,gameHeight);
    }
    
    private void update(){
        
        spawnEnemy();
        updatePlayerPos();
        updateCompanionPos();
        updateBullets();
        updateEnemies();
        collisionDetection();
    }
    
    private void draw(){
        gameGui.draw(player, bullets, enemies, geoms, companion);
    }
    
    private void randomSpawnGenerator(){
       int randomInt = randomGenerator.nextInt(4);
       switch(randomInt){
           case 0 : spawnEnemyX = 0;
               spawnEnemyY = 40 + randomGenerator.nextInt(gameHeight-40);
               break;
           case 1 :  spawnEnemyY = 40;
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
    
    private void updateCompanionPos(){
        if(player.getBounds().intersects(companion.getBounds())){
            
        }
        else {
           companion.updateCompanion(player.getPosX(),player.getPosY()); 
        }
        
    }
    
    private void makeWaves(List<Wave> waves){
        //TODO alle waves uit de databank halen 
        /*Wave wave1 = new Wave(2,1,enemies,5);
        Wave wave2 = new Wave(3,1,enemies,5);
        Wave wave3 = new Wave(4,1,enemies,5);
        Wave wave4 = new Wave(5,1,enemies,5);
        
        waves.add(wave1);
        waves.add(wave2);
        waves.add(wave3);
        waves.add(wave4);*/
        
        for(int i = 0; i < 200 ; i++){
            Wave wave = new Wave(i,1,enemies,5);
            waves.add(wave);
        }
    }
    
    private void spawnEnemy(){
        
        Wave wave = waves.get(waveCounter);
            if(System.currentTimeMillis() - spawnTimer > wave.getSpawnRateInMs() && wave.getNumberOfEnemiesLeft()!= 0){
                
                randomSpawnGenerator();
                enemies.add(new Enemy(spawnEnemyX,spawnEnemyY));
                spawnTimer = System.currentTimeMillis();
                wave.reduceNumberOfEnemiesLeft();
                
            }
            if(wave.getNumberOfEnemiesLeft()==0 && waveCounter < waves.size()-1){
                int delay = wave.getDelay();
                // TODO use this delay (maybe an upgrade screen after 2 seconds ?
                
                
                waveCounter ++;
                
            }
    }

    private void updatePlayerPos() {
        if(handler.isKeyDown(KeyEvent.VK_RIGHT)){
            player.moveRight();
            
            
        }
        if(handler.isKeyDown(KeyEvent.VK_LEFT)){
            player.moveLeft();
            
        }
        if(handler.isKeyDown(KeyEvent.VK_UP)){
            player.moveUp();
            
        }
        if(handler.isKeyDown(KeyEvent.VK_DOWN)){
            player.moveDown();
            
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
            if(lastBulletFired + (60.0/player.getBulletsPerMinute()*1000)<System.currentTimeMillis()){
                //Moeten we echt height en witdh meegeven of kunnen we er anders aan?
                Bullet newBullet = new Bullet(player.getPosX(),player.getPosY(),handler.getEvent(1).getX(),handler.getEvent(1).getY(),player.getDamage(),gameHeight,gameWidth);
                bullets.add(newBullet);
                lastBulletFired = System.currentTimeMillis();
                /*randomSpawnGenerator();
                enemies.add(new Enemy(spawnEnemyX,spawnEnemyY));*/
            }
        }
    }
    
    private void updateEnemies(){
        boolean mayMove = true;
        
        for(Enemy currentEnemy: enemies){
            mayMove = true;
            for(Enemy otherEnemy: enemies){
                if(currentEnemy.getBounds().intersects(otherEnemy.getBounds())){
                double distanceCurrentEnemy = Math.sqrt((currentEnemy.getPosX() - player.getPosX())^2 + (currentEnemy.getPosY() - player.getPosY())) ;
                double distanceOtherEnemy = Math.sqrt((otherEnemy.getPosX() - player.getPosX())^2 + (otherEnemy.getPosY() - player.getPosY())) ;
                
                if(distanceCurrentEnemy>distanceOtherEnemy){
                    mayMove = false;
                }
                }
                }
        if(mayMove){
            currentEnemy.updatePos(player.getPosX(),player.getPosY());
        }
        }
        
        
        
//        for(Enemy enemy: enemies){
//            enemy.updatePos(player.getPosX(),player.getPosY());
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
                        player.addPoints(enemy.getValue());
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
        
        /*enemies vs player detection*/
        Enemy hittedChar = null;
        for(Enemy enemy: enemies){
            if(player.getBounds().intersects(enemy.getBounds())){
                player.lifeLess();
                hittedChar = enemy;
            }
        }
        if(hittedChar!= null){
            enemies.remove(hittedChar);
        }
        
        
        /* player vs geoms */
        List<Geom> hittedGeoms = new ArrayList();
        for(Geom geom: geoms){
            if(player.getBounds().intersects(geom.getBounds())){
                player.addGeom();
                System.out.println(player.getNumberOfGeoms());
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
    
    /*private void sendDataToClient() throws IOException{
        server.sendDataToClient(player, bullets, enemies, geoms);
    }*/

    
}
