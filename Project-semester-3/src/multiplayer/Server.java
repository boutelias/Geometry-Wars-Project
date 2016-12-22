package multiplayer;



import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
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
    private Character character2;
    private List<Bullet> bullets;
    private List<Enemy> enemys;
    private List<Geom> geoms;
    
    
    private boolean keyLeft;
    private boolean keyRight ;
    private boolean keyUp;
    private boolean keyDown ;
    private boolean leftClick;
    private MouseEvent clickLeft;
    
    
    public Server(Character player,Character character2, List<Bullet> bullets, List<Enemy> enemys, List<Geom> geoms) throws IOException {
        
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
        
        this.character2 = character2;
        keepGoing = true;
        
    }

    public void run(){
        System.out.println("in run");
        while(this.keepGoing){
            sendDataToClient();
            getDataFromClient();
        }
        sendDataToClient();
    }
    
    private void sendDataToClient(){
        //System.out.println(data.getCharacter2().getDamage());
        try {
            out.reset();
            data.updateDataForClient(player,character2, bullets, enemys, geoms,keepGoing);
            out.writeObject(data);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void getDataFromClient(){
        
        try {
            DataForServer data = (DataForServer) in.readObject();
            this.keyLeft = data.isKeyLeft();
            this.keyRight = data.isKeyRight();
            this.keyUp = data.isKeyUp();
            this.keyDown = data.isKeyDown();
            this.leftClick = data.isLeftClick();
            this.clickLeft = data.getClickLeft();
            System.out.println(clickLeft.getX());
            System.out.println(clickLeft.getY());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    /*Getters */
    public boolean isKeyLeft() {
        return keyLeft;
    }

    public boolean isKeyRight() {
        return keyRight;
    }

    public boolean isKeyUp() {
        return keyUp;
    }

    public boolean isKeyDown() {
        return keyDown;
    }

    public boolean isLeftClick() {
        return leftClick;
    }

    public MouseEvent getClickLeft() {
        return clickLeft;
    }
    
    public void SetKeepGoing(boolean keepGoing){
        this.keepGoing = keepGoing;
    }
    
    
}
