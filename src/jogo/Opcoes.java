/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.awt.Color;
import jplay.Animation;
import jplay.GameImage;
import jplay.Mouse;
import jplay.URL;
import jplay.Window;

/**
 *
 * @author User
 */
public class Opcoes {

    private float volume;
    private double velocidade;
    private Window janela;
    private GameImage fundo;
    private Animation aumVel,dimVel,aumVol,dimVol,btnvoltar;
    private Mouse mouse;
    
    public Opcoes(Window janela) {
        this.janela = janela;
        this.volume = Som.getVolume(); 
        this.velocidade = Ator.getVelocidade();
        this.mouse = janela.getMouse();
        this.fundo = new GameImage(URL.sprite("opcoes.png"));
        aumVel = new Animation(URL.Animation("+.png"),2);
        dimVel = new Animation(URL.Animation("-.png"),2);
        aumVol = new Animation(URL.Animation("+.png"),2);
        dimVol = new Animation(URL.Animation("-.png"),2);
        btnvoltar = new Animation(URL.Animation("btnVoltarMenu.png"));
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
        btnvoltar.y = 500;
        btnvoltar.x = 500;
        
        run();
    }
    public Opcoes(){
        this.volume = Som.getVolume(); 
        this.velocidade = Ator.getVelocidade();
    }
    
    private void run(){
        while(true){
            fundo.draw();
            aumVel.draw();
            dimVel.draw();
            aumVol.draw();
            dimVol.draw();
            btnvoltar.draw();
            
            if (mouse.isOverObject(aumVel))  
                    {  
                        if(mouse.isLeftButtonPressed()){
                            aumVel.play();
                            aumVel();                   
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
                            dimVel();                   
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
                            aumVol();                   
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
                            dimVol();                   
                        }else{
                            if(dimVol.getCurrFrame() == 0)
                                dimVol.stop();
                        }
                    }else{
                            if(dimVol.getCurrFrame() == 0)
                                dimVol.stop();
                        }
            
            if (mouse.isOverObject(btnvoltar))  
                    {  
                        if(mouse.isLeftButtonPressed()){
                            Voltar();                   
                        }
                    }
            
            
            if(mouse.isLeftButtonPressed());
            
            
            double porcentagemVol = (volume + 80)/0.86;
            double porcentagemVel = velocidade *100;
            janela.drawText((Math.round(porcentagemVel)+"%"), 500, 195, Color.black,"Times new Roman",15);
            janela.drawText((Math.round(porcentagemVol)+"%"), 500, 395, Color.black,"Times new Roman",15);
            
            janela.drawText("Velocidade", 250, 195, Color.black,"Arial",30);
            janela.drawText("Volume", 250, 395, Color.black,"Arial",30);

            
            Volume();
            Velocidade();
            
            dimVel.update();
            aumVel.update();
            dimVol.update();
            aumVol.update();
            janela.update();
        }
    }
    
    public void Volume() {
        Som.volume(volume);
    }
    
    public void Velocidade(){
        Ator.setVelocidade(velocidade);
    }
    
    public void aumVol(){
        if(volume < 6)
            volume += 8.6;
    }
    
    public void dimVol(){
        if(volume > -79)
            volume -= 8.6;
    }
    
    public void aumVel(){
        if(velocidade < 0.9)
            velocidade += 0.1;
    }
    
    public void dimVel(){
        if(velocidade > 0.1)
            velocidade -= 0.1;
    }
    
    public void Voltar(){
        new Menu(janela);
    }
    
}
