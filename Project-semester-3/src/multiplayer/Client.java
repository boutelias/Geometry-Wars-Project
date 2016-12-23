package multiplayer;

import com.sun.glass.events.KeyEvent;
import data.ExtraDA;
import gui.GameGui;
import gui.GameOverFrame;
import java.awt.Frame;
import java.awt.event.MouseEvent;
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
    private GameGui gameGui;
    private InputHandler handler;
    private DataForClient dataForClient;
    private DataForServer dataForServer;
    private DataForServer dataOut;
    private MouseEvent m;
    private boolean keyLeft;
    private boolean keyRight;
    private boolean keyUp;
    private boolean keyDown;
    private boolean leftClick;
    private long score;
    private int geoms;
    private int playerid;
    private ExtraDA ex = ExtraDA.getInstance();
    
    private boolean keepGoing = true;

    public Client(int playerid, int characterid,String ip) throws IOException {
        gameGui = new GameGui();
        handler = new InputHandler(gameGui.getFrame());
        this.playerid = playerid;

        dataOut = new DataForServer();

        System.out.println("Connecting ...");
        socket = new Socket(ip, 7777);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());

        System.out.println("Connection succesfull");
        System.out.println("Receiving information...");
        out.writeObject(new Integer(playerid));
        out.flush();
        
        out.writeObject(new Integer(characterid));
        out.flush();
        
    }

    public void run() {
        while (keepGoing) {
            try {
                
                m = handler.getEvent(1);
                keyLeft = handler.isKeyDown(KeyEvent.VK_LEFT);
                keyRight = handler.isKeyDown(KeyEvent.VK_RIGHT);
                keyUp = handler.isKeyDown(KeyEvent.VK_UP);
                keyDown = handler.isKeyDown(KeyEvent.VK_DOWN);
                leftClick = handler.isMouseDown(1);
                
                out.reset();
                
                
                dataOut.updateDataForServer(keyLeft, keyRight, keyUp, keyDown, leftClick, m);
                out.writeObject(dataOut);
                
                out.flush();
                dataForClient = (DataForClient) in.readObject();
                
                

                
                gameGui.draw(dataForClient.getPlayer(),dataForClient.getCharacter2(), dataForClient.getBullets(), dataForClient.getEnemies(), dataForClient.getGeoms(), dataForClient.getPowers());
                this.keepGoing = dataForClient.isKeepGoing();
                model.Character character2 = dataForClient.getCharacter2();
                score = character2.getScore();
                geoms = character2.getNumberOfGeoms();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Player p = ex.getPlayer(playerid);
        if(score > p.getHighscore()){
            ex.setHighscorePlayer(playerid, score);
        }
        Frame frame = new GameOverFrame(score,geoms);
        frame.setVisible(true);
        gameGui.deleteGame();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Client client = new Client(1,1,"172.31.28.31");
        client.run();
        
    }
}