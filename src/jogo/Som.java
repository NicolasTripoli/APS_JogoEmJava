/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import jplay.Sound;
import jplay.URL;

/**
 *
 * @author User
 */
public class Som {
    private static Sound musica;
    private static float volume;
    
    public static void volume(float volume){
        Som.volume = volume;
        musica.setVolume(volume);
    }
    public static float getVolume(){
        return Som.volume;
    }
    
    public static void play(String audio){
        stop();
        musica = new Sound(URL.audio(audio));
        musica.setVolume(volume);
        Som.musica.play();
        Som.musica.setRepeat(true);
    }

   public static void stop(){
      if(Som.musica != null){
          musica.stop();
      } 
   }    
}
