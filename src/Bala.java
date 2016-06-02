
import java.awt.Graphics;
import javax.swing.ImageIcon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gago8
 */
public class Bala {
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
    public String path;
    public int currentAnimation;
    public int currentDirection;
    Animacion[] animaciones;
    public boolean estaActiva;
    
    public Bala(int x, int y, int vx, int vy, String path){
        this.path=path;
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
            for (int i = 1; i <= 5; i++) {
                System.out.println(path+"/assets/Sprites"+name+i+".png");
                animaciones[j].addEscena(
                new ImageIcon(getClass().getResource(path+"/assets/Sprites"+name+i+".png")).getImage()        
                        , 100);
            }
        }
       
    }
    public boolean isValid(int [][]matrix, int tam, int x, int y){
          
         return  matrix[getMatrixPositionY(y, tam)][getMatrixPositionX(x, tam)]==0||matrix[getMatrixPositionY(y, tam)][getMatrixPositionX(x, tam)]==3;
    }
    
    public void moveRigth(long time, int [][]matrix, int tam){
        
        if(isValid(matrix, tam, x+vx+animaciones[currentAnimation].getImage().getWidth(null), y)){
            x+=vx;
            currentAnimation=Bala.RIGTH;
            
        }
    }
    
    public void moveLeft(long time, int [][]matrix, int tam){
        
        if(isValid(matrix, tam, x-vx, y)){
        x-=vx;
        currentAnimation=Bala.LEFT;
        
        }
    }
    
     public void moveUp(long time, int [][]matrix,int tam){
         if(isValid(matrix, tam, x, y-vy)){
         y-=vy;
        currentAnimation=Bala.UP;
        
         }
    }
     
     public void moveDown(long time, int [][]matrix, int tam){
        if(isValid(matrix, tam, x, y+vy+animaciones[currentAnimation].getImage().getHeight(null))){
         
        y+=vy;
        currentAnimation=Bala.DOWN;
        
        }
    }
     
     public void draw(Graphics g){
         g.drawImage(animaciones[currentAnimation].getImage(),x,y,null);
     }
}
