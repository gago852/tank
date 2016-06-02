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
import javax.swing.JFrame;

/**
 *
 * @author gabriel
 */
public class Ventana extends JFrame{
    public Canvas c;
     public Thread movieLoop;
    public static int world[][]={
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,2,2,0,2,0,0,0,2,2,2,0,2,2,2,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,2,2,0,2,2,2,2,2,2,2,2,0,2,2,2,2,2,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},        
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
        {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };
    public Mapa mapa;
    public Tanque j1;
    public Ventana(int w,int h)throws Exception
    {
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
        mapa=new Mapa(world,16);
        j1.loadPics(namesplayer);
        movieLoop=new Thread(new Runnable() {

            @Override
            public void run() {
                c.createBufferStrategy(2);
                Graphics g=c.getBufferStrategy().getDrawGraphics();                
                long startTime=System.currentTimeMillis();
                long currentTime=0;
                while(true){
                 try{
                mapa.draw(g);
                currentTime=System.currentTimeMillis()-startTime;
                        switch(j1.currentDirection){
                            case Tanque.RIGTH:{ j1.moveRigth(currentTime,world,50); break;}
                            case Tanque.DOWN:{j1.moveDown(currentTime,world,50); break;}
                            case Tanque.LEFT:{ j1.moveLeft(currentTime,world,50); break;}
                            case Tanque.UP:{j1.moveUp(currentTime,world,50); break;}
                        }
                        mapa.draw(g,w,h);
                        mapa.draw(g);
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
        Ventana p=new Ventana(1380,750);
        p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            p.setVisible(true);
            p.movieLoop.start();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        }
}
