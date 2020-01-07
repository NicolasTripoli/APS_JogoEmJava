package jogo;

import jplay.Animation;
import jplay.URL;
import jplay.GameImage;
import jplay.Keyboard;
import jplay.Mouse;
import jplay.Window;


/**
 *
 * @author User
 */

public class MenuCenarios {
    private Window janela;
    private Mouse mouse;
    private GameImage plano;
    private int fase,pontuacao;
    private Animation Prefeitura,Industria,Apartamento,Fase3;
    private Keyboard teclado;
            
    public MenuCenarios(Window janela,int fase,int pontuacao){
        this.fase = fase;
        this.pontuacao = pontuacao;
        this.janela = janela;
        Fase3 = new Animation(URL.Animation("Fase3.png"));
        teclado=janela.getKeyboard();
        plano = new GameImage(URL.sprite("FundoMenuGame.png"));
        mouse = janela.getMouse();
        
        Prefeitura = new Animation(URL.Animation("prefeitura.png"));
        Prefeitura.x = 300;
        Prefeitura.y = 400;
        
        Industria = new Animation(URL.Animation("empresa.png"));
        Industria.x = 400;
        Industria.y = 70;

        Apartamento = new Animation(URL.Animation("apartamento.png"));
        Apartamento.x = 300;
        Apartamento.y = 150;
        
        run();
    }
    
    private void run(){
    
        while(true){
            plano.draw();
            Prefeitura.draw();
            Apartamento.draw();
            Industria.draw();
            if(fase == 29){
                new Creditos(janela,pontuacao);
            }
            if (mouse.isOverObject(Prefeitura)){
                if(mouse.isLeftButtonPressed()){
                    new SaguaoPrefeitura(janela,fase,pontuacao,1);
                }
            }
            if (mouse.isOverObject(Apartamento)){
                if(mouse.isLeftButtonPressed()){
                    new SaguaoApartamento(janela,fase,pontuacao,1);
                }
            }
            if (mouse.isOverObject(Industria)){
                if(mouse.isLeftButtonPressed()){
                    new SaguaoEmpresa(janela,fase,pontuacao,2);
                }
            }
            
            if(!mouse.isLeftButtonPressed());
            
            fase4();
            
            janela.update();
        }
    }

    private void fase4() {
        if(fase == 2){
                Fase3.draw();
                if(teclado.keyDown(Keyboard.ENTER_KEY)){
                    this.fase++;
                    Fase3.hide();
                }
            }
    }
}
