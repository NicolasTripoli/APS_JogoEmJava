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
public class SalaMeioAmbiente extends Cenario{
    private boolean jogando = true;
    private Window janela;
    private Scene pref1;
    private Jogador jogador;
    private Keyboard teclado;
    private int fase,pontuacao;
    private Animation fundo,aumVel,dimVel,aumVol,dimVol,btnSair;
    private Animation Fase6,Fase7,Pergunta1,Resposta1,Resposta2,Resposta3,Resposta4;
    private Mouse mouse;
    private Opcoes opcoes;
    
    public SalaMeioAmbiente(Window janela,int fase, int pontuacao){
        this.janela = janela;
        pref1 = new Scene();
        this.mouse = janela.getMouse();
        this.opcoes = new Opcoes();
        this.Fase6 = new Animation(URL.Animation("Fase6.png"));
        this.Fase7 = new Animation(URL.Animation("Fase7.png"));
        this.Pergunta1 = new Animation(URL.Animation("Pergunta1.png"));
        funEsc();
        pref1.loadFromFile(URL.scenario("SalaMeioAmbiente.scn"));
        jogador = new Jogador(230,300,"prefeito.png");
        teclado=janela.getKeyboard();
        
        
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
            
            if(x==0){
                if(jogador.Action(pref1, 9)){
                    if(teclado.keyDown(Keyboard.SPACE_KEY)){
                        x = 2;
                    }
                }
            }
            
            if(x == 2){
                Pergunta1();
                if(fase == 8)
                    x = 0;
            }
            
            if(teclado.keyDown(Keyboard.SPACE_KEY));
            
            esc(x);
            fase6();
            fase7();
            mudarCenario();
            janela.update();
        }
    }
    
    private void mudarCenario(){
        if(tileCollision(8,jogador,pref1) == true){
            new SaguaoPrefeitura(janela,fase,pontuacao,2);
        }
        if(tileCollision(11,jogador,pref1) == true){
            new SaguaoPrefeitura(janela,fase,pontuacao,2);
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

    private void fase6() {
        if(fase == 5){
            jogando = false;
            Fase6.draw();
            Fase6.unhide();
            jogador.hide();
            if(teclado.keyDown(Keyboard.ENTER_KEY)){
                this.fase++;
                Fase6.hide();
                jogador.unhide();
                jogando = true;
            }
        } 
    }

    private void fase7() {
        if(fase == 7){
            jogando = false;
            Fase7.draw();
            Fase7.unhide();
            jogador.hide();
            if(teclado.keyDown(Keyboard.ENTER_KEY)){
                this.fase++;
                Fase7.hide();
                jogador.unhide();
                jogando = true;
            }
        }
    }
    
    private void Pergunta1(){
        if(fase == 6){
            jogando = false;
            Pergunta1.draw();
            Pergunta1.unhide();
            jogador.hide();
            Respostas();
            }
    }

    private void Respostas() {
        this.Resposta1 = new Animation(URL.Animation("P1R1.png"));
        this.Resposta2 = new Animation(URL.Animation("P1R2.png"));
        this.Resposta3 = new Animation(URL.Animation("P1R3.png"));
        this.Resposta4 = new Animation(URL.Animation("P1R4.png"));
        Resposta1.x = 10;
        Resposta1.y = 240;
        Resposta2.x = 10;
        Resposta2.y = 330;
        Resposta3.x = 10;
        Resposta3.y = 420;
        Resposta4.x = 10;
        Resposta4.y = 510;
        
        if (mouse.isOverObject(Resposta1)){
                if(mouse.isLeftButtonPressed()){
                    pontuacao += 15;
                    fase++;
                    Pergunta1.hide();
                    jogando = true;
                    jogador.unhide();
                }
            }
        if (mouse.isOverObject(Resposta2)){
                if(mouse.isLeftButtonPressed()){
                    pontuacao += 20;
                    fase++;
                    Pergunta1.hide();
                    jogando = true;
                    jogador.unhide();
                }
            }
        if (mouse.isOverObject(Resposta3)){
                if(mouse.isLeftButtonPressed()){
                    pontuacao += 5;
                    fase++;
                    Pergunta1.hide();
                    jogador.unhide();
                    jogando = true;
                }
            }
        if (mouse.isOverObject(Resposta4)){
                if(mouse.isLeftButtonPressed()){
                    pontuacao += 10;
                    fase++;
                    Pergunta1.hide();
                    jogando = true;
                    jogador.unhide();
                }
            }
        if(!mouse.isLeftButtonPressed());
        
        Resposta1.draw();
        Resposta2.draw();
        Resposta3.draw();
        Resposta4.draw();
    }
}
