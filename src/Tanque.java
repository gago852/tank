
import java.awt.Graphics;
import javax.swing.ImageIcon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gabriel
 */
public class Tanque {
    public static final int UP=0;
    public static final int RIGTH=1;
    public static final int DOWN=2;
    public static final int LEFT=3;
    public static final int NONE=-1;
    public String id;
    public int x;
    public int y;
    public int vx;
    public int vy;
    public boolean disparo;
    public int currentAnimation;
    public int currentDirection;
    Animacion[] animaciones;
    Bala bal;
    
    public Tanque(int x, int y, int vx, int vy){
        this.disparo=false;
        this.x=x;
        this.y=y;
        this.vx=vx;
        this.vy=vy;
        this.currentDirection=-1;
        animaciones=new Animacion[4];
        
    }
    
    public int getMatrixPositionX(int x, int tam){
        return x/tam;
    }
    
    public int getMatrixPositionY(int y, int tam){
        return y/tam;
    }
    
    public void loadPics(String[] names)throws Exception{
        for (int j=0;j<4;j++) {
            String name=names[j];
            animaciones[j]=new Animacion();
            for (int i = 1; i <= 2; i++) {
                animaciones[j].addEscena(
                new ImageIcon(getClass().getResource("assets/"+name+i+".png")).getImage()        
                        , 100);
            }
        }
       
    }
    public synchronized void disparo(long currentTime,int [][]world,Graphics g,Bala bala)
    {
        bal=bala;
        Thread balio ;
        disparo=false;
        try{
        switch(currentDirection){
                 case Tanque.RIGTH:{ 
                              
                   bala=new Bala(x+50, y, 8, 8);
                String []namesbala={"balaarriba","baladerecha","balaabajo","balaizquierda"};
                           bala.loadPics(namesbala);
                            bala.currentDirection=Tanque.RIGTH;
                           balio=new Thread(new Runnable() {

                       @Override
                       public void run() {
                           while(bal.estaActiva){try{
                          bal.moveRigth(currentTime, world, 50);
                                           bal.draw(g);
                           Thread.sleep(30);
                           }catch(Exception e)
                           {
                               e.printStackTrace();
                           }
                           }
                       }
                   });
                           break;}
                            case Tanque.DOWN:{                                
                               bala=new Bala(x, y+50, 8, 8);
                            String []namesbala={"balaarriba","baladerecha","balaabajo","balaizquierda"};
                            bala.loadPics(namesbala);
                            bala.currentDirection=Tanque.DOWN;
                            balio=new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                       while(bal.estaActiva)
                                       {
                                           try{
                                           bal.moveDown(currentTime, world, 50);
                                           bal.draw(g);
                                           Thread.sleep(30);
                                           }catch(Exception e)
                                           {
                                               e.printStackTrace();
                                           }
                                       }                                                
                                    }
                                });  
                                break;}
                            case Tanque.LEFT:{
                                bala=new Bala(x-50, y, 8, 8);
                            String []namesbala={"balaarriba","baladerecha","balaabajo","balaizquierda"};
                            bala.loadPics(namesbala);
                            bala.currentDirection=Tanque.LEFT;
                            balio=new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                       while(bal.estaActiva)
                                       {
                                           try{
                                           bal.moveLeft(currentTime, world, 50);
                                           bal.draw(g);
                                           Thread.sleep(30);
                                           }catch(Exception e)
                                           {
                                               e.printStackTrace();
                                           }
                                       }                                                
                                    }
                                });  
                                break;}
                            case Tanque.UP:{
                                bala=new Bala(x, y-50, 8, 8);
                            String []namesbala={"balaarriba","baladerecha","balaabajo","balaizquierda"};
                            bala.loadPics(namesbala);
                            bala.currentDirection=Tanque.UP;
                            balio=new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                       while(bal.estaActiva)
                                       {
                                           try{
                                           bal.moveUp(currentTime, world, 50);
                                           bal.draw(g);
                                           Thread.sleep(30);
                                           }catch(Exception e)
                                           {
                                               e.printStackTrace();
                                           }
                                       }                                                
                                    }
                                });  
                                break;}
        }
        }catch(Exception e)
        {
            e.printStackTrace();
        }              
                            
                        
    
    }
    public boolean isValid(int [][]matrix, int tam, int x, int y,int currentDirection){  
        if(currentDirection==Tanque.DOWN){
          if(matrix[getMatrixPositionY(y, tam)][getMatrixPositionX(x, tam)]==0&&matrix[getMatrixPositionY(y, tam)][getMatrixPositionX(x+50, tam)]==0){
              return true;}   }
        if(currentDirection==Tanque.LEFT){
          if(matrix[getMatrixPositionY(y, tam)][getMatrixPositionX(x, tam)]==0&&matrix[getMatrixPositionY(y+50, tam)][getMatrixPositionX(x, tam)]==0){
              return true;}   }
        if(currentDirection==Tanque.RIGTH){
          if(matrix[getMatrixPositionY(y, tam)][getMatrixPositionX(x, tam)]==0&&matrix[getMatrixPositionY(y+50, tam)][getMatrixPositionX(x, tam)]==0){
              return true;}   }
        if(currentDirection==Tanque.UP){
          if(matrix[getMatrixPositionY(y, tam)][getMatrixPositionX(x, tam)]==0&&matrix[getMatrixPositionY(y, tam)][getMatrixPositionX(x+50, tam)]==0){
              return true;}   }
        return false;
    }
    
    public void moveRigth(long time, int [][]matrix, int tam){
        
        if(isValid(matrix, tam, x+vx+animaciones[currentAnimation].getImage().getWidth(null), y,Tanque.RIGTH)){
            x+=vx;
            currentAnimation=Tanque.RIGTH;
            animaciones[Tanque.RIGTH].update(time);
        }
    }
    
    public void moveLeft(long time, int [][]matrix, int tam){
        
        if(isValid(matrix, tam, x-vx, y,Tanque.LEFT)){
        x-=vx;
        currentAnimation=Tanque.LEFT;
        animaciones[Tanque.LEFT].update(time);
        }
    }
    
     public void moveUp(long time, int [][]matrix,int tam){
         if(isValid(matrix, tam, x, y-vy,Tanque.UP)){
         y-=vy;
        currentAnimation=Tanque.UP;
        animaciones[Tanque.UP].update(time);
         }
    }
     
     public void moveDown(long time, int [][]matrix, int tam){
        if(isValid(matrix, tam, x, y+vy+animaciones[currentAnimation].getImage().getHeight(null),Tanque.DOWN)){
         
        y+=vy;
        currentAnimation=Tanque.DOWN;
        animaciones[Tanque.DOWN].update(time);
        }
    }
     
     public void draw(Graphics g){
         g.drawImage(animaciones[currentAnimation].getImage(),x,y,null);
     }

    public int getCurrentDirection() {
        return currentDirection;
    }
     
}
