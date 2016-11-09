package multiplayer;

import gui.GameGui;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Bullet;
import model.Enemy;
import model.Geom;
import model.Player;


public class Client {
    
    static Socket socket;
    static ObjectInputStream in;
    
    static private GameGui gameGui = new GameGui();
    
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        
        
        System.out.println("Connecting ...");
        socket = new Socket("172.31.16.35",7777);
        
        System.out.println("Connection succesfull");
        in = new ObjectInputStream(socket.getInputStream());
        
        System.out.println("Receiving information...");
              
        while(true){
            
            DataForClient data = (DataForClient) in.readObject();
            System.out.println("Reading data");
            Player player = data.getPlayer();
            List<Bullet> bullets = data.getBullets();
            List<Enemy> enemys = data.getEnemies();
            List<Geom> geoms = data.getGeoms();
            gameGui.draw( player , bullets, enemys, geoms);
            
        }
        //DataForClient data = (DataForClient) in.readObject();
        //Player player = (Player) in.readObject();
        //System.out.println(data.getPlayer().getPosX());
        
    }
}