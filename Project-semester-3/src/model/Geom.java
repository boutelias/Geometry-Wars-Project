
package model;

import java.awt.Rectangle;
import java.io.Serializable;

public class Geom implements Serializable {
    private int posX;
    private int posY;
    
    private int width = 5;
    private int height = 5;
    
    public Geom(int posX, int posY){
        this.posX = posX;
        this.posY = posY;
    }
    
    /* Getters and setters */
    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
    public Rectangle getBounds(){
        return new Rectangle(getPosX()-(width/2),getPosY()-(height/2),width,height);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    
}
