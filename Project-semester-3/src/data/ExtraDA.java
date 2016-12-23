/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
}
