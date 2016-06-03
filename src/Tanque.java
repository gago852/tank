
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
    /**
     * constructor del tanque
     * @param x posicon en x
     * @param y posicion en y
     * @param vx su velocidad en x
     * @param vy su velocidad en y
     */
    public Tanque(int x, int y, int vx, int vy){
        this.disparo=false;
        this.x=x;
        this.y=y;
        this.vx=vx;
        this.vy=vy;
        this.currentDirection=-1;
        animaciones=new Animacion[4];
        
    }
    /**
     * funcioon que devuelve la posicion del tanque en la matrriz de coliciones desde las x
     * @param x posicion en x del tanque en el mapa
     * @param tam tamaño
     * @return entero que da la posicion en las x
     */
    public int getMatrixPositionX(int x, int tam){
        return x/tam;
    }
    /**
     * fincion que devuelve la posicion del tanuqe en la tarizz de colicones desde las y
     * @param y posicion en y del tanque en el mapa
     * @param tam tamaño
     * @return entero que da la posiicon en la y
     */
    public int getMatrixPositionY(int y, int tam){
        return y/tam;
    }
    /**
     * metodo para cargar las animaciones del tanque
     * @param names nobres de los archivos
     * @throws Exception cuando no hay animaciones
     */
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
    /**
     * metodo ahora inservible
     * @param currentTime tienpo corrido
     * @param world mapa
     * @param g donde se pinta
     * @param bala  la bala
     */
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
    /**
     * metodo para saver si esta colicionando
     * @param matrix matrix de colivion
     * @param tam tamaño del mapa
     * @param x posision en x en el mapa
     * @param y lo mismo pero en y
     * @param currentDirection la dirreccion para donde va
     * @return si se puede o no
     */
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
    /**
     * metodo para moverse a la derecha
     * @param time tiempo
     * @param matrix matris de colicones
     * @param tam  tamaño
     */
    public void moveRigth(long time, int [][]matrix, int tam){
        
        if(isValid(matrix, tam, x+vx+animaciones[currentAnimation].getImage().getWidth(null), y,Tanque.RIGTH)){
            x+=vx;
            currentAnimation=Tanque.RIGTH;
            animaciones[Tanque.RIGTH].update(time);
        }
    }
    /**
     * metodo para moverse a la izquierda
     * @param time tiempo
     * @param matrix matris de colicones
     * @param tam  tamaño
     */
    public void moveLeft(long time, int [][]matrix, int tam){
        
        if(isValid(matrix, tam, x-vx, y,Tanque.LEFT)){
        x-=vx;
        currentAnimation=Tanque.LEFT;
        animaciones[Tanque.LEFT].update(time);
        }
    }
    /**
     * metodo para moverse a la arriba
     * @param time tiempo
     * @param matrix matris de colicones
     * @param tam  tamaño
     */
     public void moveUp(long time, int [][]matrix,int tam){
         if(isValid(matrix, tam, x, y-vy,Tanque.UP)){
         y-=vy;
        currentAnimation=Tanque.UP;
        animaciones[Tanque.UP].update(time);
         }
    }
     /**
      * metodo para moverse a la abajo
      * @param time tiempo
     * @param matrix matris de colicones
     * @param tam  tamaño
      */
     public void moveDown(long time, int [][]matrix, int tam){
        if(isValid(matrix, tam, x, y+vy+animaciones[currentAnimation].getImage().getHeight(null),Tanque.DOWN)){
         
        y+=vy;
        currentAnimation=Tanque.DOWN;
        animaciones[Tanque.DOWN].update(time);
        }
    }
     /**
      * metodo para dibujar el tanque
      * @param g donde se dibjuka 
      */
     public void draw(Graphics g){
         g.drawImage(animaciones[currentAnimation].getImage(),x,y,null);
     }
/**
 * esto nunca lo uso
 * @return etc
 */
    public int getCurrentDirection() {
        return currentDirection;
    }
     
}
