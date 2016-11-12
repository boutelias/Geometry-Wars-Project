
package model;

import java.awt.Rectangle;


public class Mine {
   private int width;
   private int height;
   private int damage;
   private int posX;
   private int posY;
   
   
   public Mine(int posX,int posY,int damage){
       this.posX = posX;
       this.posY = posY;
       this.damage = damage;
       width = 10;
       height = 10;
   }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getDamage() {
        return damage;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
   
    public Rectangle getBounds(){
        return new Rectangle(getPosX()-(width/2),getPosY()-(height/2),width,height);
    }
}
