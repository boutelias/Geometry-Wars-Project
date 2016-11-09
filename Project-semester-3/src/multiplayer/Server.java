package multiplayer;



import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.ArrayList;
import model.Bullet;
import model.Enemy;
import model.Geom;
import model.Player;

/**
 *
 * @author Tobias
 */
public class Server {
    
    private ServerSocket serverSocket;
    private Socket socket;
    private ObjectOutputStream out;
    DataForClient data = new DataForClient();
    
    public Server() throws IOException{
        
        serverSocket = new ServerSocket(7777);
    }
    
    public void sendDataToClient(Player player, List<Bullet> bullets, List<Enemy> enemys, List<Geom> geoms) throws IOException{
        socket = serverSocket.accept();
        data.updateDataForClient(player, bullets, enemys, geoms);
        out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(data);
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