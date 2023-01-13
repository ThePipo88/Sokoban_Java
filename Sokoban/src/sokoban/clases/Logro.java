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
//Metodo para agregar informacion al TableView logros
public class Logro {
    
    private final String nombre;
    
    private final String primerLogro;
    
    private final String segundoLogro;
    
    private final String tercerLogro;
    
    private final String cuartoLogro;
    
    private final String quintoLogro;
    
    private final String sextoLogro;

    public Logro(String nombre, String primerLogro, String segundoLogro, String tercerLogro, String cuartoLogro, String quintoLogro, String sextoLogro) {
        this.nombre = nombre;
        this.primerLogro = primerLogro;
        this.segundoLogro = segundoLogro;
        this.tercerLogro = tercerLogro;
        this.cuartoLogro = cuartoLogro;
        this.quintoLogro = quintoLogro;
        this.sextoLogro = sextoLogro;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPrimerLogro() {
        return primerLogro;
    }

    public String getSegundoLogro() {
        return segundoLogro;
    }

    public String getTercerLogro() {
        return tercerLogro;
    }

    public String getCuartoLogro() {
        return cuartoLogro;
    }

    public String getQuintoLogro() {
        return quintoLogro;
    }

    public String getSextoLogro() {
        return sextoLogro;
    }
    
    
}
