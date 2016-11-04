/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Tobias
 */
public class InputHandler {
    
    boolean[] keys = new boolean[256];
    boolean[] mouse = new boolean[4];
    MouseEvent[] mEvent = new MouseEvent[4];
    
    
    public InputHandler(Component c){
        c.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                keys[e.getKeyCode()] = true;
            }

            @Override
            public void keyReleased(KeyEvent e) {
                keys[e.getKeyCode()] = false;
            }
            
        });
        
        c.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                mouse[e.getButton()] = true;
                mEvent[e.getButton()] = e;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mouse[e.getButton()] = false;
                mEvent[e.getButton()] = e;
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent me) {}
        });
    }
    
    public boolean isKeyDown(int keycode){
        if(keycode>=0 && keycode<256){
            return keys[keycode];
        }
        return false;
    }
    
    public boolean isMouseDown(int button){
        if(button>=0 && button<4){
            return mouse[button];
        }
        return false;
    }
    
    public MouseEvent getEvent(int event){
        if(event>=0 && event<4){
            return mEvent[event];
        }
        return null;
    }
}
