/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.awt.Color;
import jplay.Animation;
import jplay.Keyboard;
import jplay.Mouse;
import jplay.Scene;
import jplay.URL;
import jplay.Window;

/**
 *
 * @author User
 */
public class Apartamento extends Cenario{
    private boolean jogando = true;
    private Window janela;
    private Scene tutorial;
    private Jogador jogador;
    private Keyboard teclado;
    private int fase,pontuacao;
    private Animation fundo,aumVel,dimVel,aumVol,dimVol,btnSair;
    private Animation Fase1,Fase2;
    private Mouse mouse;
    private Opcoes opcoes;
    
    public Apartamento(Window janela,int fase, int pontuacao){
        this.janela = janela;
        this.opcoes = new Opcoes();
        tutorial = new Scene();
        tutorial.loadFromFile(URL.scenario("apartamento.scn"));
        this.mouse = janela.getMouse();
        if(fase == 0)jogador = new Jogador(150,200,"pijama.png");
        else jogador = new Jogador(360,150,"prefeito.png");
        teclado=janela.getKeyboard();
        funEsc();
        Fase1 = new Animation(URL.Animation("Fase1.png"));
        Fase1.x = 0;  
        Fase1.y = 0;
        Fase2 = new Animation(URL.Animation("Fase2.png"));
        Fase2.x = 0;  
        Fase2.y = 0;
        
        this.fase = fase;
        this.pontuacao = pontuacao;
        run();
    }

    private void run() {
        int x=0;
        while(true){
            
            jogador.mover(janela,teclado,jogando);
            jogador.caminho(tutorial);
            tutorial.moveScene(jogador);
            
            
            jogador.x += tutorial.getXOffset();
            jogador.y += tutorial.getYOffset();
            
            fase1();
            x=fase2(x);
            
            if(x==0){
                if(teclado.keyDown(Keyboard.ESCAPE_KEY)){
                    x=3;
                }
            }
            if(x==3){
                if(teclado.keyDown(Keyboard.ESCAPE_KEY)){
                    jogador.unhide();
                    jogando = true;
                    x=0;
                }
            }
            
            esc(x);

            jogador.draw();            
            janela.update();
            mudarCenario();
        }
    }
    
    private void mudarCenario(){
        if(tileCollision(3,jogador,tutorial) == true){
            if(fase>1){
                new SaguaoApartamento(janela,fase,pontuacao,2);
            }else{
                jogador.y = jogador.y + 0.8;
            }                 
        }
        if(tileCollision(6,jogador,tutorial) == true){
            if(fase>1){
                new SaguaoApartamento(janela,fase,pontuacao,2);
            }else{
                jogador.y = jogador.y + 0.8;
            }                 
        }
    }
    
    private void fase1(){
        if(fase == 0){
                jogando = false;
                Fase1.draw();
                jogador.hide();
                if(teclado.keyDown(Keyboard.ENTER_KEY)){
                    this.fase++;
                    Fase1.hide();
                    jogador.unhide();
                    jogando = true;
                }
            }
    }
    
    private int fase2(int x){
        if(x == 1){
                Fase2.draw();
                jogando = false;
                jogador = new Jogador((int)jogador.x,(int)jogador.y,"prefeito.png");
                jogador.hide();
            }
        if(fase==1){
            if(jogador.Action(tutorial,7)){
                if(x == 0){
                    if(teclado.keyDown(Keyboard.SPACE_KEY)){
                        x++;
                    }
                }
                if(x == 1){
                    if(teclado.keyDown(Keyboard.ENTER_KEY)){
                            this.fase++;
                            Fase2.hide();
                            jogador.unhide();
                            jogando = true;
                            x--;
                    }
                }else if(!teclado.keyDown(Keyboard.ENTER_KEY));
            }else if(!teclado.keyDown(Keyboard.SPACE_KEY));
        }
    return x;
    }

    private void esc(int x) {
        if(x==3){
            jogador.hide();
            jogando = false;
            fundo.draw();
            aumVel.draw();
            dimVel.draw();
            aumVol.draw();
            dimVol.draw();
            btnSair.draw();
            
            if (mouse.isOverObject(aumVel))  
                    {  
                        if(mouse.isLeftButtonPressed()){
                            aumVel.play();
                            opcoes.aumVel();                   
                        }else{
                            if(aumVel.getCurrFrame() == 0)
                                aumVel.stop();
                        }
                    }else{
                            if(aumVel.getCurrFrame() == 0)
                                aumVel.stop();
                        }
            if (mouse.isOverObject(dimVel))  
                    {  
                        if(mouse.isLeftButtonPressed()){
                            dimVel.play();
                            opcoes.dimVel();                   
                        }else{
                            if(dimVel.getCurrFrame() == 0)
                                dimVel.stop();
                        }
                    }else{
                            if(dimVel.getCurrFrame() == 0)
                                dimVel.stop();
                        }
            if (mouse.isOverObject(aumVol))  
                    {  
                        if(mouse.isLeftButtonPressed()){
                            aumVol.play();
                            opcoes.aumVol();                   
                        }else{
                            if(aumVol.getCurrFrame() == 0)
                                aumVol.stop();
                        }
                    }else{
                            if(aumVol.getCurrFrame() == 0)
                                aumVol.stop();
                        }
            if (mouse.isOverObject(dimVol))  
                    {  
                        if(mouse.isLeftButtonPressed()){
                            dimVol.play();
                            opcoes.dimVol();                   
                        }else{
                            if(dimVol.getCurrFrame() == 0)
                                dimVol.stop();
                        }
                    }else{
                            if(dimVol.getCurrFrame() == 0)
                                dimVol.stop();
                        }
            
            if (mouse.isOverObject(btnSair))  
                    {  
                        if(mouse.isLeftButtonPressed()){
                            janela.exit();                   
                        }
                    }
            
            
            if(mouse.isLeftButtonPressed());
            
            
            double porcentagemVol = (Som.getVolume() + 80)/0.86;
            double porcentagemVel = (Ator.getVelocidade() * 100);
            janela.drawText((Math.round(porcentagemVel)+"%"), 500, 195, Color.black,"Times new Roman",15);
            janela.drawText((Math.round(porcentagemVol)+"%"), 500, 395, Color.black,"Times new Roman",15);
            

            
            janela.drawText("Velocidade", 250, 195, Color.black,"Arial",30);
            janela.drawText("Volume", 250, 395, Color.black,"Arial",30);
            janela.drawText("ESC para voltar ao jogo!", 250, 520, Color.black,"Arial",15);


            
            opcoes.Volume();
            opcoes.Velocidade();
            
            dimVel.update();
            aumVel.update();
            dimVol.update();
            aumVol.update();
        }
    }

    private void funEsc() {
        fundo = new Animation(URL.sprite("opcoes.png"));
        aumVel = new Animation(URL.Animation("+.png"),2);
        dimVel = new Animation(URL.Animation("-.png"),2);
        aumVol = new Animation(URL.Animation("+.png"),2);
        dimVol = new Animation(URL.Animation("-.png"),2);
        btnSair = new Animation(URL.Animation("btnSairJogo.png"));
        dimVel.x = 450;
        dimVel.y = 200;
        dimVel.setTotalDuration(500);
        aumVel.x = 450;
        aumVel.y = 150;
        aumVel.setTotalDuration(500);
        dimVol.x = 450;
        dimVol.y = 400;
        dimVol.setTotalDuration(500);
        aumVol.x = 450;
        aumVol.y = 350;
        aumVol.setTotalDuration(500);
        btnSair.y = 500;
        btnSair.x = 500;
    }
    
}
