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
    
    public void draw(Graphics g){
       
        for (int i = 0; i < 10; i++) {            
            for (int j = 0; j < 52; j++) {
                switch(world[i][j]){
                    case 0:{
                        g.setColor(Color.BLACK);
                        g.fillRect(j*tam,i*tam,tam,tam);
                        System.out.println("blanco "+i+" "+"j");
                        break;
                    }
                    case 1:{
                        Image im;
                       im= new ImageIcon(getClass().getResource("assets"+"//"+"bloqueindestructible"+".png")).getImage();
                       g.drawImage(im,j*16 ,i*16 , null);                      
                        break;
                    }                   
                      case 2:{
                        Image im;
                       im= new ImageIcon(getClass().getResource("assets"+"//"+"bloquedestruible"+".png")).getImage();
                       g.drawImage(im,j*16 ,i*16 , null);                      
                        break;
                    }                  
                }
            }
            
        }
        
    }
     
}
