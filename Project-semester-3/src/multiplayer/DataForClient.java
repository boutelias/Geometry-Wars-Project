package multiplayer;

import java.io.Serializable;
import java.util.List;
import model.Bullet;
import model.Enemy;
import model.Geom;
import model.Mine;
import model.Player;
import model.companions.Companion;



public class DataForClient implements Serializable{
    private Player player;
    private List<Bullet> bullets;
    private List<Enemy> enemies;
    private List<Geom> geoms;
    private Companion companion;
    private List<Mine> mines;
    public DataForClient(){
        
    }
    
    public void updateDataForClient(Player player, List<Bullet> bullets, List<Enemy> enemies, List<Geom> geoms, Companion companion,List<Mine> mines){
        this.player = player;
        this.bullets = bullets;
        this.enemies = enemies;
        this.geoms = geoms;
        this.companion = companion;
        this.mines = mines;
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

    List<Mine> getMines() {
        
        return mines;
    }
    
}
