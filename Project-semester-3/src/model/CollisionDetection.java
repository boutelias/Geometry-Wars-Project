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
import model.power.*;

/**
 *
 * @author Tobias
 */
public class CollisionDetection {

    private Random randomGenerator = new Random();

    public void doCollisionBetweenBulletsAndEnemys(List<Bullet> bullets, List<Enemy> enemies, List<Geom> geoms, List<Power> powers) {
        List<Bullet> bulletsToRemove = new LinkedList();
        List<Enemy> enemiesToRemove = new LinkedList();
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
                            int rand = randomGenerator.nextInt(3);
                            switch (rand) {
                                case 0:
                                    powers.add(new SpeedBoost(enemy.getPosX(), enemy.getPosY()));
                                    break;
                                case 1:
                                    powers.add(new FireRateBoost(enemy.getPosX(), enemy.getPosY()));
                                    break;
                                case 2:
                                    powers.add(new DamageBoost(enemy.getPosX(), enemy.getPosY()));
                                    break;
                            }
                        }
                        if (drop(enemy.getDropratepowerdowns())) {
                            int rand = randomGenerator.nextInt(3);
                            switch (rand) {
                                case 0:
                                    powers.add(new SpeedNerf(enemy.getPosX(), enemy.getPosY()));
                                    break;
                                case 1:
                                    powers.add(new FireRateNerf(enemy.getPosX(), enemy.getPosY()));
                                    break;
                                case 2:
                                    powers.add(new DamageNerf(enemy.getPosX(), enemy.getPosY()));
                                    break;
                            }

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

        deleteObjectsFromList(enemies, enemiesToRemove);
        deleteObjectsFromList(bullets, bulletsToRemove);

    }

    public void doCollisionBetweenEnemiesAndCharacter(List<Bullet> bullets, List<Enemy> enemies, Character character) {
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
    }

    public void doCollisionBetweenCharacterAndGeom(List<Geom> geoms, Character character) {
        List<Geom> hittedGeoms = new ArrayList();
        for (Geom geom : geoms) {
            if (character.getBounds().intersects(geom.getBounds())) {
                character.addGeom();
                hittedGeoms.add(geom);
            }
        }

        deleteObjectsFromList(geoms, hittedGeoms);
    }

    public void doCollisionBetweenEnemiesAndMine(List<Enemy> enemies, List<Mine> mines) {
        List<Enemy> enemiesToRemove = new LinkedList();
        List<Mine> minesToRemove = new LinkedList();

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

        deleteObjectsFromList(enemies, enemiesToRemove);
        deleteObjectsFromList(mines, minesToRemove);
    }

    public void doCollisionBetweenCharacterAndMine(List<Mine> mines, Character character) {
        List<Mine> minesToRemove = new LinkedList();
        for (Mine mine : mines) {
            if (mine.getBounds().intersects(character.getBounds())) {
                character.removeLife();
                minesToRemove.add(mine);

            }
        }
        deleteObjectsFromList(mines, minesToRemove);
    }

    public void doCollisionBetweenCharacterAndPowers(Character c, List<Power> powers) {
        for (Power power : powers) {
            if (!power.isPickedUp()) {
                if (power.getBounds().intersects(c.getBounds())) {
                    power.start(c);
                }
            }
        }
    }

    private <T> void deleteObjectsFromList(List<T> originalList, List<T> listToRemove) {
        for (T elementToRemove : listToRemove) {
            originalList.remove(elementToRemove);
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
