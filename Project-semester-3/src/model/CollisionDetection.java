/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


/**
 *
 * @author Tobias
 */
public class CollisionDetection {
    private Random randomGenerator = new Random();
    public void doCollisionDetection(List<Bullet> bullets, List<Enemy> enemies, List<Geom> geoms,List<Mine> mines,Character character){
        /*bullets vs enemiesdetection*/
        List<Bullet> bulletsToRemove = new LinkedList();
        List<Enemy> enemiesToRemove = new LinkedList();
        List<Mine> minesToRemove = new LinkedList();

        for (Bullet bullet : bullets) {
            for (Enemy enemy : enemies) {
                if (bullet.getBounds().intersects(enemy.getBounds())) {

                    checkdamage(bullet, enemy);

                    if (enemy.getHp() == 0) {
                        if (drop(enemy.getDropRateGeom())) {
                            Geom geom = new Geom(enemy.getPosX(), enemy.getPosY());
                            geoms.add(geom);
                        }
                        if (drop(enemy.getDropratepowerups())) {

                        }
                        if (drop(enemy.getDropratepowerdowns())) {

                        }
                        enemiesToRemove.add(enemy);
                        bullet.getCharacter().addPoints(enemy.getValue());
                    }
                    if (bullet.getDamage() == 0) {
                        bulletsToRemove.add(bullet);

                    }

                }
            }
        }

        for (Bullet bullet : bulletsToRemove) {
            bullets.remove(bullet);
        }

        /*enemies vs player detection*/
        Enemy hittedChar = null;
        for (Enemy enemy : enemies) {
            if (character.getBounds().intersects(enemy.getBounds())) {
                character.removeLife();
                hittedChar = enemy;
            }
        }
        if (hittedChar != null) {
            enemies.remove(hittedChar);
        }

        /* player vs geoms */
        List<Geom> hittedGeoms = new ArrayList();
        for (Geom geom : geoms) {
            if (character.getBounds().intersects(geom.getBounds())) {
                character.addGeom();
                hittedGeoms.add(geom);
            }
        }
        for (Geom geom : hittedGeoms) {
            geoms.remove(geom);
        }
        /* enemies vs mine */

        for (Enemy enemy : enemies) {
            for (Mine mine : mines) {
                if (enemy.getBounds().intersects(mine.getBounds())) {
                    int newhp = enemy.getHp() - mine.getDamage();
                    if (newhp <= 0) {
                        enemiesToRemove.add(enemy);

                    } else {
                        enemy.setHp(newhp);
                    }
                    minesToRemove.add(mine);

                }
            }
        }
        for (Enemy enemy : enemiesToRemove) {
            enemies.remove(enemy);
        }

        /* player vs mine */
        for (Mine mine : mines) {
            if (mine.getBounds().intersects(character.getBounds())) {
                character.removeLife();
                minesToRemove.add(mine);

            }
        }
        for (Mine mine : minesToRemove) {
            mines.remove(mine);
        }
    }
    
     private void checkdamage(Bullet bullet, Enemy enemy) {
        int bulletdamage = bullet.getDamage();
        int enemyhp = enemy.getHp();

        if (bulletdamage > enemyhp) {
            bullet.setDamage(bulletdamage - enemyhp);
            enemy.setHp(0);

        } else if (bulletdamage < enemyhp) {
            enemy.setHp(enemyhp - bulletdamage);
            bullet.setDamage(0);
        } else {
            enemy.setHp(0);
            bullet.setDamage(0);
        }
    }

    private boolean drop(double droprate) {
        int maxint = (int) (droprate * 100);

        int randomInt = randomGenerator.nextInt(100);

        if (randomInt < maxint) {
            return true;
        } else {
            return false;
        }

    }
}
