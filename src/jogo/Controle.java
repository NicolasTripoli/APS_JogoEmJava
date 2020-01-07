/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import jplay.GameObject;
import jplay.TileInfo;

/**
 *
 * @author User
 */
public class Controle {
    
    public static boolean Colisao(GameObject obj, TileInfo tile){
        
        if((tile.id)>= 30 && obj.collided(tile)){
            return true;
        }
                
        return false;
    }
    
}
