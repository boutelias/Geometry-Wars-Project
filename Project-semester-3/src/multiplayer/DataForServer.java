/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiplayer;

import com.sun.glass.events.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.Serializable;

public class DataForServer implements Serializable {

    private boolean keyLeft;
    private boolean keyRight ;
    private boolean keyUp;
    private boolean keyDown ;
    private boolean leftClick;
    private MouseEvent clickLeft;

    public DataForServer(){

    }
    
    public void updateDataForServer(boolean keyLeft,boolean keyRight,boolean keyUp,boolean keyDown,boolean leftClick,MouseEvent clickLeft){
        this.keyLeft = keyLeft;
        this.keyRight = keyRight;
        this.keyUp = keyUp;
        this.keyDown = keyDown;
        this.leftClick = leftClick;
        this.clickLeft = clickLeft;
    }

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
}