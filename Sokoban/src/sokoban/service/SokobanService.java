/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban.service;

import com.jfoenix.controls.JFXTextField;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import sokoban.Dto.JuegoDto;
import sokoban.Dto.LogrosDto;
import sokoban.Dto.MenuDto;
import sokoban.model.Logros;
import sokoban.model.Mapa;
import sokoban.model.Sokoban;
import sokoban.util.EntityManagerHelper;
import sokoban.util.Respuesta;

/**
 *
 * @author Pipo
 */
public class SokobanService {
    
    EntityManager em = EntityManagerHelper.getInstance().getManager();
    private EntityTransaction et;
    
    //Metodo para crear un jugador
    public Respuesta crearJuego(String nombre, Long mapa, String dificultad,Boolean actualizar) {
        try {
            et = em.getTransaction();
            et.begin();
            Sokoban sokoban;
            if(actualizar == true){
             sokoban = em.find(Sokoban.class, nombre);
                
                if(sokoban == null){
                    et.rollback();
                    return new Respuesta(false, "No se encotró el jugador a modificar","guardarJuego NoResultException");
                }
                sokoban.actualizarMapa(nombre,mapa,dificultad);
                sokoban = em.merge(sokoban);  
            }else{
            sokoban = new Sokoban(nombre,mapa,dificultad); 
             em.persist(sokoban);   
            }
            
            et.commit();
            return new Respuesta(true, "", "", "Juego","Juego creado");
        } catch (NoResultException ex) {
            return new Respuesta(false, "No existe un juego con el codigo ingresado", "getJuego NoResultException" );
        } catch (NonUniqueResultException ex) {
            Logger.getLogger(SokobanService.class.getName()).log(Level.SEVERE, "A ocurrido un error consultando el juego.", ex);
            return new Respuesta(false, "Error guardando el juego.", "getJuego NonUniqueResultException");
        } catch (Exception ex) {
            Logger.getLogger(SokobanService.class.getName()).log(Level.SEVERE, "Error guardando el juego.", ex);
            return new Respuesta(false, "Error guardando el juego.", "guardarJuego " + ex.getMessage());
        }
    }
    
    //Metodo para crear un mapa referente al jugador
    public Respuesta crearMapa(String nombre, Long pasos, String posicion, String filas, String columnas,Boolean actualizar) {
        try {
            et = em.getTransaction();
            et.begin();
            Mapa mapa;  
            if(actualizar == true){
             mapa = em.find(Mapa.class, nombre); 
                if(mapa == null){
                    et.rollback();
                    return new Respuesta(false, "No se encotró la partida a modificar","guardarJuego NoResultException");
                }
                mapa.actualizarMapa(nombre,pasos,posicion,filas,columnas);
                mapa = em.merge(mapa);  
            }else{
                mapa = new Mapa(nombre,pasos,posicion,filas,columnas);
                em.persist(mapa);
            }
            et.commit();
            return new Respuesta(true, "", "", "Mapa","Mapa creado");
        } catch (NoResultException ex) {
            return new Respuesta(false, "No existe un mapa con el codigo ingresado", "getMapa NoResultException" );
        } catch (NonUniqueResultException ex) {
            Logger.getLogger(SokobanService.class.getName()).log(Level.SEVERE, "A ocurrido un error consultando el paciente.", ex);
            return new Respuesta(false, "Error guardando el mapa.", "getMapa NonUniqueResultException");
        } catch (Exception ex) {
            Logger.getLogger(SokobanService.class.getName()).log(Level.SEVERE, "Error guardando el mapa.", ex);
            return new Respuesta(false, "Error guardando el mapa.", "guardarMapa " + ex.getMessage());
        }
    }
    
    public Respuesta crearLogros(String nombre, String primerL, String segundoL, String tercerL, String cuartoL,String quintoL, String sextoL, Boolean actualizar) {
        try {
            et = em.getTransaction();
            et.begin();
            Logros logro; 
            if(actualizar == true){
             logro = em.find(Logros.class, nombre); 
                if(logro == null){
                    et.rollback();
                    return new Respuesta(false, "No se encotró los logros a modificar","guardarLogros NoResultException");
                }
                logro.actualizarLogros(nombre,primerL,segundoL,tercerL,cuartoL,quintoL,sextoL);
                logro = em.merge(logro);  
            }else{
                logro = new Logros(nombre,primerL,segundoL,tercerL,cuartoL,quintoL,sextoL);
                em.persist(logro);
            }
            et.commit();
            return new Respuesta(true, "", "", "Logros","Logros creados");
        } catch (NoResultException ex) {
            return new Respuesta(false, "No existe un logro con el codigo ingresado", "getLogro NoResultException" );
        } catch (NonUniqueResultException ex) {
            Logger.getLogger(SokobanService.class.getName()).log(Level.SEVERE, "A ocurrido un error consultando el logro.", ex);
            return new Respuesta(false, "Error guardando el logro.", "getLogro NonUniqueResultException");
        } catch (Exception ex) {
            Logger.getLogger(SokobanService.class.getName()).log(Level.SEVERE, "Error guardando el logro.", ex);
            return new Respuesta(false, "Error guardando el logro.", "guardarLogro " + ex.getMessage());
        }
    } 
    
    public Respuesta getPartida(String nombre) {
        
        try {
            
            Query qryPartida = em.createNamedQuery("Sokoban.findBySkNombre", Sokoban.class);
            qryPartida.setParameter("nombre", nombre);
            return new Respuesta(true, "", "", "Partida", new MenuDto((Sokoban)qryPartida.getSingleResult()));
        } catch (NoResultException ex) {
            return new Respuesta(false, "No existe una partida con el nombre ingresado", "getPartida NoResultException" );
        } catch (NonUniqueResultException ex) {
            Logger.getLogger(SokobanService.class.getName()).log(Level.SEVERE, "A ocurrido un error consultando la partida.", ex);
            return new Respuesta(false, "A ocurrido un error consultando la partida.", "getPartida NonUniqueResultException");
        } catch (Exception ex) {
            Logger.getLogger(SokobanService.class.getName()).log(Level.SEVERE, "Error obteniendo la partida [" + nombre + "]", ex);
            return new Respuesta(false, "Error obteniendo la partida.", "getPartida " + ex.getMessage());
        }
    }
    
    public Respuesta getLogro(String nombre) {
        
        try {
            
            Query qryLogro = em.createNamedQuery("Logros.findByLgNombre", Logros.class);
            qryLogro.setParameter("nombre", nombre);
            return new Respuesta(true, "", "", "Logro", new LogrosDto((Logros)qryLogro.getSingleResult()));
        } catch (NoResultException ex) {
            return new Respuesta(false, "No existe un logro con el nombre ingresado", "getLogro NoResultException" );
        } catch (NonUniqueResultException ex) {
            Logger.getLogger(SokobanService.class.getName()).log(Level.SEVERE, "A ocurrido un error consultando el logro.", ex);
            return new Respuesta(false, "A ocurrido un error consultando el logro.", "getLogro NonUniqueResultException");
        } catch (Exception ex) {
            Logger.getLogger(SokobanService.class.getName()).log(Level.SEVERE, "Error obteniendo el logro [" + nombre + "]", ex);
            return new Respuesta(false, "Error obteniendo el logro.", "getLogro " + ex.getMessage());
        }
    }
    
    public Respuesta getMapa(String nombre) {
        
        try {
            
            Query qryMapa = em.createNamedQuery("Mapa.findByMpNombre", Mapa.class);
            qryMapa.setParameter("nombre", nombre);
            return new Respuesta(true, "", "", "Partida", new JuegoDto((Mapa)qryMapa.getSingleResult()));
        } catch (NoResultException ex) {
            return new Respuesta(false, "No existe un mapa con el nombre ingresado", "getMapa NoResultException" );
        } catch (NonUniqueResultException ex) {
            Logger.getLogger(SokobanService.class.getName()).log(Level.SEVERE, "A ocurrido un error consultando el mapa.", ex);
            return new Respuesta(false, "A ocurrido un error consultando el mapa.", "getMapa NonUniqueResultException");
        } catch (Exception ex) {
            Logger.getLogger(SokobanService.class.getName()).log(Level.SEVERE, "Error obteniendo el mapa [" + nombre + "]", ex);
            return new Respuesta(false, "Error obteniendo el mapa.", "getMapa " + ex.getMessage());
        }
    }
    
    public Respuesta getLogros() {
        
        try {
            Query qryLogro = em.createNamedQuery("Logros.findAll", Logros.class);
            List<Logros> logros = qryLogro.getResultList();
            List<LogrosDto> logrosDto = new ArrayList<>();
            logros.forEach((logro)-> {
                logrosDto.add(new LogrosDto(logro));
            });
            
            return new Respuesta(true, "", "", "Logro", logrosDto);
        } catch (NoResultException ex) {
            return new Respuesta(false, "No enxisten logros con los criterios ingresados","getLogros NoResultException" );
        } catch (Exception ex) {
            Logger.getLogger(SokobanService.class.getName()).log(Level.SEVERE, "Error obteniendo logros.", ex);
            return new Respuesta(false, "Error obteniendo logros.", "getLogros " + ex.getMessage());
        }
    }
    
    public Respuesta actualizarLogros(String nombre,String logro1,String logro2, String logro3, String logro4,String logro5,String logro6) {
        
        try {
            et = em.getTransaction();
            et.begin();
            Logros logros;
            
            logros = em.find(Logros.class, nombre);
            
            if(nombre == null){
                    et.rollback();
                    return new Respuesta(false, "No se encotró el paciente a modificar","guardarPaciente NoResultException");
                }
                logros.actualizarLogros(nombre,logro1,logro2,logro3,logro4,logro5,logro6);
                logros = em.merge(logros);
                et.commit();
            return new Respuesta(true, "", "", "Logro", new LogrosDto(logros));
        } catch (NoResultException ex) {
            return new Respuesta(false, "No enxisten logros con los criterios ingresados","getLogros NoResultException" );
        } catch (Exception ex) {
            Logger.getLogger(SokobanService.class.getName()).log(Level.SEVERE, "Error obteniendo logros.", ex);
            return new Respuesta(false, "Error obteniendo logros.", "getLogros " + ex.getMessage());
        }
    }
    
    public Respuesta getPartidas() {
        try {
            Query qryPartida = em.createNamedQuery("Sokoban.findAll", Sokoban.class);
            List<Sokoban> jugadores = qryPartida.getResultList();
            List<MenuDto> menuDto = new ArrayList<>();
            jugadores.forEach((menu)-> {
                menuDto.add(new MenuDto(menu));
            });
            
            return new Respuesta(true, "", "", "Menu", menuDto);
        } catch (NoResultException ex) {
            return new Respuesta(false, "No enxisten partidas con los criterios ingresados","getPartidas NoResultException" );
        } catch (Exception ex) {
            Logger.getLogger(SokobanService.class.getName()).log(Level.SEVERE, "Error obteniendo la partida", ex);
            return new Respuesta(false, "Error obteniendo la partida", "getPartidas " + ex.getMessage());
        }
        
    }
    
}
