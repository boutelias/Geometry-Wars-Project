package multiplayer;

import gui.GameGui;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Enemy;
import model.Player;


public class Client {
    
    static Socket socket;
    static ObjectInputStream in;
    
    static private GameGui gameGui = new GameGui();
    
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        
        
        System.out.println("Connecting ...");
        socket = new Socket("localhost",7777);
        
        System.out.println("Connection succesfull");
        in = new ObjectInputStream(socket.getInputStream());
        
        System.out.println("Receiving information...");
              
        while(true){
            try{
            DataForClient data = (DataForClient) in.readObject();
            
            gameGui.draw(data.getPlayer(), data.getBullets(), data.getEnemies(), data.getGeoms());
            }catch(Exception e){
                
            }
        }
        //DataForClient data = (DataForClient) in.readObject();
        //Player player = (Player) in.readObject();
        //System.out.println(data.getPlayer().getPosX());
        
    }
}