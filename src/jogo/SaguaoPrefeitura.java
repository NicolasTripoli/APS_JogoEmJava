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
public class SaguaoPrefeitura extends Cenario{
    private boolean jogando = true;
    private Window janela;
    private Scene pref1;
    private Jogador jogador;
    private Keyboard teclado;
    private int fase,pontuacao;
    private Animation fundo,aumVel,dimVel,aumVol,dimVol,btnSair,Fase4,Fase5,Fase8
            ,Fase12,Fase16,Fase20;
    private Mouse mouse;
    private Opcoes opcoes;



    
    public SaguaoPrefeitura(Window janela,int fase, int pontuacao,int porta){
        this.janela = janela;
        pref1 = new Scene();
        this.mouse = janela.getMouse();
        this.opcoes = new Opcoes();
        this.Fase4 = new Animation(URL.Animation("Fase4.png"));
        this.Fase5 = new Animation(URL.Animation("Fase5.png"));
        this.Fase8 = new Animation(URL.Animation("Fase8.png"));
        this.Fase12 = new Animation(URL.Animation("Fase12.png"));
        this.Fase16 = new Animation(URL.Animation("Fase16.png"));
        this.Fase20 = new Animation(URL.Animation("Fase20.png"));

        pref1.loadFromFile(URL.scenario("saguaoprefeitura.scn"));
        if(porta == 1)jogador = new Jogador(430,520,"prefeito.png");
        if(porta == 2)jogador = new Jogador(500,380,"prefeito.png");
        if(porta == 3)jogador = new Jogador(450,150,"prefeito.png");
        if(porta == 4)jogador = new Jogador(450,150,"prefeito.png");
        if(porta == 5)jogador = new Jogador(450,150,"prefeito.png");
        teclado=janela.getKeyboard();
        funEsc();
        
        this.fase = fase;
        this.pontuacao = pontuacao;
        run();
    }

    private void run() {
        int x=0;
        while(true){
            
            jogador.mover(janela,teclado,jogando);
            jogador.caminho(pref1);
            pref1.moveScene(jogador);
            
            jogador.x += pref1.getXOffset();
            jogador.y += pref1.getYOffset();
          
            jogador.draw();            
            
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
            
            fase4();
            fase5();
            fase8();
            fase12();
            fase16();
            fase20();
            Action();
            
            mudarCenario();
            janela.update();
        }
    }
    
    private void mudarCenario(){
        if(tileCollision(10,jogador,pref1) == true){
            new SalaTransporte(janela,fase,pontuacao);
        }
        if(tileCollision(9,jogador,pref1) == true){
            new SalaTransporte(janela,fase,pontuacao);
        }
        if(tileCollision(7,jogador,pref1) == true){
            new SalaMeioAmbiente(janela,fase,pontuacao);
        }
        if(tileCollision(8,jogador,pref1) == true){
            new SalaMeioAmbiente(janela,fase,pontuacao);
        }
        if(tileCollision(11,jogador,pref1) == true){
            new SalaMinistroCiencia(janela,fase,pontuacao);
        }
        if(tileCollision(12,jogador,pref1) == true){
            new SalaMinistroCiencia(janela,fase,pontuacao);
        }
        if(tileCollision(5,jogador,pref1) == true){
            new SalaGovernador(janela,fase,pontuacao);
        }
        if(tileCollision(6,jogador,pref1) == true){
            new SalaGovernador(janela,fase,pontuacao);
        }
        if(tileCollision(4,jogador,pref1) == true){
            new MenuCenarios(janela,fase,pontuacao);
        }
        if(tileCollision(3,jogador,pref1) == true){
            new MenuCenarios(janela,fase,pontuacao);
        }
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

    private void fase4() {
        if(fase == 3){
                jogando = false;
                Fase4.draw();
                Fase4.unhide();
                jogador.hide();
                if(teclado.keyDown(Keyboard.ENTER_KEY)){
                    this.fase++;
                    Fase4.hide();
                    jogador.unhide();
                    jogando = true;
                }
            }
    }
    
    private void fase5() {
        if(fase == 4){
                jogando = false;
                Fase5.draw();
                Fase5.unhide();
                jogador.hide();
                if(teclado.keyDown(Keyboard.ENTER_KEY)){
                    this.fase++;
                    Fase5.hide();
                    jogador.unhide();
                    jogando = true;
                }
            }
    }
    
    private void fase8() {
        if(fase == 8){
                jogando = false;
                Fase8.draw();
                Fase8.unhide();
                jogador.hide();
                if(teclado.keyDown(Keyboard.ENTER_KEY)){
                    this.fase++;
                    Fase8.hide();
                    jogador.unhide();
                    jogando = true;
                }
            }
    }
    
    private void fase12() {
        if(fase == 13){
                jogando = false;
                Fase12.draw();
                Fase12.unhide();
                jogador.hide();
                if(teclado.keyDown(Keyboard.ENTER_KEY)){
                    this.fase++;
                    Fase12.hide();
                    jogador.unhide();
                    jogando = true;
                }
            }
    }
    
    private void fase16() {
        if(fase == 18){
                jogando = false;
                Fase16.draw();
                Fase16.unhide();
                jogador.hide();
                if(teclado.keyDown(Keyboard.ENTER_KEY)){
                    this.fase++;
                    Fase16.hide();
                    jogador.unhide();
                    jogando = true;
                }
            }
    }

    private void fase20() {
        if(fase == 23){
                jogando = false;
                Fase20.draw();
                Fase20.unhide();
                jogador.hide();
                if(teclado.keyDown(Keyboard.ENTER_KEY)){
                    this.fase++;
                    Fase20.hide();
                    jogador.unhide();
                    jogando = true;
                }
            }
    }
    
    private void Action() {
        if(jogador.Action(pref1, 13)){
            if(teclado.keyDown(Keyboard.SPACE_KEY)){
                if(jogando){
                    fase--;
                }
            }
            if(!teclado.keyDown(Keyboard.SPACE_KEY));
        }
    }
    
}
