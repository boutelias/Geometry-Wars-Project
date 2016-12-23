/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import gui.Upgrades;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Bullet;
import model.Character;
import model.Enemy;
import model.InputHandler;
import model.Mine;
import model.Player;
import model.Wave;
import model.companions.AutoShooter;
import model.companions.Companion;
import gui.CompanionShop;
import model.companions.LifeSaver;
import model.companions.Miner;
import model.companions.Shooter;
import util.GameException;

/**
 *
 * @author Elias
 */
public class GameDA {

    private static final String URL = "jdbc:mysql://sql7.freemysqlhosting.net/sql7150035";
    private static final String USER = "sql7150035";
    private static final String PASSWORD = "xgxNJBEPGP";

    private static GameDA instance;
    private Connection con;

    private GameDA() {
        this.registerDriver();
        this.openConnection();
    }

    private void registerDriver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new GameException("Unable to load MySQL driver.", ex);
        }
    }

    private void openConnection() {
        try {
            this.con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException ex) {
            throw new GameException("Unable to open connection.", ex);
        }
    }

    public static GameDA getInstance() {
        if (instance == null) {
            instance = new GameDA();
        }

        return instance;
    }

    public List<Player> getPlayers() {
        List<Player> players = new ArrayList<>();
        try {
            String sql = "select * from `Player`";
            PreparedStatement prep = this.con.prepareStatement(sql);
            ResultSet rs = prep.executeQuery(sql);

            while (rs.next()) {
                Player player = new Player(rs.getString("username"), rs.getString("password"), rs.getInt("highscore"), rs.getInt("geoms"), rs.getInt("premiumcoins"));
                players.add(player);
            }
            rs.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return players;
    }

    public Player getPlayer(String username) {
        Player p;
        try {
            String sql = "select * from `Player` where username= ?";
            PreparedStatement prep = this.con.prepareStatement(sql);
            prep.setString(1, username);
            ResultSet rs = prep.executeQuery();
            rs.next();
            p = new Player(rs.getString("username"), rs.getString("password"), rs.getInt("highscore"), rs.getInt("geoms"), rs.getInt("premiumcoins"));

            rs.close();
        } catch (SQLException ex) {
            throw new IllegalArgumentException(ex);

        }
        return p;
    }

    public int getPLayerId(String playername) {
        try {
            String sql = "select * from `Player` where username= ?";
            PreparedStatement prep = this.con.prepareStatement(sql);
            prep.setString(1, playername);
            ResultSet rs = prep.executeQuery();
            rs.next();
            int id = rs.getInt("playerid");
            rs.close();

            return id;
        } catch (SQLException ex) {
            throw new IllegalArgumentException(ex);

        }

    }

    public Character getCharacter(int characterid, int maxX, int maxY) {
        Character c;
        try {
            String sql = "SELECT sprite,firerate,movementspeed,height,width,bulletdamage,bulletspeed,numberOfLives\n"
                    + "from sql7150035.Character \n"
                    + "join player_character on sql7150035.Character.characterid = player_character.characterid\n"
                    + "join characterlives on player_character.leveloflives = characterlives.characterliveslevel\n"
                    + "join characterfirerate on player_character.leveloffirerate = characterfirerate.characterfireratelevel\n"
                    + "join charactermovementspeed on player_character.levelofmovementspeed = charactermovementspeed.charactermovementspeedid\n"
                    + "join characterbulletdamage on player_character.levelofbulletdamage = characterbulletdamage.characterbulletdamageid\n"
                    + "join characterbulletspeed on player_character.levelofbulletspeed = characterbulletspeed.characterbulletspeedid\n"
                    + "where playerid = 2 and sql7150035.Character.characterid = 1";
            PreparedStatement prep = this.con.prepareStatement(sql);
            //prep.setInt(1,characterid);

            ResultSet rs = prep.executeQuery();
            rs.next();
            c = new Character(maxX, maxY, rs.getInt("numberoflives"), rs.getString("sprite"), rs.getInt("firerate"), rs.getInt("movementspeed"), rs.getInt("height"), rs.getInt("width"), rs.getInt("bulletdamage"), rs.getInt("bulletspeed"));

            prep.close();
            rs.close();
        } catch (SQLException ex) {
            throw new IllegalArgumentException(ex);
        }
        return c;
    }

    public List<Wave> getWaves() {
        List<Wave> waves = new ArrayList<>();
        try {
            String sql = "Select * from `Wave`";
            PreparedStatement prep = this.con.prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                Wave wave = new Wave(rs.getInt("numberofenemies"), rs.getInt("spawndelay"), rs.getInt("enemyid"), rs.getInt("timeafterwave"));
                waves.add(wave);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GameDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return waves;
    }

    public Enemy getEnemy(int enemyid, int posx, int posy) {
        Enemy e;
        try {
            String sql = "select * from `Enemy` where enemyid = ? ";
            PreparedStatement prep = this.con.prepareStatement(sql);
            prep.setInt(1, enemyid);
            ResultSet rs = prep.executeQuery();
            rs.next();
            e = new Enemy(posx, posy, rs.getString("sprite"), rs.getDouble("dropratepowerups"), rs.getDouble("dropratepowerdowns"), rs.getDouble("droprategeoms"), rs.getInt("width"), rs.getInt("height"), rs.getInt("hp"), rs.getInt("movementspeed"), rs.getInt("value"));
        } catch (SQLException ex) {
            throw new IllegalArgumentException(ex);
        }
        return e;
    }

    public List<Player> getTop10HighscorePlayers() {
        List<Player> players = new ArrayList<>();
        try {
            String sql = "SELECT * FROM `Player` order by highscore desc limit 10";
            PreparedStatement prep = this.con.prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while (rs.next()) {
                Player player = new Player(rs.getString("username"), rs.getString("password"), rs.getInt("highscore"), rs.getInt("geoms"), rs.getInt("premiumcoins"));
                players.add(player);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GameDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        return players;
    }

    public void addNewPlayer(String username, String password) {
        try {
            String sql = "insert into `Player`(username,password,highscore,premiumcoins,geoms) Values(?,?,0,0,0)";
            PreparedStatement prep = this.con.prepareStatement(sql);
            prep.setString(1, username);
            prep.setString(2, password);
            prep.executeUpdate();
            prep.close();
        } catch (SQLException ex) {
            Logger.getLogger(GameDA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Companion getCompanionAutoShooter(int playerid, Character character, List<Enemy> enemies, List<Bullet> bullets) {
        Companion c;
        try {
            String sql = "SELECT * FROM `player_companion`\n"
                    + "join companionlevel on player_companion.level = companionlevel.companionlevelid\n"
                    + "join Companion on player_companion.companionid = Companion.companionid\n"
                    + "where player_companion.companionid = 1 and playerid = ?";
            PreparedStatement prep = this.con.prepareStatement(sql);
            prep.setInt(1, playerid);
            ResultSet rs = prep.executeQuery();
            rs.next();
            c = new AutoShooter(character, bullets, enemies, rs.getInt("height"), rs.getInt("width"), rs.getInt("bulletdamage"), rs.getInt("bulletsPerMinute"), rs.getInt("bulletspeed"));

        } catch (SQLException ex) {
            throw new IllegalArgumentException(ex);
        }
        return c;
    }

    public Companion getCompanionLifeSaver(int playerid, Character character) {
        Companion c;
        try {
            String sql = "SELECT * FROM `player_companion`\n"
                    + "join companionlevel on player_companion.level = companionlevel.companionlevelid\n"
                    + "join Companion on player_companion.companionid = Companion.companionid\n"
                    + "where player_companion.companionid = 2 and playerid = ?";
            PreparedStatement prep = this.con.prepareStatement(sql);
            prep.setInt(1, playerid);
            ResultSet rs = prep.executeQuery();
            rs.next();
            c = new LifeSaver(character, rs.getInt("width"), rs.getInt("height"), rs.getInt("livesperminute"));
        } catch (SQLException ex) {
            throw new IllegalArgumentException(ex);
        }
        return c;
    }

    public Companion getCompanionMiner(int playerid, Character character, List<Mine> mines, InputHandler handler) {
        Companion c;
        try {
            String sql = "SELECT * FROM `player_companion`\n"
                    + "join companionlevel on player_companion.level = companionlevel.companionlevelid\n"
                    + "join Companion on player_companion.companionid = Companion.companionid\n"
                    + "where player_companion.companionid = 3 and playerid = ?";
            PreparedStatement prep = this.con.prepareStatement(sql);
            prep.setInt(1, playerid);
            ResultSet rs = prep.executeQuery();
            rs.next();
            c = new Miner(character, mines, handler, rs.getInt("width"), rs.getInt("height"), rs.getInt("minesperminute"), rs.getInt("minedamage"));
        } catch (SQLException ex) {
            throw new IllegalArgumentException(ex);
        }
        return c;

    }

    public Companion getCompanionShooter(int playerid, Character character, InputHandler handler, List<Bullet> bullets) {
        Companion c;
        try {
            String sql = "SELECT * FROM `player_companion`\n"
                    + "join companionlevel on player_companion.level = companionlevel.companionlevelid\n"
                    + "join Companion on player_companion.companionid = Companion.companionid\n"
                    + "where player_companion.companionid = 4 and playerid = ?";
            PreparedStatement prep = this.con.prepareStatement(sql);
            prep.setInt(1, playerid);
            ResultSet rs = prep.executeQuery();
            rs.next();
            c = new Shooter(character, handler, bullets, rs.getInt("height"), rs.getInt("width"), rs.getInt("bulletdamage"), rs.getInt("bulletsperminute"), rs.getInt("bulletspeed"));
        } catch (SQLException ex) {
            throw new IllegalArgumentException(ex);
        }
        return c;
    }

    public List<Character> getShips() {
        List<Character> characters = new ArrayList<>();
        try {
            String sql = "select * from `Character`";
            PreparedStatement prep = this.con.prepareStatement(sql);
            ResultSet rs = prep.executeQuery(sql);

            while (rs.next()) {
                Character character = new Character(rs.getInt("characterid"), rs.getString("sprite"), rs.getInt("price"));
                characters.add(character);
            }
            rs.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return characters;
    }
    
    public List<CompanionShop> getCompanions(){
         List<CompanionShop> companions = new ArrayList<>();
        try {
            String sql = "select * from `Companion`";
            PreparedStatement prep = this.con.prepareStatement(sql);
            ResultSet rs = prep.executeQuery(sql);

            while (rs.next()) {
                CompanionShop companion = new CompanionShop(rs.getInt("companionid"), rs.getString("sprite"), rs.getInt("price"));
                companions.add(companion);
            }
            rs.close();

        } catch (SQLException ex) {
            System.err.println(ex);
        }
        return companions;

    }

    public int isBought(int playerid, int characterid) {

        try {
            String sql = "SELECT * FROM player_character WHERE playerid=? AND characterid =?";
            PreparedStatement prep = this.con.prepareStatement(sql);
            prep.setInt(1, playerid);
            prep.setInt(2, characterid);
            ResultSet rs = prep.executeQuery();
            rs.next();
            int isbought = rs.getInt("isBought");
            rs.close();

            return isbought;
        } catch (SQLException ex) {
            throw new IllegalArgumentException(ex);

        }

    }
    
    public int isBoughtCompanion(int playerid, int companionid) {

        try {
            String sql = "SELECT * FROM player_companion WHERE playerid=? AND companionid =?";
            PreparedStatement prep = this.con.prepareStatement(sql);
            prep.setInt(1, playerid);
            prep.setInt(2, companionid);
            ResultSet rs = prep.executeQuery();
            rs.next();
            int isbought = rs.getInt("isBought");
            rs.close();

            return isbought;
        } catch (SQLException ex) {
            throw new IllegalArgumentException(ex);

        }

    }

    public int getGeoms(int playerid) {
        try {
            String sql = "SELECT * FROM Player WHERE playerid=?";
            PreparedStatement prep = this.con.prepareStatement(sql);
            prep.setInt(1, playerid);
            ResultSet rs = prep.executeQuery();
            rs.next();
            int geoms = rs.getInt("geoms");
            rs.close();

            return geoms;
        } catch (SQLException ex) {
            throw new IllegalArgumentException(ex);

        }
    }

    public int getPremiumcoins(int playerid) {
        try {
            String sql = "SELECT * FROM Player WHERE playerid=?";
            PreparedStatement prep = this.con.prepareStatement(sql);
            prep.setInt(1, playerid);
            ResultSet rs = prep.executeQuery();
            rs.next();
            int premiumcoins = rs.getInt("premiumcoins");
            rs.close();

            return premiumcoins;
        } catch (SQLException ex) {
            throw new IllegalArgumentException(ex);

        }
    }

    public void buyCharacter(int playerid, int characterid) {

        try {
            String sql = "UPDATE player_character SET isBought=? WHERE playerid=? AND characterid =?";

            PreparedStatement prep = this.con.prepareStatement(sql);
            prep.setInt(1, 1);
            prep.setInt(2, playerid);
            prep.setInt(3, characterid);
            prep.executeUpdate();

        } catch (SQLException ex) {
            throw new IllegalArgumentException(ex);

        }

    }
    
    public void buyCompanion(int playerid, int companionid) {

        try {
            String sql = "UPDATE player_companion SET isBought=? WHERE playerid=? AND companionid =?";

            PreparedStatement prep = this.con.prepareStatement(sql);
            prep.setInt(1, 1);
            prep.setInt(2, playerid);
            prep.setInt(3, companionid);
            prep.executeUpdate();

        } catch (SQLException ex) {
            throw new IllegalArgumentException(ex);

        }

    }

    public void setNewGeomBalance(int newBalance, int playerid) {
        try {
            String sql = "UPDATE Player SET geoms=? WHERE playerid=?";

            PreparedStatement prep = this.con.prepareStatement(sql);
            prep.setInt(1, newBalance);
            prep.setInt(2, playerid);
            prep.executeUpdate();

        } catch (SQLException ex) {
            throw new IllegalArgumentException(ex);

        }

    }

    public Upgrades getUpgrades(int playerid, int characterid) {
        try {
            String sql = "SELECT * FROM player_character WHERE playerid=? AND characterid =?";
            PreparedStatement prep = this.con.prepareStatement(sql);
            prep.setInt(1, playerid);
            prep.setInt(2, characterid);
            ResultSet rs = prep.executeQuery();
            rs.next();
            Upgrades upgrades = new Upgrades(rs.getInt("leveloflives"), rs.getInt("leveloffirerate"), rs.getInt("levelofmovementspeed"), rs.getInt("levelofbulletdamage"), rs.getInt("levelofbulletspeed"));
            rs.close();

            return upgrades;
        } catch (SQLException ex) {
            throw new IllegalArgumentException(ex);

        }
    }
    
    public int getUpgradesCompanion(int playerid, int companionid){
        try {
            String sql = "SELECT * FROM player_companion WHERE playerid=? AND companionid =?";
            PreparedStatement prep = this.con.prepareStatement(sql);
            prep.setInt(1, playerid);
            prep.setInt(2, companionid);
            ResultSet rs = prep.executeQuery();
            rs.next();
            int level = rs.getInt("level");
            rs.close();

            return level;
        } catch (SQLException ex) {
            throw new IllegalArgumentException(ex);

        }
        
        
            };

    public int getPriceLeveloflives(int leveloflives) {
        try {
            String sql = "SELECT * FROM characterlives WHERE characterliveslevel=?";
            PreparedStatement prep = this.con.prepareStatement(sql);
            prep.setInt(1, leveloflives);
            ResultSet rs = prep.executeQuery();
            rs.next();
            int price = rs.getInt("price");
            rs.close();

            return price;
        } catch (SQLException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    public int getPriceLeveloffirerate(int leveloffirerate) {
        try {
            String sql = "SELECT * FROM characterfirerate WHERE characterfireratelevel=?";
            PreparedStatement prep = this.con.prepareStatement(sql);
            prep.setInt(1, leveloffirerate);
            ResultSet rs = prep.executeQuery();
            rs.next();
            int price = rs.getInt("price");
            rs.close();

            return price;
        } catch (SQLException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    public int getPriceLevelofmovementspeed(int levelofmovementspeed) {
        try {
            String sql = "SELECT * FROM charactermovementspeed WHERE charactermovementspeedid=?";
            PreparedStatement prep = this.con.prepareStatement(sql);
            prep.setInt(1, levelofmovementspeed);
            ResultSet rs = prep.executeQuery();
            rs.next();
            int price = rs.getInt("price");
            rs.close();

            return price;
        } catch (SQLException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    public int getPriceLevelofbulletspeed(int levelofbulletspeed) {
        try {
            String sql = "SELECT * FROM characterbulletspeed WHERE characterbulletspeedid=?";
            PreparedStatement prep = this.con.prepareStatement(sql);
            prep.setInt(1, levelofbulletspeed);
            ResultSet rs = prep.executeQuery();
            rs.next();
            int price = rs.getInt("price");
            rs.close();

            return price;
        } catch (SQLException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    public int getPriceLevelofbulletdamage(int levelofbulletdamage) {
        try {
            String sql = "SELECT * FROM characterbulletdamage WHERE characterbulletdamageid=?";
            PreparedStatement prep = this.con.prepareStatement(sql);
            prep.setInt(1, levelofbulletdamage);
            ResultSet rs = prep.executeQuery();
            rs.next();
            int price = rs.getInt("price");
            rs.close();

            return price;
        } catch (SQLException ex) {
            throw new IllegalArgumentException(ex);
        }
    }
    
    public int getPriceLevelofCompanion(int level) {
        try {
            String sql = "SELECT * FROM companionlevel WHERE companionlevelid =?";
            PreparedStatement prep = this.con.prepareStatement(sql);
            prep.setInt(1, level);
            ResultSet rs = prep.executeQuery();
            rs.next();
            int price = rs.getInt("price");
            rs.close();

            return price;
        } catch (SQLException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    public void setMovementspeedUpgrade(int playerid, int charCounter, int lvl) {

        try {
            String sql = "UPDATE player_character SET levelofmovementspeed = ? WHERE playerid=? AND characterid = ?";

            PreparedStatement prep = this.con.prepareStatement(sql);
            prep.setInt(1, lvl);
            prep.setInt(2, playerid);
            prep.setInt(3, charCounter);
            prep.executeUpdate();

        } catch (SQLException ex) {
            throw new IllegalArgumentException(ex);

        }
    }
    
    public void setBulletspeedUpgrade(int playerid, int charCounter, int lvl) {

        try {
            String sql = "UPDATE player_character SET levelofbulletspeed = ? WHERE playerid=? AND characterid = ?";

            PreparedStatement prep = this.con.prepareStatement(sql);
            prep.setInt(1, lvl);
            prep.setInt(2, playerid);
            prep.setInt(3, charCounter);
            prep.executeUpdate();

        } catch (SQLException ex) {
            throw new IllegalArgumentException(ex);

        }
    }
    
    public void setBulletdamageUpgrade(int playerid, int charCounter, int lvl) {

        try {
            String sql = "UPDATE player_character SET levelofbulletdamage = ? WHERE playerid=? AND characterid = ?";

            PreparedStatement prep = this.con.prepareStatement(sql);
            prep.setInt(1, lvl);
            prep.setInt(2, playerid);
            prep.setInt(3, charCounter);
            prep.executeUpdate();

        } catch (SQLException ex) {
            throw new IllegalArgumentException(ex);

        }
    }
    
    public void setFirerateUpgrade(int playerid, int charCounter, int lvl) {

        try {
            String sql = "UPDATE player_character SET leveloffirerate = ? WHERE playerid=? AND characterid = ?";

            PreparedStatement prep = this.con.prepareStatement(sql);
            prep.setInt(1, lvl);
            prep.setInt(2, playerid);
            prep.setInt(3, charCounter);
            prep.executeUpdate();

        } catch (SQLException ex) {
            throw new IllegalArgumentException(ex);

        }
    }
    
    public void setLivesUpgrade(int playerid, int charCounter, int lvl) {

        try {
            String sql = "UPDATE player_character SET leveloflives = ? WHERE playerid=? AND characterid = ?";

            PreparedStatement prep = this.con.prepareStatement(sql);
            prep.setInt(1, lvl);
            prep.setInt(2, playerid);
            prep.setInt(3, charCounter);
            prep.executeUpdate();

        } catch (SQLException ex) {
            throw new IllegalArgumentException(ex);

        }
    }
    
    public void setCompanionUpgrade(int playerid, int companionCounter, int lvl) {

        try {
            String sql = "UPDATE player_companion SET level = ? WHERE playerid=? AND companionid = ?";

            PreparedStatement prep = this.con.prepareStatement(sql);
            prep.setInt(1, lvl);
            prep.setInt(2, playerid);
            prep.setInt(3, companionCounter);
            prep.executeUpdate();

        } catch (SQLException ex) {
            throw new IllegalArgumentException(ex);

        }
    }
    public void setHighscorePlayer(int playerid,long score){
        try {
            String sql = "update `Player` set highscore= ? where playerid = ?";
            PreparedStatement prep = this.con.prepareStatement(sql);
            prep.setLong(1, score);
            prep.setInt(2, playerid);
            prep.executeUpdate();
            prep.close();
        } catch (SQLException ex) {
            Logger.getLogger(ExtraDA.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public Player getPlayer(int playerid){
        Player p;
        try {
            String sql = "select * from `Player` where playerid = ?";
            PreparedStatement prep = this.con.prepareStatement(sql);
            prep.setInt(1, playerid);
            ResultSet rs = prep.executeQuery();
            rs.next();
            p = new Player(rs.getString("username"), rs.getString("password"), rs.getInt("highscore"), rs.getInt("geoms"), rs.getInt("premiumcoins"));
        } catch (SQLException ex) {
            throw new IllegalArgumentException(ex);
        }
    return p;
    }
    public int getPower(String name){
        int i;    
        try {
            String sql = "select value from `powerup` where powerupname = ?";
            PreparedStatement prep = this.con.prepareStatement(sql);
            prep.setString(1, name);
            ResultSet rs = prep.executeQuery();
            rs.next();
            i = rs.getInt("value");
        } catch (SQLException ex) {
            throw new IllegalArgumentException(ex);
        }
     return i;   
    }

}
