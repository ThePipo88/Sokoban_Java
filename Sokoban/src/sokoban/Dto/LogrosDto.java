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
import sokoban.model.Logros;
import sokoban.model.Mapa;

/**
 *
 * @author Pipo
 */

@XmlRootElement(name = "LogrosDto")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class LogrosDto {
    
    @XmlTransient
    public SimpleStringProperty lgNombre;    
    @XmlTransient
    public SimpleStringProperty lgPrimerLogro;   
    @XmlTransient
    public SimpleStringProperty lgSegundoLogro;
    @XmlTransient
    public SimpleStringProperty lgTercerLogro;
    @XmlTransient
    public SimpleStringProperty lgCuartoLogro;
    @XmlTransient
    public SimpleStringProperty lgQuintoLogro;
    @XmlTransient
    public SimpleStringProperty lgSextoLogro;
    
    public LogrosDto(){
        this.lgNombre = new SimpleStringProperty();
        this.lgPrimerLogro = new SimpleStringProperty();
        this.lgSegundoLogro  = new SimpleStringProperty();
        this.lgTercerLogro  = new SimpleStringProperty();
        this.lgCuartoLogro  = new SimpleStringProperty();
        this.lgQuintoLogro  = new SimpleStringProperty();
        this.lgSextoLogro  = new SimpleStringProperty();
    }
    
    public LogrosDto(Logros logros){
        this();
        this.lgNombre.set(logros.getLgNombre());
        this.lgPrimerLogro.set(logros.getLgLogro1());
        this.lgSegundoLogro.set(logros.getLgLogro2());
        this.lgTercerLogro.set(logros.getLgLogro3());
        this.lgCuartoLogro.set(logros.getLgLogro4());
        this.lgQuintoLogro.set(logros.getLgLogo5());
        this.lgSextoLogro.set(logros.getLgLogro6());
        
    }
    
    public void setLgNombre(String nombre){
        this.lgNombre.set(nombre);
    }
    
    public String getLgNombre(){
        return lgNombre.get();
    }
    
    public void setLgPrimerLogro(String primer){
        this.lgPrimerLogro.set(primer);
    }
    
    public String getLgPrimerLogro(){
        return lgPrimerLogro.get();
    }
    
    public void setLgSegndoLogro(String segundo){
        this.lgSegundoLogro.set(segundo);
    }
    
    public String getLgSegundoLogro(){
        return lgSegundoLogro.get();
    }
    
    public void setLgTercerLogro(String tercer){
        this.lgTercerLogro.set(tercer);
    }
    
    public String getLgTercerLogro(){
        return lgTercerLogro.get();
    }
    
    public void setLgCuartoLogro(String cuarto){
        this.lgCuartoLogro.set(cuarto);
    }
    
    public String getLgCuartoLogro(){
        return lgCuartoLogro.get();
    }
    
    public void setLgQuintoLogro(String quinto){
        this.lgQuintoLogro.set(quinto);
    }
    
    public String getLgQuintoLogro(){
        return lgQuintoLogro.get();
    }
    
    public void setLgSextoLogro(String sexto){
        this.lgSextoLogro.set(sexto);
    }
    
    public String getLgSextoLogro(){
        return lgSextoLogro.get();
    }
}
