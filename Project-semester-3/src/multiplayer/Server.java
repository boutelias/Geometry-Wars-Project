package multiplayer;



import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import model.Player;

/**
 *
 * @author Tobias
 */
public class Server {
    
    static ServerSocket serverSocket;
    static Socket socket;
    static ObjectOutputStream out;
    
    public static void main(String[] args) throws IOException{
        
        System.out.println("Starting server");
        serverSocket = new ServerSocket(7777);
        
        System.out.println("Server started");
        socket = serverSocket.accept();
        
        socket.setKeepAlive(true);
        
        System.out.println("Connection from: "+ socket.getInetAddress());
        
        Player character = new Player(1920,1080);
        
        out = new ObjectOutputStream(socket.getOutputStream());
        
        out.writeObject(character);
        System.out.println("character send");
        
        System.out.println(socket.getKeepAlive());
    }
}
