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
    
    public Mapa(int [][]world,int tam){
        this.world=world;
        this.tam=tam;
    }
    public void draw(Graphics g,int w,int h){
        g.setColor(Color.BLACK);
        g.fillRect(1,1,w,h);
    }
    public void draw(Graphics g){
       
        for (int i = 0; i < 12; i++) {            
            for (int j = 0; j < 17; j++) {
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
