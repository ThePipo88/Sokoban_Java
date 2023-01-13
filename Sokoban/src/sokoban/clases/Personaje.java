/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban.clases;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Pipo
 */
//Clase para crear el personaje y retornarlo
public class Personaje {
   private final int Pselec;
   private int lado;
   private double cx;
   private double cy;
   private ImageView jg;
   private int n1 = 0;
   private int n2 = 0;
   private int n3 = 0;
   private int n4 = 0;
   
   public Personaje(int P, int l, double x, double y){
    this.Pselec = P;
    this.lado = l;
   }
   //Metodo para retornar un poersonaje, el ciclo va aumentando conforme se solicite el metodo, llegando hasta 4 que es el maximo movimiento que puede dar el personaje y se devuelve a el inicio
    public ImageView getJugador() {
        if (n1 == 4 || n2 == 4 || n3 == 4 || n4 == 4){
         n1 = 0;  
         n2 = 0;
         n3 = 0;
         n4 = 0;   
        }
    if(Pselec == 1){
        if(lado == 1){
           if(n1 == 0){
             Image jug = new Image("/sokoban/resources/P0.png",32,55,false,true);  
             jg = new ImageView(jug);
             n1++;
           }else if(n1 == 1){
             Image jug = new Image("/sokoban/resources/P1.png",32,55,false,true);  
             jg = new ImageView(jug);  
             n1++;
           }else if(n1 == 2){
             Image jug = new Image("/sokoban/resources/P0.png",32,55,false,true);  
             jg = new ImageView(jug);  
             n1++;
           }else if(n1 == 3){
             Image jug = new Image("/sokoban/resources/P2.png",32,55,false,true);  
             jg = new ImageView(jug);  
             n1++;
           }
           n2 = 0;
           n3 = 0;
           n4 = 0;
        } else if(lado == 2){
           if(n2 == 0){
             Image jug = new Image("/sokoban/resources/P3.png",32,55,false,true);  
             jg = new ImageView(jug);
             n2++;
           }else if(n2 == 1){
             Image jug = new Image("/sokoban/resources/P4.png",32,55,false,true);  
             jg = new ImageView(jug);  
             n2++;
           }else if(n2 == 2){
             Image jug = new Image("/sokoban/resources/P3.png",32,55,false,true);  
             jg = new ImageView(jug);  
             n2++;
           }else if(n2 == 3){
             Image jug = new Image("/sokoban/resources/P5.png",32,55,false,true);  
             jg = new ImageView(jug);  
             n2++;
           }
           n1 = 0;
           n3 = 0;
           n4 = 0;
        } else if(lado == 3){
           if(n3 == 0){
             Image jug = new Image("/sokoban/resources/P6.png",32,55,false,true);  
             jg = new ImageView(jug);
             n3++;
           }else if(n3 == 1){
             Image jug = new Image("/sokoban/resources/P7.png",32,55,false,true);  
             jg = new ImageView(jug);  
             n3++;
           }else if(n3 == 2){
             Image jug = new Image("/sokoban/resources/P6.png",32,55,false,true);  
             jg = new ImageView(jug);  
             n3++;
           }else if(n3 == 3){
             Image jug = new Image("/sokoban/resources/P8.png",32,55,false,true);  
             jg = new ImageView(jug);  
             n3++;
           }
           n1 = 0;
           n2 = 0;
           n4 = 0;
        } else if(lado == 4){
           if(n4 == 0){
             Image jug = new Image("/sokoban/resources/P9.png",32,55,false,true);  
             jg = new ImageView(jug);
             n4++;
           }else if(n4 == 1){
             Image jug = new Image("/sokoban/resources/P10.png",32,55,false,true);  
             jg = new ImageView(jug);  
             n4++;
           }else if(n4 == 2){
             Image jug = new Image("/sokoban/resources/P9.png",32,55,false,true);  
             jg = new ImageView(jug);  
             n4++;
           }else if(n4 == 3){
             Image jug = new Image("/sokoban/resources/P11.png",32,55,false,true);  
             jg = new ImageView(jug);  
             n4++;
           }
           n1 = 0;
           n2 = 0;
           n3 = 0;
        }
    }
       return jg; 
    }
    
    //Setters y getters para el lado escogido del personaje
    
    public void setX(double x){
        this.cx = x;
    }
    
    public void setY(double y){
        this.cy = y;
    }
    
    public void setL(int la){
        this.lado = la;
    }
    
    public double getX(){
        return cx;
    }
    
    public double getY(){
        return cy;
    }
}