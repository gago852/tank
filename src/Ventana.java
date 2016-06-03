/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

/**
 *
 * @author gabriel
 */
public class Ventana extends JFrame{
    public Canvas c;
     public Thread movieLoop;
     public Thread sonido;
     public Bala bala;
     public boolean sw=true;
     public ArrayList<Enemigo> enemigos=new ArrayList<Enemigo>();
    public static int world[][]={
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,2,0,0,2,2,2,2,0,0,2,0,0,2,2,0,0,2,0,0,1},
        {1,0,0,2,0,0,2,0,0,2,0,0,2,0,0,2,2,0,0,2,0,0,1},
        {1,0,0,2,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,1},
        {1,0,0,2,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,0,0,0,1},
        {1,0,0,2,2,2,2,0,0,2,2,2,2,0,0,0,0,0,0,2,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0,2,0,0,1},        
        {1,0,0,2,2,2,2,0,0,2,2,2,2,2,2,2,2,0,0,2,0,0,1},        
        {1,0,0,2,0,0,0,0,0,2,0,0,2,0,0,0,0,0,0,2,0,0,1},        
        {1,0,0,2,0,0,0,0,0,2,0,0,2,0,0,0,0,0,0,0,0,0,1},        
        {1,0,0,2,0,0,2,0,0,2,0,0,2,0,0,0,0,0,0,0,0,0,1},        
        {1,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,1},        
        {1,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,1},
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };
    public Mapa mapa;
    public Tanque j1;
    public Enemigo en;
    public Thread enemigo;
    public Thread enemigoss;
    public Ventana(int w,int h)throws Exception
    {
        sonido=new Thread(new Runnable() {

            @Override
            public void run() {
                
               Sonido son=new Sonido();
                try {
                    son.cargarsonido();
                } catch (Exception ex) {
                    ex.printStackTrace();
                } 
            }
        });
        c= new Canvas();
        this.setSize(w, h);
        c.setSize(w, h);
        this.add(c);
        this.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                
            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()){
                   case KeyEvent.VK_UP:{j1.currentDirection=Tanque.UP; break;}
                   case KeyEvent.VK_DOWN:{j1.currentDirection=Tanque.DOWN; break;}
                   case KeyEvent.VK_LEFT:{j1.currentDirection=Tanque.LEFT; break;}
                   case KeyEvent.VK_RIGHT:{j1.currentDirection=Tanque.RIGTH; break;}
                   case KeyEvent.VK_SPACE:{j1.disparo=true;break;}
               }  
            }
            @Override
            public void keyReleased(KeyEvent e) {
               switch(e.getKeyCode()){
                   case KeyEvent.VK_UP:{j1.currentDirection=Tanque.NONE; break;}
                   case KeyEvent.VK_DOWN:{j1.currentDirection=Tanque.NONE; break;}
                   case KeyEvent.VK_LEFT:{j1.currentDirection=Tanque.NONE; break;}
                   case KeyEvent.VK_RIGHT:{j1.currentDirection=Tanque.NONE; break;}
               }
            }
        });
        j1=new Tanque(120,120, 5, 5);
        String []namesplayer={"jugadorarriba","jugadorderecha","jugadorabajo","jugadorizquierda"};
        mapa=new Mapa(world,50);
        j1.loadPics(namesplayer);
        movieLoop=new Thread(new Runnable() {

            @Override
            public void run() {
                c.createBufferStrategy(2);
               Graphics  g=c.getBufferStrategy().getDrawGraphics();                
                long startTime=System.currentTimeMillis();
              long  currentTime=0;
              enemigo=new Thread(new Runnable() {

                         @Override
                         public void run() {
                             for(int i=0;i<5;i++)
                             {
                                 try{
                                     Random rand=new Random();
                                 Enemigo enemi=new Enemigo((int) rand.nextDouble()* 200-50, (int) rand.nextDouble()* 200-50, 5, 5);
                                 String []namesenemigo={"enemigoarriba","enemigoderecha","enemigoabajo","enemigoizquierda"};
                                 enemi.loadPics(namesenemigo);
                                 enemigos.add(enemi);
                                 Thread.sleep(100);
                                 }catch(Exception e)
                                 {
                                     e.printStackTrace();
                                 }
                             }
                         }
                     });
              enemigo.start();
                while(true){
                 try{                     
                     mapa.draw(g,w,h);
                mapa.draw(g);
                currentTime=System.currentTimeMillis()-startTime;
                        switch(j1.currentDirection){
                            case Tanque.RIGTH:{ j1.moveRigth(currentTime,world,50); break;}
                            case Tanque.DOWN:{j1.moveDown(currentTime,world,50); break;}
                            case Tanque.LEFT:{ j1.moveLeft(currentTime,world,50); break;}
                            case Tanque.UP:{j1.moveUp(currentTime,world,50); break;}
                        } 
                        if (j1.disparo) {
                            j1.disparo(currentTime, world, g,bala);                         
                     }
                        enemigoss=new Thread(new Runnable() {

                         @Override
                         public void run() {
                             for (int i = 0; i < enemigos.size(); i++) {
                                 try{
                                 en=enemigos.get(i);
                                 Thread.sleep(100);
                                 }catch(Exception e)
                                 {
                                     e.printStackTrace();
                                 }
                             }
                         }
                     });
                      /*  if (sw) {
                         enemigoss.start();
                         sw=false;
                     }else{
                        en.draw(g);}*/
                        j1.draw(g);
                        Thread.sleep(30);
                c.getBufferStrategy().show();
                 }catch(Exception e)
                 {
                     e.printStackTrace();
                 }
            }}
        });
       
        
    }
    public static void main(String[] args) throws Exception {
        try{
        Ventana p=new Ventana(1220,950);
        p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            p.setVisible(true);
            p.sonido.start();
            p.movieLoop.start();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        }
}
