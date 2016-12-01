package model.companions;

import java.awt.Rectangle;
import model.Player;

public class LifeSaver implements Companion{
    private long timeWhenLifeNeedsToBeAdded;
    private long timeBetweenLifes;
    private Player player;
    int width;
    int height;
    int posX;
    int posY;
    
    public LifeSaver(Player player, int width, int height, int livesPerMinute) {
        this.player = player;
        this.width = width;
        this.height = height;
        
        timeBetweenLifes = (60 *1000) / livesPerMinute;
        timeWhenLifeNeedsToBeAdded = System.currentTimeMillis() + timeBetweenLifes;
        
        
        posX = player.getPosX();
        posY = player.getPosY();
    }

  
    
    public void doSpecialAction(){
        if(timeWhenLifeNeedsToBeAdded<= System.currentTimeMillis()){
            player.addLife();
            timeWhenLifeNeedsToBeAdded += timeBetweenLifes;
        }
    }

    @Override
    public void doMove() {
        this.posX = player.getPosX();
        this.posY = player.getPosY();
    }

    /* Getters and Setters */
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
