package model.companions;

import java.awt.Rectangle;
import model.Character;

public class LifeSaver implements Companion{
    private long timeWhenLifeNeedsToBeAdded;
    private long timeBetweenLifes;
    private Character character;
    int width;
    int height;
    int posX;
    int posY;
    String sprite;
    
    public LifeSaver(Character character, int width, int height, int livesPerMinute, String sprite) {
        
        this.width = width;
        this.height = height;
        this.character = character;
        this.sprite = sprite;
        
        timeBetweenLifes = (60 *1000) / livesPerMinute;
        timeWhenLifeNeedsToBeAdded = System.currentTimeMillis() + timeBetweenLifes;
        
        
        posX = character.getPosX();
        posY = character.getPosY();
    }

  
    
    public void doSpecialAction(){
        if(timeWhenLifeNeedsToBeAdded<= System.currentTimeMillis()){
            character.addLife();
            timeWhenLifeNeedsToBeAdded += timeBetweenLifes;
        }
    }

    @Override
    public void doMove() {
        
        this.posX = character.getPosX();
        this.posY = character.getPosY();
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
    
    @Override
    public String getSprite() {
        return this.sprite;
    }
                
}
