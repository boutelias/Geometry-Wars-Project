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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Player;
import util.GameException;

/**
 *
 * @author Elias
 */
public class ExtraDA {
    private static final String URL = "jdbc:mysql://sql7.freemysqlhosting.net/sql7150035";
    private static final String USER = "sql7150035";
    private static final String PASSWORD = "xgxNJBEPGP";
    
    private static ExtraDA instance;
    private Connection con;
    
    private ExtraDA()
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
    
    public static ExtraDA getInstance()
    {
        if (instance == null)
        {
            instance = new ExtraDA();
        }
        
        return instance;
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
}
