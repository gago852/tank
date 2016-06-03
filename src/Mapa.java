/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author gabriel
 */
public class Mapa {
     public int world[][];
    public int tam;
    /**
     * constructor del mapa
     * @param world ubicaciones de cada cosa en el mapa
     * @param tam el tamaño de cada parte
     * @since vercion 1.0
     */
    public Mapa(int [][]world,int tam){
        this.world=world;
        this.tam=tam;
    }
    /**
     * dibuja el fondo
     * @param g donde se dibuja
     * @param w alto(creo)
     * @param h ancho (creo)
     * @since vercion 1.0
     */
    public synchronized void draw(Graphics g,int w,int h){
        g.setColor(Color.BLACK);
        g.fillRect(1,1,w,h);
    }
    /**
     * donde se dibuja todo los elementos del mapa
     * @param g donde se dibuja
     * @since version 1.0
     */
    public synchronized void draw(Graphics g){
       
        for (int i = 0; i < 17; i++) {            
            for (int j = 0; j < 23; j++) {
                switch(world[i][j]){                    
                    case 1:{
                        Image im;
                       im= new ImageIcon(getClass().getResource("assets"+"//"+"bloqueindestructible"+".png")).getImage();
                       g.drawImage(im,j*50 ,i*50 , null);                      
                        break;
                    }                   
                      case 2:{
                        Image im;
                       im= new ImageIcon(getClass().getResource("assets"+"//"+"bloquedestruible"+".png")).getImage();
                       g.drawImage(im,j*50 ,i*50 , null);                      
                        break;
                    }                  
                }
            }
            
        }
        
    }
     
}
