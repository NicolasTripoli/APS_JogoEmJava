/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import jplay.Animation;
import jplay.GameImage;
import jplay.URL;
import jplay.Window;

/**
 *
 * @author User
 */
public class Creditos {
    private GameImage plano;
    private Animation creditos;
    private Window janela;
    private int pontuacao;
    private long tempoInicial;
    
    
    Creditos(Window janela,int pontuacao) {
        this.pontuacao = pontuacao;
        this.janela=janela;
        plano = new GameImage(URL.sprite("starting.png"));
        creditos = new Animation(URL.Animation("credito.png"));
        creditos.x = 0;
        creditos.y = 0;
        this.tempoInicial = System.currentTimeMillis();
        run();
    }
    
    private void run(){
        while(true){
            plano.draw();
            creditos.draw();
            if(System.currentTimeMillis() - tempoInicial > 3000)
                new janelaPontuacao(janela,pontuacao);
            creditos.update();
            janela.update();
        }
    }
    
}
