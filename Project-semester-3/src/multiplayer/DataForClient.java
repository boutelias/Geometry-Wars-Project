package multiplayer;

import java.io.Serializable;
import java.util.List;
import model.Bullet;
import model.Enemy;
import model.Geom;
import model.Character;
import model.Player;
import model.companions.Companion;



public class DataForClient implements Serializable{
    private Character player;
    private Character character2;
    private List<Bullet> bullets;
    private List<Enemy> enemies;
    private List<Geom> geoms;
    private boolean keepGoing = true;
    public DataForClient(){
        
    }
    
    public void updateDataForClient(Character player,Character character2, List<Bullet> bullets, List<Enemy> enemies, List<Geom> geoms, boolean keepGoing){
        this.player = player;
        this.character2 = character2;
        this.bullets = bullets;
        this.enemies = enemies;
        this.geoms = geoms;
        this.keepGoing = keepGoing;
    }

    /*Getters and setters*/
    public Character getPlayer() {
        return player;
    }
    
    public Character getCharacter2(){
        return character2;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<Geom> getGeoms() {
        return geoms;
    }
    
    public boolean isKeepGoing(){
        return this.keepGoing;
    }
}
