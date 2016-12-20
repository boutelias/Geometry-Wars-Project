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
    private List<Bullet> bullets;
    private List<Enemy> enemies;
    private List<Geom> geoms;
    public DataForClient(){
        
    }
    
    public void updateDataForClient(Character player, List<Bullet> bullets, List<Enemy> enemies, List<Geom> geoms){
        this.player = player;
        this.bullets = bullets;
        this.enemies = enemies;
        this.geoms = geoms;
    }

    /*Getters and setters*/
    public Character getPlayer() {
        return player;
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
}
