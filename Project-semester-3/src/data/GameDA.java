/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

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
import model.Character;
import model.Player;
import model.Wave;
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
    
    private GameDA()
    {
        this.registerDriver();
        this.openConnection();
    }
    
    private void registerDriver()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException ex)
        {
            throw new GameException("Unable to load MySQL driver.", ex);
        }
    }
    
    private void openConnection()
    {
        try
        {
            this.con = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        catch (SQLException ex)
        {
            throw new GameException("Unable to open connection.", ex);
        }
    }
    
    public static GameDA getInstance()
    {
        if (instance == null)
        {
            instance = new GameDA();
        }
        
        return instance;
    }
    
    public List<Player> getPlayers(){
        List<Player> players = new ArrayList<>();
        try{
            String sql= "select * from `Player`";
            PreparedStatement prep = this.con.prepareStatement(sql);
            ResultSet rs = prep.executeQuery(sql);
            
            while(rs.next()){
                Player player = new Player(rs.getString("username"),rs.getString("password"),rs.getInt("highscore"),rs.getInt("geoms"),rs.getInt("premiumcoins"));
                players.add(player);
            }
            rs.close();
            
            
        }
        catch (SQLException ex){
            System.err.println(ex);
        }
        return players;
    }
    
    public Player getPlayer(String username){
        Player p ;
        try{
            String sql = "select * from `Player` where username= ?";
            PreparedStatement prep = this.con.prepareStatement(sql);
            prep.setString(1,username);
            ResultSet rs = prep.executeQuery();
            
            p = new Player(rs.getString("username"),rs.getString("password"),rs.getInt("highscore"),rs.getInt("geoms"),rs.getInt("premiumcoins"));
            
            rs.close();
            }    
        catch (SQLException ex){
            throw new IllegalArgumentException(ex);
            
        }
       return p; 
    }
    
   public Character getCharacter(int characterid,int maxX, int maxY){
       Character c;
         try {
             String sql = "SELECT * FROM `Character` WHERE characterid = ?";
             PreparedStatement prep = this.con.prepareStatement(sql);
             prep.setInt(1,characterid);
             ResultSet rs = prep.executeQuery();
             rs.next();
             c = new Character(maxX,maxY,rs.getInt("lives"),rs.getString("sprite"),rs.getInt("firerate"),rs.getInt("movementspeed"),rs.getInt("height"),rs.getInt("width"),rs.getInt("bulletdamage"),rs.getInt("bulletspeed"));
             
             prep.close();
             rs.close();
         } catch (SQLException ex) {
             throw new IllegalArgumentException(ex);
         }
         return c;
   }
   public List<Wave> getWaves(){
       List<Wave> waves = new ArrayList<>();
         try {
             String sql = "Select * from `Wave`";
             PreparedStatement prep = this.con.prepareStatement(sql);
             ResultSet rs = prep.executeQuery();
             while (rs.next()){
                 Wave wave = new Wave(rs.getInt("numberofenemies"),rs.getInt("spawndelay"),rs.getInt("enemyid"),rs.getInt("timeafterwave"));
                 waves.add(wave);
             } } catch (SQLException ex) {
             Logger.getLogger(GameDA.class.getName()).log(Level.SEVERE, null, ex);
         }
         return waves;
   }
   
    
}
