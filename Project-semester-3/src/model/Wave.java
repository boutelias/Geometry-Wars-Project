
package model;

import java.io.Serializable;
import java.util.List;


public class Wave implements Serializable{
    
    private int numberOfEnemiesLeft;
    private int spawnRate;
    private List<Enemy> typesEnemy;
    private int delayToNextWave;
    
    public Wave(int numberOfEnemies,int spawnRate , List<Enemy> typesEnemy, int delayToNextWave){
        this.numberOfEnemiesLeft = numberOfEnemies;
        this.spawnRate = spawnRate;
        this.typesEnemy = typesEnemy;
        this.delayToNextWave = delayToNextWave;
    }
    
    public void reduceNumberOfEnemiesLeft(){
        numberOfEnemiesLeft --;
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
        return spawnRate * 1000;
    }
    
}
