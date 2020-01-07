/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.awt.Point;
import java.util.Vector;
import jplay.GameObject;
import jplay.Scene;
import jplay.Sprite;
import jplay.TileInfo;

/**
 *
 * @author User
 */
public class Ator extends Sprite{
    protected int direcao = 3;
    protected double velocidade;
    private static double vel;
    protected boolean movendo = false;
    protected Controle controle = new Controle();

    public Ator(String fileName, int numFrames) {
        super(fileName, numFrames);
        this.velocidade = vel;
    }
    
    public static void setVelocidade(double velocidade){
        Ator.vel = velocidade;
    }
    
    public static double getVelocidade(){
        return Ator.vel;
    }
    
    public void caminho(Scene cena) {
        Point min = new Point((int)this.x,(int)this.y);
        Point max = new Point((int)this.x + this.width,(int)this.y + this.height);

        Vector<?> tiles = cena.getTilesFromPosition(min, max);

        for(int i = 0;i<tiles.size();i++){
            TileInfo tile = (TileInfo)tiles.elementAt(i);
            if(controle.Colisao(this, tile) == true){
                if(colisaoVertical(this, tile)){
                    if(tile.y + tile.height - 2< this.y){
                        this.y = tile.y + tile.height + 0.9;
                    }else if(tile.y > this.y + this.height - 2){
                        this.y = tile.y - this.height - 0.01;
                    }
                } 

                if(colisaoHorizontal(this, tile)){
                    if(tile.x > this.x + this.width - 4 ){
                        this.x = tile.x - this.width;
                    }else{ 
                        this.x = tile.x + tile.width;
                    }
                }
            }
        }
    }

    private boolean colisaoVertical(GameObject obj, GameObject obj2){
        if(obj2.x + obj2.width < obj.x){
            return false;
        }
        if(obj.x + obj.width < obj2.x){
            return false;
        }
        return true;            
    }

    private boolean colisaoHorizontal(GameObject obj, GameObject obj2){
        if(obj2.y + obj2.height < obj.y){
            return false;
        }
        if(obj.y + obj.height < obj2.y){
            return false;
        }
        return true;  
    }
    
    public boolean Action(Scene cena,int n){
        int b = 0;
        Point min = new Point((int)this.x,(int)this.y);
        Point max = new Point((int)this.x + this.width,(int)this.y + this.height);

        Vector<?> tiles = cena.getTilesFromPosition(min, max);

        for(int i = 0;i<tiles.size();i++){
            TileInfo tile = (TileInfo)tiles.elementAt(i);
            String x = (Integer.toString(tile.id));
            if((tile.id) >= n && (tile.id) <= n+1){
               b++;
           }
        }
        if(b > 1)
            return true;
        return false;
    }

}
