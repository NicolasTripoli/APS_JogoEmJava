/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import BD.Entidade.PontuacoesE;
import BD.Percistencia.PontuacoesP;
import java.awt.Color;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import jplay.Animation;
import jplay.GameImage;
import jplay.Mouse;
import jplay.URL;
import jplay.Window;

/**
 *
 * @author User
 */
public class janelaPontuacao {
    private ArrayList<PontuacoesE> resultados;
    private Window janela;
    private Mouse mouse;
    private GameImage fundo;
    private Animation btnMenu,btnSair;
    private String name,erro;
    private int pontuacao;
    
    
    public janelaPontuacao(Window janela, int pontuacao) {
        this.janela = janela;
        this.pontuacao = pontuacao;
        this.erro = "";
        mouse = janela.getMouse();
        fundo = new GameImage(URL.sprite("pontuacao.png"));
        btnSair = new Animation(URL.Animation("btnSairJogo.png")); 
        btnSair.y = 500;
        btnSair.x = 612;
        btnMenu = new Animation(URL.Animation("btnVoltarMenu.png"));
        btnMenu.y = 500;
        btnMenu.x = 100;
        SaveScore();
        run();
    }
    
    public janelaPontuacao(Window janela){
        this.janela = janela;
        this.pontuacao = 0;
        this.erro = "";
        mouse = janela.getMouse();
        fundo = new GameImage(URL.sprite("pontuacao.png"));
        btnSair = new Animation(URL.Animation("btnSairJogo.png")); 
        btnSair.y = 500;
        btnSair.x = 612;
        btnMenu = new Animation(URL.Animation("btnVoltarMenu.png"));
        btnMenu.y = 500;
        btnMenu.x = 100;
        
        run();
    }

    private void run(){
        
        while(true){
            fundo.draw();
            if(pontuacao != 0 && name != null){
                janela.drawText("Nome:"+name+"    Popularidade:"+pontuacao+"%",250,50,Color.black,"Arial",20);
            }
            showPontuacao();
            btnSair.draw();
            btnMenu.draw();
            janela.drawText(erro,20,20,Color.black,"Arial",15);
            if(mouse.isOverObject(btnSair)){
                btnSair.play();
                if(mouse.isLeftButtonPressed()){
                    janela.exit();
                }
            }else{
                btnSair.stop();
            }
            if(mouse.isOverObject(btnMenu)){
                btnMenu.play();
                if(mouse.isLeftButtonPressed()){
                    new Menu(janela);
                }
            }else{
                btnMenu.stop();
            }
            if(!mouse.isLeftButtonPressed()){}
            janela.update();
        }
    }
    
    private void SaveScore(){
        this.name = JOptionPane.showInputDialog("Qual o seu nome?");
        if(name != null){
            try{
                PontuacoesP pontosP = new PontuacoesP();
                PontuacoesE pontos = Tela2BD(); 
                pontosP.Incluir(pontos);
            }catch(Exception e){
                erro = e.getMessage();
            }
        }
    }

    private void showPontuacao() {
        try {
            PontuacoesP pontuacaoP = new PontuacoesP();
            resultados = pontuacaoP.Listar();
            BD2Tela();
        } catch (Exception e) {
            erro = e.getMessage();
        }
    }
    
    private PontuacoesE Tela2BD() throws ParseException
	{
		PontuacoesE pontuacao = new PontuacoesE();
                pontuacao.id = null;
		pontuacao.nome = this.name;
                Date data = new Date();
		pontuacao.data = data;
		pontuacao.pontuacao = this.pontuacao;
		return pontuacao;
	}
    
    private void BD2Tela(){
        for(int i = 0; i < resultados.size() ; i++){
            PontuacoesE result = resultados.get(i);
            writePontuacao(result.nome,result.pontuacao,result.data,i);
        }
    }
    
    private void writePontuacao(String nome,int popularidade,Date data,int posicao){
        janela.drawText(""+(posicao+1),250,(200 + posicao*30),Color.black,"Arial",15);
        janela.drawText(""+nome,300,(200 + posicao*30),Color.black,"Arial",15);
        janela.drawText(""+popularidade+"%",400,(200 + posicao*30),Color.black,"Arial",15);
        janela.drawText(""+data,460,(200 + posicao*30),Color.black,"Arial",15);
    }

    
}
    

