package multiplayer;

import com.sun.glass.events.KeyEvent;
import gui.GameGui;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Enemy;
import model.InputHandler;
import model.Player;

public class Client {

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private GameGui gameGui ;
    private InputHandler handler;
    private DataForClient dataForClient;
    private DataForServer dataForServer;

    public Client() throws IOException {
        gameGui = new GameGui();
        handler = new InputHandler(gameGui.getFrame());
        
        
        System.out.println("Connecting ...");
        socket = new Socket("172.31.28.63", 7777);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());

        System.out.println("Connection succesfull");
        System.out.println("Receiving information...");
    }
    
    public void run(){
        while (true) {
            try {
                //System.out.println("left: "+handler.isKeyDown(KeyEvent.VK_LEFT));
                  out.reset();
                  out.writeObject(handler);
                //out.writeInt(15);
                  out.flush();
                dataForClient = (DataForClient) in.readObject();
                //System.out.println("Data received");
                //System.out.println(data.getPlayer().getPosX());

                gameGui.draw(dataForClient.getPlayer(), dataForClient.getBullets(), dataForClient.getEnemies(), dataForClient.getGeoms());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Client client = new Client();
        client.run();
    }
}