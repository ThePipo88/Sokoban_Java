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
//Metodo para agregar informacion al TableView cargar partida
public class Jugador {
    
    private final String nombre;
    
    private final String mapa;
    
    private final String dificultad;

    public Jugador(String nombre, String mapa, String dificultad) {
        this.nombre = nombre;
        this.mapa = mapa;
        this.dificultad = dificultad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMapa() {
        return mapa;
    }

    public String getDificultad() {
        return dificultad;
    } 
    
}
