package jogo;

import jplay.Animation;
import jplay.URL;
import jplay.GameImage;
import jplay.Keyboard;
import jplay.Window;


/**
 *
 * @author User
 */

public class Main {
    
    public static void main(String[] args){
        Window janela = new Window(800,600);
        GameImage plano = new GameImage(URL.sprite("starting.png"));
        Keyboard teclado = janela.getKeyboard();
        Animation APS = new Animation(URL.Animation("APS.png"),20);
        Som.play("intro.wav");
        Som.volume(-37);
        Ator.setVelocidade(0.3);
        APS.x = 0;  
        APS.y = 0;
        APS.setTotalDuration(1800);
        long tempoInicial = System.currentTimeMillis();
        while(true){
            plano.draw();
            APS.draw();
            APS.update();         
            if (APS.getCurrFrame() == 19) {
                APS.pause();
            }
            if (System.currentTimeMillis() - tempoInicial > 4000)
                    new Menu(janela);
            janela.update();          
        }
    }
}
