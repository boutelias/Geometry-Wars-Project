/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.companions;

import java.awt.Rectangle;

/**
 *
 * @author Tobias
 */
public interface Companion {
    public void doMove();
    public void doSpecialAction();
    public int getPosX();
    public int getPosY();
    public int getWidth();
    public int getHeight();
}
