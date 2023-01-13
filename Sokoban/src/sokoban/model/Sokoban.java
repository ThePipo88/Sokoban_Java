/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban.model;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import sokoban.Dto.MenuDto;

/**
 *
 * @author Pipo
 */
@Entity
@Table(name = "SOKOBAN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sokoban.findAll", query = "SELECT s FROM Sokoban s")
    , @NamedQuery(name = "Sokoban.findBySkNombre", query = "SELECT s FROM Sokoban s WHERE s.skNombre = :nombre")
    , @NamedQuery(name = "Sokoban.findBySkMapa", query = "SELECT s FROM Sokoban s WHERE s.skMapa = :skMapa")
    , @NamedQuery(name = "Sokoban.findBySkDificultad", query = "SELECT s FROM Sokoban s WHERE s.skDificultad = :skDificultad")})
public class Sokoban implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "SK_NOMBRE")
    private String skNombre;
    @Column(name = "SK_MAPA")
    private Long skMapa;
    @Column(name = "SK_DIFICULTAD")
    private String skDificultad;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "sokoban")
    private Mapa mapa;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "sokoban")
    private Logros logros;

    public Sokoban() {
    }
    
    public Sokoban(String skNombre, Long skMapa, String skDificultad) {
        
        this.skNombre = skNombre;
        this.skMapa = skMapa;
        this.skDificultad = skDificultad;
    }
    
    public Sokoban(MenuDto menu) {
        this.skNombre = menu.getMnNombre();
        //actualizarPaciente(menu);
    }
    
    public void actualizarMapa(String nombre,Long mapa,String dificultad){
       this.skNombre = nombre;
        this.skMapa = mapa;
        this.skDificultad = dificultad;
    }

    public Sokoban(String skNombre) {
        this.skNombre = skNombre;
    }

    public String getSkNombre() {
        return skNombre;
    }

    public void setSkNombre(String skNombre) {
        this.skNombre = skNombre;
    }

    public Long getSkMapa() {
        return skMapa;
    }

    public void setSkMapa(Long skMapa) {
        this.skMapa = skMapa;
    }

    public String getSkDificultad() {
        return skDificultad;
    }

    public void setSkDificultad(String skDificultad) {
        this.skDificultad = skDificultad;
    }

    public Mapa getMapa() {
        return mapa;
    }

    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }

    public Logros getLogros() {
        return logros;
    }

    public void setLogros(Logros logros) {
        this.logros = logros;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (skNombre != null ? skNombre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sokoban)) {
            return false;
        }
        Sokoban other = (Sokoban) object;
        if ((this.skNombre == null && other.skNombre != null) || (this.skNombre != null && !this.skNombre.equals(other.skNombre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sokoban.model.Sokoban[ skNombre=" + skNombre + " ]";
    }
    
}
