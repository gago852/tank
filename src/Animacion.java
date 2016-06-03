
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
    /**
     * metodo que inicializa la animacion
     * @since version 1.0
     */
    public synchronized void start(){
        movieTime=0;
        escenaIndex=0;
    }
    /** 
     * constructor para las animaciones
     * @since vercion 1.0
     */
    public Animacion(){
        escenas = new ArrayList<EscenaUno>();
        totalTime=0;
        start();
    }
    /**
     * metodo que da la animacion
     * @param timePassed tiempo que a pasado
     * @since vercion 1.0
     */
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
    /**
     * metodo que añade fragmentos de animacion 
     * @param i imagen
     * @param t en cual fotograma va
     * @since vercion 1.0
     */
    public synchronized void addEscena(Image i, long t){
        totalTime+=t;
        escenas.add(new EscenaUno(i, totalTime));
    }
    /**
     * funcion que devuelve una escena
     * @param x indice en la lista de escenas
     * @return una escena
     * @since version 1.0
     */
    private EscenaUno getScene(int x){return escenas.get(x);}
    /**
     * fincion que entrega una imagen
     * @return imagen
     * @since vercion 1.0
     */
    public synchronized  Image getImage(){
        if(escenas.isEmpty()){
            return null;
        }else{
           return getScene(escenaIndex).pic;
        }
    }/**
     * clase constructora para las escenas
     * @since vercion 1.0
     */
     private class EscenaUno{
        Image pic;
        long endTime;
    /**
     * constructor de la contructora de escenas
     * @param pic imagen
     * @param endTime tiempo de la imagen
     * @since vercion 1.0
     */
        public EscenaUno(Image pic, long endTime){
            this.pic=pic;
            this.endTime=endTime;
        }
    }
}
