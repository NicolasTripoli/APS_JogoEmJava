package jogo;

import jplay.GameImage;
import jplay.Window;
import jplay.URL;
import jplay.Mouse;  
import jplay.Animation;  

/**
 *
 * @author User
 */
public class Menu{
    private Window janela;
    private GameImage fundo;
    private Mouse mouse;
    private Animation btnPlay,btnOpcoes,btnSair,btnPontuacoes;
    private int fase = 0,pontuacao = 0;
    
    public Menu(Window window){
        janela = window;
        fundo = new GameImage(URL.sprite("menu.png"));
        btnPlay = new Animation(URL.Animation("btnJogar.png"),9);
        btnOpcoes = new Animation(URL.Animation("btnOpcoes.png"),9);
        btnPontuacoes = new Animation(URL.Animation("btnPontuacao.png"),12);
        btnSair = new Animation(URL.Animation("btnSair.png"),7);
        mouse = janela.getMouse();
        Som.play("menu.wav");
        btnPlay.x = 136.5;  
        btnPlay.y = 300;
        btnPlay.setTotalDuration(1000);
        btnOpcoes.x = 136.5;
        btnOpcoes.y = 370;
        btnOpcoes.setTotalDuration(1000);
        btnPontuacoes.x = 136.5;
        btnPontuacoes.y = 440;
        btnPontuacoes.setTotalDuration(1500);
        btnSair.x = 136.5;
        btnSair.y = 510;
        btnSair.setTotalDuration(1000);
         
        
        run();
    }

    public void run() {
        while(true)  
            {  
                    fundo.draw();  
                    btnPlay.draw();
                    btnOpcoes.draw();
                    btnPontuacoes.draw();
                    btnSair.draw();
                    if (mouse.isOverObject(btnOpcoes))  
                    {  
                        btnOpcoes.play();
                        if(mouse.isLeftButtonPressed()){
                            new Opcoes(janela);
                        }
                    }else{
                        btnOpcoes.stop();
                    }
                    
                    if (mouse.isOverObject(btnPlay))  
                    {  
                        btnPlay.play();
                        if(mouse.isLeftButtonPressed()){
                            Som.play("principal.wav");
                            new Apartamento(janela,fase,pontuacao);
                        }
                    }else{
                        btnPlay.stop();
                    }
                    
                    if (mouse.isOverObject(btnPontuacoes))  
                    {  
                        btnPontuacoes.play();
                        if(mouse.isLeftButtonPressed()){
                            new janelaPontuacao(janela);
                        }
                    }else{
                        btnPontuacoes.stop();
                    }
                    if (mouse.isOverObject(btnSair))  
                    {  
                        btnSair.play();
                        if(mouse.isLeftButtonPressed()){
                            janela.exit();
                        }
                    }else{
                        btnSair.stop();
                    }
                    
                    if(!mouse.isLeftButtonPressed()){
                    }
                    
                    btnPlay.update();
                    btnOpcoes.update();
                    btnPontuacoes.update();
                    btnSair.update();
                    janela.update();  
            }  
    }  
}
