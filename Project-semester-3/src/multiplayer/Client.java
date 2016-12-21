package multiplayer;

import com.sun.glass.events.KeyEvent;
import gui.GameGui;
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

    public Client() throws IOException {
        gameGui = new GameGui();
        handler = new InputHandler(gameGui.getFrame());

        dataOut = new DataForServer();

        System.out.println("Connecting ...");
        socket = new Socket("localhost", 7777);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());

        System.out.println("Connection succesfull");
        System.out.println("Receiving information...");
    }

    public void run() {
        while (true) {
            try {
                //System.out.println("left: "+handler.isKeyDown(KeyEvent.VK_LEFT));
                m = handler.getEvent(1);
                keyLeft = handler.isKeyDown(KeyEvent.VK_LEFT);
                keyRight = handler.isKeyDown(KeyEvent.VK_RIGHT);
                keyUp = handler.isKeyDown(KeyEvent.VK_UP);
                keyDown = handler.isKeyDown(KeyEvent.VK_DOWN);
                leftClick = handler.isMouseDown(1);
                out.reset();
                //out.writeObject(handler);
                System.out.println("left : " + keyLeft);
                dataOut.updateDataForServer(keyLeft, keyRight, keyUp, keyDown, leftClick, m);
                out.writeObject(dataOut);
                //out.writeInt(15);
                out.flush();
                dataForClient = (DataForClient) in.readObject();
                //System.out.println("Data received");
                //System.out.println(data.getPlayer().getPosX());
                System.out.println(dataForClient.getCharacter2().getDamage());

                gameGui.draw(dataForClient.getPlayer(),dataForClient.getCharacter2(), dataForClient.getBullets(), dataForClient.getEnemies(), dataForClient.getGeoms());
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