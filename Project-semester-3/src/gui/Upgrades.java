/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author Maxime
 */
public class Upgrades {
    
    private int leveloflives;
    private int leveloffirerate;
    private int levelofmovementspeed;
    private int levelofbulletdamage;
    private int levelofbulletspeed;
    
    
    public Upgrades(int leveloflives, int leveloffirerate, int levelofmovementspeed, int levelofbulletdamage, int levelofbulletspeed){
    
        this.leveloflives = leveloflives;
        this.leveloffirerate = leveloffirerate;
        this.levelofmovementspeed = levelofmovementspeed;
        this.levelofbulletdamage = levelofbulletdamage;
        this.levelofbulletspeed = levelofbulletspeed;
        
    }

    public int getLeveloflives() {
        return leveloflives;
    }

    public int getLeveloffirerate() {
        return leveloffirerate;
    }

    public int getLevelofmovementspeed() {
        return levelofmovementspeed;
    }

    public int getLevelofbulletdamage() {
        return levelofbulletdamage;
    }

    public int getLevelofbulletspeed() {
        return levelofbulletspeed;
    }
    
    
    
}
