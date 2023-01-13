/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import sokoban.Dto.JuegoDto;
import sokoban.Dto.LogrosDto;

/**
 *
 * @author Pipo
 */
@Entity
@Table(name = "LOGROS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Logros.findAll", query = "SELECT l FROM Logros l")
    , @NamedQuery(name = "Logros.findByLgNombre", query = "SELECT l FROM Logros l WHERE l.lgNombre = :nombre")
    , @NamedQuery(name = "Logros.findByLgLogro1", query = "SELECT l FROM Logros l WHERE l.lgLogro1 = :lgLogro1")
    , @NamedQuery(name = "Logros.findByLgLogro2", query = "SELECT l FROM Logros l WHERE l.lgLogro2 = :lgLogro2")
    , @NamedQuery(name = "Logros.findByLgLogro3", query = "SELECT l FROM Logros l WHERE l.lgLogro3 = :lgLogro3")
    , @NamedQuery(name = "Logros.findByLgLogro4", query = "SELECT l FROM Logros l WHERE l.lgLogro4 = :lgLogro4")
    , @NamedQuery(name = "Logros.findByLgLogo5", query = "SELECT l FROM Logros l WHERE l.lgLogo5 = :lgLogo5")
    , @NamedQuery(name = "Logros.findByLgLogro6", query = "SELECT l FROM Logros l WHERE l.lgLogro6 = :lgLogro6")})
public class Logros implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "LG_NOMBRE")
    private String lgNombre;
    @Column(name = "LG_LOGRO1")
    private String lgLogro1;
    @Column(name = "LG_LOGRO2")
    private String lgLogro2;
    @Column(name = "LG_LOGRO3")
    private String lgLogro3;
    @Column(name = "LG_LOGRO4")
    private String lgLogro4;
    @Column(name = "LG_LOGO5")
    private String lgLogo5;
    @Column(name = "LG_LOGRO6")
    private String lgLogro6;
    @JoinColumn(name = "LG_NOMBRE", referencedColumnName = "SK_NOMBRE", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Sokoban sokoban;

    public Logros() {
    }
    
    public Logros(String lgNombre, String lgLogro1, String lgLogro2, String lgLogro3, String lgLogro4, String lgLogro5, String lgLogro6) {       
        this.lgNombre = lgNombre;
        this.lgLogro1 = lgLogro1;
        this.lgLogro2 = lgLogro2;
        this.lgLogro3 = lgLogro3;
        this.lgLogro4 = lgLogro4;
        this.lgLogo5 = lgLogro5;
        this.lgLogro6 = lgLogro6;
    }
    
    public Logros(LogrosDto logros) {
        this.lgNombre = logros.getLgNombre();
        //actualizarLogros(logros);
    }
    
    public void actualizarLogros(String lgNombre, String lgLogro1, String lgLogro2, String lgLogro3, String lgLogro4, String lgLogro5, String lgLogro6){
        this.lgNombre = lgNombre;
        this.lgLogro1 = lgLogro1;
        this.lgLogro2 = lgLogro2;
        this.lgLogro3 = lgLogro3;
        this.lgLogro4 = lgLogro4;
        this.lgLogo5 = lgLogro5;
        this.lgLogro6 = lgLogro6;
    }
 
    public Logros(String lgNombre) {
        this.lgNombre = lgNombre;
    }

    public String getLgNombre() {
        return lgNombre;
    }

    public void setLgNombre(String lgNombre) {
        this.lgNombre = lgNombre;
    }

    public String getLgLogro1() {
        return lgLogro1;
    }

    public void setLgLogro1(String lgLogro1) {
        this.lgLogro1 = lgLogro1;
    }

    public String getLgLogro2() {
        return lgLogro2;
    }

    public void setLgLogro2(String lgLogro2) {
        this.lgLogro2 = lgLogro2;
    }

    public String getLgLogro3() {
        return lgLogro3;
    }

    public void setLgLogro3(String lgLogro3) {
        this.lgLogro3 = lgLogro3;
    }

    public String getLgLogro4() {
        return lgLogro4;
    }

    public void setLgLogro4(String lgLogro4) {
        this.lgLogro4 = lgLogro4;
    }

    public String getLgLogo5() {
        return lgLogo5;
    }

    public void setLgLogo5(String lgLogo5) {
        this.lgLogo5 = lgLogo5;
    }

    public String getLgLogro6() {
        return lgLogro6;
    }

    public void setLgLogro6(String lgLogro6) {
        this.lgLogro6 = lgLogro6;
    }

    public Sokoban getSokoban() {
        return sokoban;
    }

    public void setSokoban(Sokoban sokoban) {
        this.sokoban = sokoban;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lgNombre != null ? lgNombre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Logros)) {
            return false;
        }
        Logros other = (Logros) object;
        if ((this.lgNombre == null && other.lgNombre != null) || (this.lgNombre != null && !this.lgNombre.equals(other.lgNombre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sokoban.model.Logros[ lgNombre=" + lgNombre + " ]";
    }
    
}
