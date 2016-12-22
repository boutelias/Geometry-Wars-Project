/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author Maxime
 */
public class CompanionShop {
    
    int id;
    String sprite;
    int price;
    
    public CompanionShop(int id, String sprite, int price){
    this.id= id;
    this.sprite = sprite;
    this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getSprite() {
        return sprite;
    }

    public int getPrice() {
        return price;
    }
    
    
    
}
