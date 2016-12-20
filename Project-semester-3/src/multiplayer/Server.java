package multiplayer;



import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import model.Character;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.input.KeyCode;
import model.Bullet;
import model.Enemy;
import model.Geom;
import model.InputHandler;
import model.Mine;
import model.Player;
import model.companions.Companion;

/**
 *
 * @author Tobias
 */
public class Server implements Runnable{
    
    private ServerSocket serverSocket;
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private DataForServer dataFromClient;
    DataForClient data = new DataForClient();
    
    boolean keepGoing;
    
    private Character player;
    private List<Bullet> bullets;
    private List<Enemy> enemys;
    private List<Geom> geoms;
    
    InputHandler handler;
    
    
    public Server(Character player, List<Bullet> bullets, List<Enemy> enemys, List<Geom> geoms) throws IOException {
        
        //to send data
        serverSocket = new ServerSocket(7777);
        
        System.out.println("servesocket made");
        System.out.println("waiting for connection");
        socket = serverSocket.accept();
        System.out.println("connection esthabelished");
        
        out = new ObjectOutputStream(socket.getOutputStream());    
        in = new ObjectInputStream(socket.getInputStream());
        
        this.player = player;
        this.bullets = bullets;
        this.enemys = enemys;
        this.geoms = geoms;
        
        keepGoing = true;
        
    }

    public void run(){
        System.out.println("in run");
        while(this.keepGoing){
            sendDataToClient();
            getDataFromClient();
        }
    }
    
    private void sendDataToClient(){
        
        try {
            out.reset();
            data.updateDataForClient(player, bullets, enemys, geoms);
            out.writeObject(data);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void getDataFromClient(){
        
        try {
            DataForServer data = (DataForServer) in.readObject();
            System.out.println(data.isKeyLeft());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    public InputHandler getHandler(){
        return this.handler;
    }            
}
