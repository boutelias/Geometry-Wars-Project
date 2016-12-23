
package model.power;

import java.awt.Rectangle;
import model.Character;


public interface Power{
    
    public void start(Character c);
    public boolean isTheEnd();
    public boolean isPickedUp();
    public int getPosX();
    public int getPosY();
    public Rectangle getBounds();
    public String getName();
    public int getTimeLeft();
}
