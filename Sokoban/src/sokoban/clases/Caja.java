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
public class Caja {
    private ImageView ca;
    private final int tcaja;

    public Caja(int tpCaja){
        this.tcaja = tpCaja;
    }
    
    //Metodo para retornar una caja para el mapa 1
    public ImageView getCaja(){
        Image jug = new Image("/sokoban/resources/Caja" + tcaja + ".png",45,45,false,true);  
        ca = new ImageView(jug);
        return ca;   
    }
    
    //Metodo para retornar una caja para el mapa 2
    public ImageView getCaja2(){
        Image jug = new Image("/sokoban/resources/Caja" + tcaja + ".png",40,40,false,true);  
        ca = new ImageView(jug);
        return ca;   
    }
    
    //Metodo para retornar una caja para el mapa 3
    public ImageView getCaja3(){
        Image jug = new Image("/sokoban/resources/Caja" + tcaja + ".png",42,35,false,true);  
        ca = new ImageView(jug);
        return ca;   
    }

}
