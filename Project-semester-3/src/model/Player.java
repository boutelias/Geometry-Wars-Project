/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Elias
 */
public class Player {
    String username;
    String password;
    int highscore;
    int totalgeoms;
    int totalpremiumcoins;
    public Player(String username, String password,int highscore ,int totalgeoms,int totalpremiumcoins){
        this.username = username;
        this.password = password;
        this.highscore = highscore;
        this.totalgeoms = totalgeoms;
        this.totalpremiumcoins = totalpremiumcoins;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getHighscore() {
        return highscore;
    }

    public int getTotalgeoms() {
        return totalgeoms;
    }

    public int getTotalpremiumcoins() {
        return totalpremiumcoins;
    }
    
}
