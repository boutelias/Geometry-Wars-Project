
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Arne
 */
public class ScientistDA {

    private static ScientistDA instance;
    private static final String URL = "jdbc:mysql://localhost/crazyscientist";
    private static final String UID = "root";
    private static final String PWD = "";
    
    private Connection connection;
    private static String sql;
    
    private ScientistDA()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection(URL, UID, PWD);
        }
        catch (ClassNotFoundException ex)
        {
            throw new ScientistException("Unable to load database driver!", ex);
        }
        catch (SQLException ex)
        {
            throw new ScientistException("Unable to connect to database.", ex);
        }
        
    }
    
    public static ScientistDA getInstance()
    {
        if (instance == null)
        {
            instance = new ScientistDA();
        }
        
        return instance;
    }
    
    public User getLogin() throws SQLException{
        sql = "select username from player";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next())
        {
            String name	= rs.getString("username");
            String price = rs.getString("password");
            int idcategory = rs.getInt("playerid");
        }
        
        
        rs.close();
        stmt.close();
        
        return null;
    }
}
