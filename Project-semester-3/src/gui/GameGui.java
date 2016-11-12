/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import model.Player;
import model.Bullet;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.swing.JFrame;
import model.Bullet;
import model.Enemy;
import model.Geom;
import model.InputHandler;
import java.awt.Color;
import model.Companion;
import model.Mine;

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
    Player character;
    List<Bullet> bullets;
    Companion companion;
        
    public GameGui(){        
        frame = new JFrame("Crazy Scientist Game");
        //frame.setSize(new Dimension(gameWidth,gameHeight));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setUndecorated(true);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        
        gameWidth = (int) frame.getBounds().getWidth();
        gameHeight = (int) frame.getBounds().getHeight();
        
        
        System.out.println(gameWidth);
        System.out.println(gameHeight);
 
        
        
         //graphic stuff
        
        i = new BufferedImage(gameWidth,gameHeight, BufferedImage.TYPE_INT_RGB);
        g = i.getGraphics();
        g2 = frame.getGraphics();
        
    }
    
    public void draw(Player character, List<Bullet> bullets, List<Enemy> enemies, List<Geom> geoms, Companion companion,List<Mine> mines){
        this.character = character;
        this.bullets = bullets;
        
        
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, gameWidth, gameHeight);
        
        Image bannerTop = Toolkit.getDefaultToolkit().getImage("src/img/banner-top.jpg");
        g.drawImage(bannerTop, 0, 0,null);
        
        g.setColor(Color.YELLOW);
        g.fillOval(character.getPosX()-(character.getHeight()/2), character.getPosY()-(character.getWidth()/2), character.getHeight(), character.getWidth());
                
        g.setColor(Color.pink);
        for(Geom geom: geoms){
            g.fillOval(geom.getPosX()-(geom.getHeight()/2), geom.getPosY()-(geom.getWidth()/2), geom.getHeight(), geom.getWidth());
        }
        
        g.setColor(Color.green);
        for(Bullet bullet: bullets){
            g.fillOval(bullet.getPosX()-10, bullet.getPosY()-10,20,20);
        }
        
        g.setColor(Color.darkGray);
        for(Mine mine: mines){
            g.fillOval(mine.getPosX()-(mine.getHeight()/2),mine.getPosY() - (mine.getWidth()/2) , mine.getHeight(), mine.getWidth());
        }
        
        g.setColor(Color.WHITE);
        g.fillOval(companion.getPosX()-(companion.getHeight()/2), companion.getPosY()-(companion.getWidth()/2),companion.getHeight() , companion.getWidth());
        
        
        Image enemySprite = Toolkit.getDefaultToolkit().getImage("src/img/bomber.png");
        
        g.setColor(Color.red);
        for(Enemy enemy: enemies){
            g.drawImage(enemySprite,enemy.getPosX()-10, enemy.getPosY()-10,20,20,null);
        }
        Color kleur = new Color(25,139,193);
        g.setFont(new Font("Comic Sans",Font.BOLD,26));
        g.setColor(kleur);
        g.drawString(Integer.toString(character.getLives()), 1015, 30);
        
        
        g.drawString(Long.toString(character.getScore()), 130, 30);
        
        g.drawString(Integer.toString(character.getNumberOfGeoms()), 1735, 30);
        
        
        g2.drawImage(i,0,0,frame);
    }
    
    /* GETTERS AND SETTERS */
    public JFrame getFrame(){
        return frame;
    }

    public int getGameWidth() {
        return gameWidth;
    }

    public int getGameHeight() {
        return gameHeight;
    }
    
    
}
