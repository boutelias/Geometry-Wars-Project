package multiplayer;



import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Bullet;
import model.Enemy;
import model.Geom;
import model.Mine;
import model.Player;
import model.companions.Companion;

/**
 *
 * @author Tobias
 */
public class Server {
    
    private ServerSocket serverSocket;
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private DataForServer dataFromClient;
    DataForClient data = new DataForClient();
    
    private Player player;
    private List<Bullet> bullets;
    private List<Enemy> enemys;
    private List<Geom> geoms;
    
    
    public Server(Player player, List<Bullet> bullets, List<Enemy> enemys, List<Geom> geoms) throws IOException{
        
        //to send data
        serverSocket = new ServerSocket(7777);
        
        System.out.println("servesocket made");
        System.out.println("waiting for connection");
        socket = serverSocket.accept();
        System.out.println("connection esthabelished");
        
        out = new ObjectOutputStream(socket.getOutputStream());    
        
        this.player = player;
        this.bullets = bullets;
        this.enemys = enemys;
        this.geoms = geoms;
        
    }

    public void sendDataToClient(){
        try {
            out.reset();
            System.out.println(player.getPosX());
            data.updateDataForClient(player, bullets, enemys, geoms);
            out.writeObject(data);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public DataForServer getDataForServer(){
        
        try {
            dataFromClient = (DataForServer) in.readObject();
            return dataFromClient;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return dataFromClient;
        }
        
        

    }
    
        /*while (true)
        {
            socket = serverSocket.accept();


            System.out.println("Connection from: "+ socket.getInetAddress());

            data.updateDataForClient(player, bullets, enemys, geoms);
            
            out = new ObjectOutputStream(socket.getOutputStream());

            out.writeObject(data);
            System.out.println("data send");
        }
    }*/
    
    
    
    
    
    
    
    
    /*public static void main(String[] args) throws IOException{
        Player player = new Player(1920,1080);
        Enemy enemy = new Enemy(200,200);
        Bullet bullet = new Bullet(250, 250, 300, 300, 5, 1080, 1920);
        Geom geom = new Geom(800,800);
        List<Enemy> enemys = new ArrayList();
        enemys.add(enemy);
        List<Bullet> bullets = new ArrayList();
        bullets.add(bullet);
        List<Geom> geoms = new ArrayList();
        geoms.add(geom);

        DataForClient data = new DataForClient();
            
        System.out.println("Starting server");
        
        
        System.out.println("Server started");
        
        
        */
    
}
