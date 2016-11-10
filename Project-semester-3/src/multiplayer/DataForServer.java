/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiplayer;

import java.awt.event.MouseEvent;



class DataForServer {
    
    boolean[] keys = new boolean[256];
    boolean[] mouse = new boolean[4];
    MouseEvent[] mEvent = new MouseEvent[4];

    public DataForServer(){
    
    
    }
    
    public void updateDataForServer(boolean[] keys,boolean[] mouse,MouseEvent[] mEvent ){
        this.keys = keys;
        this.mouse = mouse;
        this.mEvent = mEvent;   
    }
    
    /*Getters and setters*/
    public boolean[] getKeys() {
        return keys;
    }

    public boolean[] getMouse() {
        return mouse;
    }

    public MouseEvent[] getmEvent() {
        return mEvent;
    }
    
    

    
}
