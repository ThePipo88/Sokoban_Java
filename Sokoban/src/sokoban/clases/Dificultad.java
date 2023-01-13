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
//Clase usada para gurdar informacion del Table View dificultad
public class Dificultad {
    
    private final String mapa;
    
    private final String facil;
    
    private final String medio;
    
    private final String dificil;

    public Dificultad(String mapa, String facil, String medio, String dificil) {
        this.mapa = mapa;
        this.facil = facil;
        this.medio = medio;
        this.dificil = dificil;
    }

    public String getMapa() {
        return mapa;
    }

    public String getFacil() {
        return facil;
    }

    public String getMedio() {
        return medio;
    }

    public String getDificil() {
        return dificil;
    }
   
}
