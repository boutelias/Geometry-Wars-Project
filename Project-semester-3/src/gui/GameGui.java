/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import model.Character;
import model.Bullet;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.swing.JFrame;
import model.Bullet;
import model.Enemy;
import model.InputHandler;

/**
 *
 * @author Tobias
 */
public class GameGui {
    
    JFrame frame;
    
    Graphics g;
    Graphics g2;
    BufferedImage i;
    
    int gameWidth;
    int gameHeight;
    Character character;
    List<Bullet> bullets;
        
    public GameGui(int gameWidth, int gameHeight){
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        
        frame = new JFrame("Crazy Scientist Game");
        frame.setSize(new Dimension(gameWidth,gameHeight));
        //frame.setLocation(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        
        
         //graphic stuff
        
        i = new BufferedImage(gameWidth,gameHeight, BufferedImage.TYPE_INT_RGB);
        g = i.getGraphics();
        g2 = frame.getGraphics();
        
    }
    
    public void draw(Character character, List<Bullet> bullets, List<Enemy> enemys){
        this.character = character;
        this.bullets = bullets;
        
        
        g.setColor(Color.black);
        g.fillRect(0, 0, gameWidth, gameHeight);
        
        g.setColor(Color.YELLOW);
        g.fillOval(character.getPosX()-(character.getHeight()/2), character.getPosY()-(character.getWidth()/2), character.getHeight(), character.getWidth());
        
        /*g.fillRect(10, 38, 10, 10);*/
        
        g.setColor(Color.green);
        
        for(Bullet bullet: bullets){
            g.fillOval(bullet.getPosX()-10, bullet.getPosY()-10,20,20);
        }
        
        g.setColor(Color.red);
        for(Enemy enemy: enemys){
            g.fillOval(enemy.getPosX()-10, enemy.getPosY()-10,20,20);
        }
        
        g2.drawImage(i,0,0,frame);
    }
    
    /* GETTERS AND SETTERS */
    public JFrame getFrame(){
        return frame;
    }
}
