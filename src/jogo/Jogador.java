package jogo;

import jplay.Keyboard;
import jplay.URL;
import jplay.Window;

/**
 *
 * @author User
 */
public class Jogador extends Ator{
    
    public Jogador(int x, int y,String roupa) {
        super(URL.sprite(roupa),16);
        this.x=x;
        this.y=y;
        setTotalDuration(2000);
    }
       
    public void mover(Window janela,Keyboard teclado,boolean jogando){
         if(jogando){       
            if(teclado.keyDown(Keyboard.LEFT_KEY)){
                if(this.x > 0)
                    this.x -= velocidade;
                if(direcao != 1){
                    setSequence(4,8);
                    direcao = 1;
                }
                movendo = true;
                update();
                movendo = false;
                if(!teclado.keyDown(Keyboard.LEFT_KEY)){
                    setCurrFrame(4);
                }
            }else if(teclado.keyDown(Keyboard.RIGHT_KEY)){
                if(this.x < janela.getWidth()-45)
                    this.x += velocidade;
                if(direcao != 2){
                    setSequence(12,16);
                    direcao = 2;
                }
                movendo = true;
                update();
                movendo = false;
                if(!teclado.keyDown(Keyboard.RIGHT_KEY)){
                    setCurrFrame(12);
                }
            }else if(teclado.keyDown(Keyboard.UP_KEY)){
                if(this.y > 0)
                    this.y -= velocidade;
                if(direcao != 4){
                    setSequence(8,12);
                    direcao = 4;
                }
                movendo = true;
                update();
                movendo = false;
                if(!teclado.keyDown(Keyboard.UP_KEY)){
                    setCurrFrame(8);
                }
            }else if(teclado.keyDown(Keyboard.DOWN_KEY)){
                if(this.y < janela.getHeight()-60)
                    this.y += velocidade;
                if(direcao != 5){
                    setSequence(0,4);
                    direcao = 5;
                }
                movendo = true;
                update();
                movendo = false;
                if(!teclado.keyDown(Keyboard.DOWN_KEY)){
                    setCurrFrame(0);
                }     
            }
         }
    }
    
}
    

