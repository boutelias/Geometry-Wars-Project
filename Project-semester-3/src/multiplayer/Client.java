package multiplayer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import model.Player;


public class Client {
    
    static Socket socket;
    static ObjectInputStream in;
    
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        
        
        System.out.println("Connecting ...");
        socket = new Socket("localhost",7777);
        
        System.out.println("Connection succesfull");
        in = new ObjectInputStream(socket.getInputStream());
        
        System.out.println("Receiving information...");
              
        Player player = (Player) in.readObject();
        System.out.println("String from server " + Integer.toString(player.getLives()));
        
    }
}