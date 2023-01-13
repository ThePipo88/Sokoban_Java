/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban.model;

import java.io.Serializable;
import java.math.BigInteger;
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
import sokoban.Dto.MenuDto;

/**
 *
 * @author Pipo
 */
@Entity
@Table(name = "MAPA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mapa.findAll", query = "SELECT m FROM Mapa m")
    , @NamedQuery(name = "Mapa.findByMpNombre", query = "SELECT m FROM Mapa m WHERE m.mpNombre = :nombre")
    , @NamedQuery(name = "Mapa.findByMpPasos", query = "SELECT m FROM Mapa m WHERE m.mpPasos = :mpPasos")
    , @NamedQuery(name = "Mapa.findByMpPosicionjugador", query = "SELECT m FROM Mapa m WHERE m.mpPosicionjugador = :mpPosicionjugador")
    , @NamedQuery(name = "Mapa.findByMpFilascajas", query = "SELECT m FROM Mapa m WHERE m.mpFilascajas = :mpFilascajas")
    , @NamedQuery(name = "Mapa.findByMpColumnascajas", query = "SELECT m FROM Mapa m WHERE m.mpColumnascajas = :mpColumnascajas")})
public class Mapa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "MP_NOMBRE")
    private String mpNombre;
    @Column(name = "MP_PASOS")
    private Long mpPasos;
    @Column(name = "MP_POSICIONJUGADOR")
    private String mpPosicionjugador;
    @Column(name = "MP_FILASCAJAS")
    private String mpFilascajas;
    @Column(name = "MP_COLUMNASCAJAS")
    private String mpColumnascajas;
    @JoinColumn(name = "MP_NOMBRE", referencedColumnName = "SK_NOMBRE", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Sokoban sokoban;

    public Mapa() {
    }
    
    public Mapa(String mpNombre, Long mpPasos, String mpPosicionJugador, String mpFilascajas, String mpColumnascajas) {       
        this.mpNombre = mpNombre;
        this.mpPasos = mpPasos;
        this.mpPosicionjugador = mpPosicionJugador;
        this.mpFilascajas = mpFilascajas;
        this.mpColumnascajas = mpColumnascajas;
    }
    
    public Mapa(JuegoDto juego) {
        this.mpNombre = juego.getJgNombre();
        //actualizarPaciente(juego);
    }
    
    public void actualizarMapa(String nombre,Long pasos,String posicionJugador, String filas, String columnas){
        this.mpNombre = nombre;
        this.mpPasos = pasos;
        this.mpPosicionjugador = posicionJugador;
        this.mpFilascajas = filas;
        this.mpColumnascajas = columnas;
    }

    public Mapa(String mpNombre) {
        this.mpNombre = mpNombre;
    }

    public String getMpNombre() {
        return mpNombre;
    }

    public void setMpNombre(String mpNombre) {
        this.mpNombre = mpNombre;
    }

    public Long getMpPasos() {
        return mpPasos;
    }

    public void setMpPasos(Long mpPasos) {
        this.mpPasos = mpPasos;
    }

    public String getMpPosicionjugador() {
        return mpPosicionjugador;
    }

    public void setMpPosicionjugador(String mpPosicionjugador) {
        this.mpPosicionjugador = mpPosicionjugador;
    }

    public String getMpFilascajas() {
        return mpFilascajas;
    }

    public void setMpFilascajas(String mpFilascajas) {
        this.mpFilascajas = mpFilascajas;
    }

    public String getMpColumnascajas() {
        return mpColumnascajas;
    }

    public void setMpColumnascajas(String mpColumnascajas) {
        this.mpColumnascajas = mpColumnascajas;
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
        hash += (mpNombre != null ? mpNombre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mapa)) {
            return false;
        }
        Mapa other = (Mapa) object;
        if ((this.mpNombre == null && other.mpNombre != null) || (this.mpNombre != null && !this.mpNombre.equals(other.mpNombre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sokoban.model.Mapa[ mpNombre=" + mpNombre + " ]";
    }
    
}
