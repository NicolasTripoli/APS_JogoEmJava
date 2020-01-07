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
public class SaguaoApartamento extends Cenario{
    private boolean jogando = true;
    private Window janela;
    private Scene pref1;
    private Jogador jogador;
    private Keyboard teclado;
    private int fase,pontuacao,inst,x;
    private Animation FalaP,Instrucoes,Resposta;
    private Animation fundo,aumVel,dimVel,aumVol,dimVol,btnSair;
    private Mouse mouse;
    private Opcoes opcoes;
    
    public SaguaoApartamento(Window janela,int fase, int pontuacao,int porta){
        this.janela = janela;
        pref1 = new Scene();
        this.inst = 0;
        this.FalaP = new Animation(URL.Animation("falaporteiro.png"));
        this.Resposta = new Animation(URL.Animation("resposta.png"));
        this.Instrucoes = new Animation(URL.Animation("instrucoes.png"));
        this.mouse = janela.getMouse();
        this.opcoes = new Opcoes();
        pref1.loadFromFile(URL.scenario("saguaoapartamento.scn"));
        if(porta == 1)jogador = new Jogador(490,130,"prefeito.png");
        else jogador = new Jogador(238,360,"prefeito.png");
        teclado=janela.getKeyboard();
        funEsc();
        
        
        this.fase = fase;
        this.pontuacao = pontuacao;
        run();
    }

    private void run() {
        x=0;
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
            if(teclado.keyDown(Keyboard.SPACE_KEY)){
                x = 1;
            }
            if(x == 1){
                jogando = false;
                Resposta.draw();
                Resposta.unhide();
                jogador.hide();
                if(teclado.keyDown(Keyboard.ENTER_KEY)){
                    Resposta.hide();
                    jogador.unhide();
                    jogando = true;
                    x = 0;
                }
            }
            esc(x);
            
            instrucoes();
            
            mudarCenario();
            janela.update();
        }
    }
    
    private void mudarCenario(){
        if(tileCollision(9,jogador,pref1) == true){
            new Apartamento(janela,fase,pontuacao);
        }
        if(tileCollision(5,jogador,pref1) == true){
            new MenuCenarios(janela,fase,pontuacao);
        }
        if(tileCollision(8,jogador,pref1) == true){
            new Apartamento(janela,fase,pontuacao);
        }
        if(tileCollision(6,jogador,pref1) == true){
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

    private void instrucoes() {
        if(fase == 2){
            if(inst == 0){
                jogando = false;
                FalaP.draw();
                FalaP.unhide();
                jogador.hide();
                if(teclado.keyDown(Keyboard.ENTER_KEY)){
                    inst = 1;
                    FalaP.hide();
                    jogador.unhide();
                    jogando = true;
                }
            }
            if(inst == 1){
                jogando = false;
                Instrucoes.draw();
                Instrucoes.unhide();
                jogador.hide();
                if(teclado.keyDown(Keyboard.ENTER_KEY)){
                    inst = 2;
                    Instrucoes.hide();
                    jogador.unhide();
                    jogando = true;
                }
            }
        }
    }   
}
