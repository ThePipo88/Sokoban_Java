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
import sokoban.model.Sokoban;

/**
 *
 * @author Pipo
 */

@XmlRootElement(name = "MenuDto")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class MenuDto {
    
    //Se crean los archivos para xmlTransient
    @XmlTransient
    public SimpleStringProperty mnNombre;
    @XmlTransient
    public SimpleStringProperty mnMapa;
    @XmlTransient
    public SimpleStringProperty mnDificultad;
    
    public MenuDto(){
        this.mnNombre = new SimpleStringProperty();
        this.mnMapa = new SimpleStringProperty();
        this.mnDificultad  = new SimpleStringProperty();
    }
    
    public MenuDto(Sokoban sokoban){
        this();
        this.mnNombre.set(sokoban.getSkNombre());
        this.mnMapa.set(sokoban.getSkMapa().toString());
        this.mnDificultad.set(sokoban.getSkDificultad());
    }
    
    public MenuDto(String nombre,String mapa, String dificultad){
        this();
        this.mnNombre.set(nombre);
        this.mnMapa.set(mapa);
        this.mnDificultad.set(dificultad);
    }
    
    public void setMnNombre(String nombre){
        this.mnNombre.set(nombre);
    }
    
    public String getMnNombre(){
        return mnNombre.get();
    }
    
    public void setMnMapa(String mapa){
        this.mnMapa.set(mapa);
    }
    
    public String getMnMapa(){
        return mnMapa.get();
    }
    public void setMnDificultad(String dificultad){
        this.mnDificultad.set(dificultad);
    }
    
    public String getMnDificultad(){
        return mnDificultad.get();
    }
    
}
