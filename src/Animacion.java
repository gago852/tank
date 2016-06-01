
import java.awt.Image;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gabriel
 */
public class Animacion {
     private ArrayList<EscenaUno> escenas;
    private int escenaIndex;
    private long movieTime;
    private long totalTime;
    
    public synchronized void start(){
        movieTime=0;
        escenaIndex=0;
    }
    
    public Animacion(){
        escenas = new ArrayList<EscenaUno>();
        totalTime=0;
        start();
    }
    
    public synchronized void update(long timePassed){
        if(escenas.size()>1){
            if(timePassed > totalTime){
                movieTime=timePassed-((int)(timePassed/totalTime))*totalTime;
                escenaIndex=0;
            }else{
                movieTime=timePassed;
            }
            
            while(movieTime > getScene(escenaIndex).endTime){
                escenaIndex++;
            }
        }
        
    }
    public synchronized void addEscena(Image i, long t){
        totalTime+=t;
        escenas.add(new EscenaUno(i, totalTime));
    }
    
    private EscenaUno getScene(int x){return escenas.get(x);}
    
    public synchronized  Image getImage(){
        if(escenas.isEmpty()){
            return null;
        }else{
           return getScene(escenaIndex).pic;
        }
    }
     private class EscenaUno{
        Image pic;
        long endTime;
    
        public EscenaUno(Image pic, long endTime){
            this.pic=pic;
            this.endTime=endTime;
        }
    }
}
