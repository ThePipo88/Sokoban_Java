/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban.Dto;

import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import sokoban.model.Mapa;
import sokoban.model.Sokoban;

/**
 *
 * @author Pipo
 */

@XmlRootElement(name = "JuegoDto")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class JuegoDto {
    
    //Se crean los archivos para xmlTransient
    @XmlTransient
    public SimpleStringProperty jgNombre;
    @XmlTransient
    public SimpleStringProperty jgPasos;
    @XmlTransient
    public SimpleStringProperty jgPosicionJugador;
    @XmlTransient
    public SimpleStringProperty jgFilasCajas;
    @XmlTransient
    public SimpleStringProperty jgColumnasCajas;
    
    public JuegoDto(){
        this.jgNombre = new SimpleStringProperty();
        this.jgPasos = new SimpleStringProperty();
        this.jgPosicionJugador  = new SimpleStringProperty();
        this.jgFilasCajas  = new SimpleStringProperty();
        this.jgColumnasCajas  = new SimpleStringProperty();
    }
    
    public JuegoDto(Mapa mapa){
        this();
        this.jgNombre.set(mapa.getMpNombre());
        this.jgPasos.set(mapa.getMpPasos().toString());
        this.jgPosicionJugador.set(mapa.getMpPosicionjugador());
        this.jgFilasCajas.set(mapa.getMpFilascajas());
        this.jgColumnasCajas.set(mapa.getMpColumnascajas());
    }
    
    public void setJgNombre(String nombre){
        this.jgNombre.set(nombre);
    }
    
    public String getJgNombre(){
        return jgNombre.get();
    }
    
    public void setJgPasos(String pasos){
        this.jgPasos.set(pasos);
    }
    
    public String getJgPasos(){
        return jgPasos.get();
    }
    
    public void setJgPosicionJugador(String posicionJugador){
        this.jgPosicionJugador.set(posicionJugador);
    }
    
    public String getJgPosicionJugador(){
        return jgPosicionJugador.get();
    }
    
    public void setJgFilasCajas(String filas){
        this.jgFilasCajas.set(filas);
    }
    
    public String getJgFilasCajas(){
        return jgFilasCajas.get();
    }
    
    public void setJgColumnasCajas(String columnas){
        this.jgColumnasCajas.set(columnas);
    }
    
    public String getJgColumnasCajas(){
        return jgColumnasCajas.get();
    }
    
}
