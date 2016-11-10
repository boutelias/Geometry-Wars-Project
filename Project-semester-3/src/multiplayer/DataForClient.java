package multiplayer;

import java.io.Serializable;
import java.util.List;
import model.Bullet;
import model.Companion;
import model.Enemy;
import model.Geom;
import model.Player;



public class DataForClient implements Serializable{
    private Player player;
    private List<Bullet> bullets;
    private List<Enemy> enemies;
    private List<Geom> geoms;
    private Companion companion;
    
    public DataForClient(){
        
    }
    
    public void updateDataForClient(Player player, List<Bullet> bullets, List<Enemy> enemies, List<Geom> geoms, Companion companion){
        this.player = player;
        this.bullets = bullets;
        this.enemies = enemies;
        this.geoms = geoms;
        this.companion = companion;
    }

    /*Getters and setters*/
    public Player getPlayer() {
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

    public Companion getCompanion() {
        return companion;
    }
    
}
