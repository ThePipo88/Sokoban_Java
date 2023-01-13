/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban.clases;

/**
 *
 * @author Pipo
 */
//Clase para crear la matriz logica de los mapas y retornarlos al controlador correspondiente
public class Mapas {
    
    public int [][] Mapa1 = {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                             {0,1,1,1,1,1,0,0,0,1,1,1,1,1,0,0},
                             {0,1,1,2,1,1,0,0,0,1,1,2,1,1,0,0},
                             {0,1,1,1,1,1,0,0,0,1,1,1,1,1,0,0},
                             {0,0,0,0,1,1,1,3,1,1,1,0,0,0,0,0},
                             {0,0,0,0,1,1,3,1,3,1,1,0,0,0,0,0},
                             {0,0,0,0,1,1,1,3,1,1,1,0,0,0,0,0},
                             {0,1,1,1,1,1,0,0,0,1,1,1,1,1,0,0},
                             {0,1,1,2,1,1,0,0,0,1,1,2,1,1,0,0},
                             {0,1,1,1,1,1,0,0,0,1,1,1,1,1,0,0},
                             {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
    
    public int [][] Mapa2 = {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                             {0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,0,0,0},
                             {0,0,0,0,0,3,1,1,0,0,0,0,0,0,0,0,0,0,0},
                             {0,0,0,0,0,1,1,3,0,0,0,0,0,0,0,0,0,0,0},
                             {0,0,0,1,1,3,1,3,1,0,0,0,0,0,0,0,0,0,0},
                             {0,0,0,1,0,1,0,0,1,0,0,0,0,0,0,0,0,0,0},
                             {0,1,1,1,0,1,0,0,1,0,0,0,0,0,1,1,1,1,0},
                             {0,1,3,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,0},
                             {0,0,0,0,0,1,0,0,0,1,0,1,0,0,1,1,1,1,0},
                             {0,0,0,0,0,1,1,1,1,1,0,0,0,0,0,0,0,0,0},
                             {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
    
    public int [][] Mapa3 = {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                             {0,0,0,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0,0},
                             {0,0,0,0,0,0,1,1,0,0,0,1,1,0,0,0,0,0,0},
                             {0,0,0,0,1,1,1,1,1,1,3,1,1,0,0,0,0,0,0},
                             {0,0,0,1,1,3,1,3,3,0,0,1,0,0,0,0,0,0,0},
                             {0,0,1,1,0,3,0,0,1,1,1,1,1,0,0,0,0,0,0},
                             {0,0,1,0,1,3,1,3,3,1,0,1,0,0,0,0,0,0,0},
                             {0,0,1,1,1,3,1,0,1,1,0,1,1,1,0,0,0,0,0},
                             {0,0,0,0,1,1,1,1,0,1,1,1,1,1,0,1,1,1,0},
                             {0,0,0,0,1,0,0,1,3,1,1,1,1,1,1,1,1,1,0},
                             {0,1,1,1,1,1,0,0,0,1,1,0,0,0,0,0,0,0,0},
                             {0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
                             {0,1,1,1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
                             {0,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
                             {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
    
    public int [][] Mapa4 = {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                             {0,0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0,0,0},
                             {0,1,1,1,1,1,0,1,1,1,3,1,1,0,1,1,0,0,0},
                             {0,1,0,0,0,0,0,0,0,3,0,0,0,0,1,1,0,0,0},
                             {0,1,0,1,1,1,1,0,0,1,0,1,1,0,3,1,1,1,0},
                             {0,1,0,1,3,1,1,3,1,1,0,1,1,0,1,1,0,1,0},
                             {0,1,0,1,3,1,1,0,1,1,1,1,1,0,1,1,1,1,0},
                             {0,1,0,1,1,0,0,0,1,0,0,1,1,1,1,1,0,1,0},
                             {0,1,0,0,0,1,1,0,1,1,0,1,1,0,3,1,1,1,0},
                             {0,1,0,1,1,1,1,0,1,3,0,0,0,0,1,1,0,1,0},
                             {0,1,0,3,1,1,1,3,1,1,3,1,1,0,2,1,1,1,0},
                             {0,1,1,1,1,1,1,0,1,1,1,1,1,0,1,1,0,1,0},
                             {0,0,0,0,1,1,0,0,0,1,1,1,1,0,2,1,1,1,0},
                             {0,0,0,0,1,1,1,1,1,1,1,0,0,0,1,1,1,1,0},
                             {0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0},
                             {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
    
    public int [][] Mapa5 = {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                             {0,0,0,0,0,0,0,1,1,1,1,1,1,0,1,1,1,0,0},
                             {0,0,0,0,0,0,1,1,2,1,3,1,1,1,1,3,1,0,0},
                             {0,0,0,0,0,1,1,2,1,0,1,0,1,0,3,1,0,0,0},
                             {0,0,0,0,1,1,2,1,0,1,0,1,0,1,3,1,1,0,0},
                             {0,0,0,0,1,1,1,0,1,1,0,1,1,1,1,0,1,0,0},
                             {0,1,1,0,0,1,0,1,1,1,1,1,1,1,1,1,1,0,0},
                             {0,1,1,3,1,3,1,0,0,0,1,1,0,1,0,1,0,0,0},
                             {0,1,3,1,1,1,3,1,1,1,0,1,0,1,1,1,0,0,0},
                             {0,0,0,3,3,1,1,1,0,1,0,1,0,1,0,1,0,0,0},
                             {0,0,0,1,1,1,3,1,1,1,0,1,0,1,0,0,0,0,0},
                             {0,0,0,1,3,0,1,0,0,0,0,0,1,1,1,1,1,1,0},
                             {0,0,0,3,1,1,1,0,0,0,0,0,1,1,1,0,1,1,0},
                             {0,0,0,1,1,0,0,0,0,0,0,0,0,1,1,1,1,1,0},
                             {0,0,0,1,1,0,0,0,0,0,0,0,0,1,1,1,1,0,0},
                             {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};
    
    public Mapas(){
           
    }
    
    public int getMapa1(int f, int c){
        return Mapa1[f][c];
    }
    
    public int getMapa2(int f, int c){
        return Mapa2[f][c];
    }
    
    public int getMapa3(int f, int c){
        return Mapa3[f][c];
    }
    
    public int getMapa4(int f, int c){
        return Mapa4[f][c];
    }
    
    public int getMapa5(int f, int c){
        return Mapa5[f][c];
    }
}