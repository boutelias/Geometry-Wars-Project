
package model;

import java.io.Serializable;
import java.util.List;


public class Wave implements Serializable{
    
    private int numberOfEnemiesLeft;
    private int spawnRate;
    private int enemyid;
    private int delayToNextWave;
    private int numberOfEnemies;
    
    public Wave(int numberOfEnemies,int spawnRate , int enemyid, int delayToNextWave){
        this.numberOfEnemiesLeft = numberOfEnemies;
        this.numberOfEnemies = numberOfEnemies;
        this.spawnRate = spawnRate;
        this.enemyid = enemyid;
        this.delayToNextWave = delayToNextWave;
    }
    
    public void reduceNumberOfEnemiesLeft(){
        numberOfEnemiesLeft --;
    }
    
    public void addExtraEnemies(int extraEnemies){
        numberOfEnemiesLeft += extraEnemies;
    }
    
    
    
    public int getNumberOfEnemiesLeft(){
        return numberOfEnemiesLeft;
    }
    public int getSpawnRate(){
        return spawnRate;
    }
    
    public int getDelay(){
        return delayToNextWave;
    }
    
    public long getSpawnRateInMs(){
        return spawnRate;
    }
    public int getEnemyID(){
        return enemyid;
    }
    public void setEnemiesLeft(){
        numberOfEnemiesLeft = numberOfEnemies;
    }
    public void increaseEnemies(){
        numberOfEnemies = numberOfEnemies + 1;
    }
}
