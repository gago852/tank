
import java.io.BufferedInputStream;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gago8
 */
public class Sonido {
    public Sonido()
    {
        
    }
    /**
     * metodo para el sonido
     * @throws LineUnavailableException no se que significa esto
     * @throws IOException cuando no existe audio
     * @throws UnsupportedAudioFileException cuando el audio no es compatible
     */
    public  void cargarsonido() throws LineUnavailableException,IOException,UnsupportedAudioFileException
            {
                BufferedInputStream bis=new BufferedInputStream(getClass().getResourceAsStream("assets/Lucian stole my bike .wav"));
                AudioInputStream ais=AudioSystem.getAudioInputStream(bis);
                Clip sonido=AudioSystem.getClip();
                sonido.loop(Clip.LOOP_CONTINUOUSLY);
                sonido.open(ais);
                sonido.start();
            }
}
