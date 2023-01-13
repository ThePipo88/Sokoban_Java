/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban.controller;

import com.jfoenix.controls.JFXButton;
import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import sokoban.Dto.JuegoDto;
import sokoban.Dto.LogrosDto;
import sokoban.clases.Caja;
import sokoban.clases.Mapas;
import sokoban.clases.Personaje;
import sokoban.service.SokobanService;
import sokoban.util.FlowController;
import sokoban.util.Mensajes;
import sokoban.util.Respuesta;

/**
 * FXML Controller class
 *
 * @author Pipo
 */
public class Nivel5ViewController extends Controller implements Initializable {

    //Se crean instancias que se ocuparan a lo largo del proyecto
    
    @FXML
    private AnchorPane anchorNivel5;
    @FXML
    private JFXButton btnC;
    @FXML
    private AnchorPane anchorJuego;
    @FXML
    private GridPane gridMap5;

    Mapas mp = new Mapas();
     
    Personaje p = new Personaje(1,1,0,0);
    
    Caja ca = new Caja(1);
    
    Mensajes sms = new Mensajes();
    
    InicioViewController init = new InicioViewController();
    
    //Datos del proyecto como posicion del jugador, posicion cajas, matrices, Booleanos para validar ganes etc.
    
    private int x = 2;
    private int y = 7;
    
    private int c1 = 10;
    private int c2 = 15;
    private int c3 = 14;
    private int c4 = 14;
    private int c5 = 3;
    private int c6 = 5;
    private int c7 = 2;
    private int c8 = 6;
    private int c9 = 3;
    private int c10 = 4;
    private int c11 = 6;
    private int c12 = 4;
    private int c13 = 3;
    
    private int y1 = 2;
    private int y2 = 2;
    private int y3 = 3;
    private int y4 = 4;
    private int y5 = 7;
    private int y6 = 7;
    private int y7 = 8;
    private int y8 = 8;
    private int y9 = 9;
    private int y10 = 9;
    private int y11 = 10;
    private int y12 = 11;
    private int y13 = 12;
    
    private boolean nivelCompleto = false;
    private boolean primera = true;
    private boolean primerUltimoNivel = false;
    private boolean retroceso = true;
    private static int cajas = 0;
    private static int pasos = 0;
    
    private static Boolean cargarP = true;
    private int xA,xAF;
    private int yA,yAF;
    private boolean regresar;
    private String dificultad;
    private final int Matriz5[][] = new int[16][19];
    private final int AuxP[][] = new int[16][19];
    private final int AuxF[][] = new int[16][19];
    
    //Componentes JFX necesarios
    
    @FXML
    private Text txtNombreJugador;
    @FXML
    private Text txtPasosDados;
    @FXML
    private Text txtPasosPermitidos;
    @FXML
    private Text txtTiempo;
    @FXML
    private JFXButton btnReiniciar;
    @FXML
    private JFXButton btnGuardar;
    @FXML
    private JFXButton btnSalir;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      //Se comprueba si la partida es cargada, si es asi, se pasa la matriz y se llama al metodo posicionesCajas(), el cual
       //se encarga de cargar la informacion desde base de datos y agregarla agregarla al juego
       //En caso contrario si la partida no es cargada se llamaran a los metodos para crear la partida normalmente
       
      Nivel4ViewController nivel4 = new Nivel4ViewController();
      cajas = nivel4.getCajas();
      pasos = nivel4.getPasos();
      if(init.getCargar() == true && nivel4.getCargarP() == true){
       pasarMatriz();
       mover(); 
       configInterfaz();
       posicionesCajas();
       crearJugador();
        }else{
       cargarP = false;
       crearJugador();
       mover(); 
       pasarMatriz();
       configInterfaz();   
       }
       
      //Se guarda la informacion en la matriz de retroceder
      
       xAF = xA;
        yAF = yA;
        
      for(int i = 0; i<= 15; i++){
          for(int j = 0; j<= 18; j++){
          AuxF[i][j] = AuxP[i][j]; 
          }   
      }
      
      for(int i = 0; i<= 15; i++){
          for(int j = 0; j<= 18; j++){
          AuxP[i][j] = Matriz5[i][j]; 
          }   
      }
      
      xA = x;
      yA = y;
    }    

    @Override
    public void initialize() {

    }
    
    //Se crea y agrega al jugador y las cajas en el gridPane
    private void crearJugador(){
    gridMap5.add(ca.getCaja3(), c1, y1);
    gridMap5.add(ca.getCaja3(), c2, y2);
    gridMap5.add(ca.getCaja3(), c3, y3);
    gridMap5.add(ca.getCaja3(), c4, y4);
    gridMap5.add(ca.getCaja3(), c5, y5);
    gridMap5.add(ca.getCaja3(), c6, y6);
    gridMap5.add(ca.getCaja3(), c7, y7);
    gridMap5.add(ca.getCaja3(), c8, y8);
    gridMap5.add(ca.getCaja3(), c9, y9);
    gridMap5.add(ca.getCaja3(), c10, y10);
    gridMap5.add(ca.getCaja3(), c11, y11);
    gridMap5.add(ca.getCaja3(), c12, y12);
    gridMap5.add(ca.getCaja3(), c13, y13);
    gridMap5.add(p.getJugador(), x, y);
    }
    
    //Se pasa la matriz logica de el mapa desde la clase mapas a una matriz del juego
    public void pasarMatriz(){

      for(int i = 0; i<= 15; i++){
          for(int j = 0; j<= 18; j++){
              Matriz5[i][j] = mp.getMapa5(i, j);
          }   
      }

    }
    
    //Se configuran datos como el nombre del jugador, la cantidad de pasos y la cantidad de pasos a dar dependiendo de la dificultad
    private void configInterfaz(){
        SeleccionViewController ct = new SeleccionViewController();
        txtNombreJugador.setText(ct.getNombre());
        
        txtPasosDados.setText("0");
        
        txtTiempo.setText("Mapa 5");
        
        if(ct.getDificultad() == 1){
           txtPasosPermitidos.setText("1200");
           dificultad = "Facil";
        }else if(ct.getDificultad() == 2){
           txtPasosPermitidos.setText("1100"); 
           dificultad = "Medio";
        }else if(ct.getDificultad() == 3){
           txtPasosPermitidos.setText("1000"); 
           dificultad = "Dificil";
        }
        
    }
    
    //Metodo para vaslidar los logros, se comprueba si en el juego se ha logrado un logro, si es asi ingresa a cada uno de los if y hace una consulta en base de datos,
    //esta consulta es para verificar si ya se ha logrado el logro desbloqueado, si retorna un "Logrado" no ingresa al if, si retorna "No lkogrado" ingresa al if y
    //realiza el cambio en base de datos, en este caso un "Logrado" y envia una notificacion en pantalla
    private void validarLogros(){

        LogrosDto logros = new LogrosDto();
        SokobanService service = new SokobanService();        
        Respuesta respuesta = service.getLogro(txtNombreJugador.getText());
        logros =(LogrosDto) respuesta.getResultado("Logro");
        
        if(Long.valueOf(txtPasosDados.getText()) == 20){
            if("No logrado".equals(logros.getLgPrimerLogro())){
                service.actualizarLogros(logros.getLgNombre(),"Logrado", logros.getLgSegundoLogro(), logros.getLgTercerLogro(), logros.getLgCuartoLogro(), logros.getLgQuintoLogro(),
                logros.getLgSextoLogro());
              information("Logro desbloqueado","Felicidades, has desbloqueado el logro 'Dar 20 pasos'");  
            }
           }
        
               
            if(retroceso == true && nivelCompleto == true){

              if("No logrado".equals(logros.getLgCuartoLogro())){
                service.actualizarLogros(logros.getLgNombre(),logros.getLgPrimerLogro(),logros.getLgSegundoLogro(),logros.getLgTercerLogro(), "Logrado", logros.getLgQuintoLogro(),
                logros.getLgSextoLogro());
              information("Logro desbloqueado","Felicidades, has desbloqueado el logro 'Pasar un nivel sin retroceder una jugada'");  
            }  
           }
            
            if(primera == true && nivelCompleto == true){
               
              if("No logrado".equals(logros.getLgSegundoLogro())){
                service.actualizarLogros(logros.getLgNombre(),logros.getLgPrimerLogro(),"Logrado", logros.getLgTercerLogro(), logros.getLgCuartoLogro(), logros.getLgQuintoLogro(),
                logros.getLgSextoLogro());
              information("Logro desbloqueado","Felicidades, has desbloqueado el logro 'Pasar un nivel a la primera'");  
            }  
           }
        
            if(cajas == 30){
               
              if("No logrado".equals(logros.getLgTercerLogro())){
                service.actualizarLogros(logros.getLgNombre(),logros.getLgPrimerLogro(),logros.getLgSegundoLogro(),"Logrado", logros.getLgCuartoLogro(), logros.getLgQuintoLogro(),
                logros.getLgSextoLogro());
              information("Logro desbloqueado","Felicidades, has desbloqueado el logro 'Mover una caja 30 veces'");  
            }  
           }
            
            if(cajas == 150){
               
              if("No logrado".equals(logros.getLgQuintoLogro())){
                service.actualizarLogros(logros.getLgNombre(),logros.getLgPrimerLogro(),logros.getLgSegundoLogro(),logros.getLgTercerLogro(), logros.getLgCuartoLogro(), "Logrado",
                logros.getLgSextoLogro());
              information("Logro desbloqueado","Felicidades, has desbloqueado el logro 'Mover una caja 150 veces'");  
            }  
          }
            
            if(primera == true && nivelCompleto == true){
               
              if("No logrado".equals(logros.getLgSextoLogro())){
                service.actualizarLogros(logros.getLgNombre(),logros.getLgPrimerLogro(),logros.getLgSegundoLogro(),logros.getLgTercerLogro(), logros.getLgCuartoLogro(), logros.getLgQuintoLogro(),
                "Logrado");
              information("Logro desbloqueado","Felicidades, has desbloqueado el logro 'Completar nivel 5 a la primera'");  
            }  
          }
    }
    
    //Metodo para mostrar en pantalla una notificacion, recive como parametro un titulo y un mensaje
    public void information(String titulo, String mensaje){
        Notifications notificationBuilder = Notifications.create()
                .title(titulo)
                .text(mensaje)
                .graphic(null)
                .darkStyle()
                .hideAfter(Duration.seconds(3.0))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.showInformation();
    }
    
    //Metodo para mover el personaje, se controla mediante un EventFilter, primero se comprueba hacia que direccion se quiere mover el personaje
    //una vez se comprueba esto se comprueba si hacia la direccion que se desea mover el jugador hay una caja, si hay una caja se cpmprueba si delante
    //de la caja hay una caja o un muro, si lo hay no se realiza el movimiento, si no hay una caja se comprueba que en esa direccion no haya un muro, 
    //si no lo hay se mueve el jugador, si hay un muro no se mueve, tambien se ira validando los pasos dados, logros o movimientos mal ejecutados.
    public void mover() {
         anchorNivel5.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
             @Override
             public void handle(KeyEvent key) {
                 if(key.getCode()==KeyCode.A){
                     
                     if(Matriz5[y][x-1] == 3){
                         if(Matriz5[y][x-2] != 0 && Matriz5[y][x-2] != 3){
                             removeNodeByRowColumnIndex(y,x-1,gridMap5);
                             removeNodeByRowColumnIndex(y,x,gridMap5);
                             Matriz5[y][x-1] = 1;
                             Matriz5[y][x-2] = 3;
                             moverCaja(3);
                             x--;
                             p.setL(3);
                             gridMap5.add(p.getJugador(), x, y);
                             sumarPasos();
                         }else {
                             removeNodeByRowColumnIndex(y,x,gridMap5);
                             p.setL(3);
                             gridMap5.add(p.getJugador(), x, y);
                         }
                     }else if(Matriz5[y][x-1] != 0){
                         removeNodeByRowColumnIndex(y,x,gridMap5);
                         x--;
                         p.setL(3);
                         gridMap5.add(p.getJugador(), x, y);
                     }else{
                         removeNodeByRowColumnIndex(y,x,gridMap5);   
                         p.setL(3);
                         gridMap5.add(p.getJugador(), x, y);
                     }
                 }
                 if(key.getCode()==KeyCode.D){
                     
                     if(Matriz5[y][x+1] == 3){
                         if(Matriz5[y][x+2] != 0 && Matriz5[y][x+2] != 3){
                             removeNodeByRowColumnIndex(y,x+1,gridMap5);
                             removeNodeByRowColumnIndex(y,x,gridMap5);
                             Matriz5[y][x+1] = 1;
                             Matriz5[y][x+2] = 3;
                             moverCaja(4);
                             x++;
                             p.setL(4);
                             gridMap5.add(p.getJugador(), x, y);
                             sumarPasos();
                         }else {
                             removeNodeByRowColumnIndex(y,x,gridMap5);
                             p.setL(4);
                             gridMap5.add(p.getJugador(), x, y);
                         }
                     }else if(Matriz5[y][x+1] != 0){
                         removeNodeByRowColumnIndex(y,x,gridMap5);
                         x++;
                         p.setL(4);
                         gridMap5.add(p.getJugador(), x, y);
                         sumarPasos();
                     }else{
                         removeNodeByRowColumnIndex(y,x,gridMap5);
                         p.setL(4);
                         gridMap5.add(p.getJugador(), x, y);
                     }   
                 }
                 if(key.getCode()==KeyCode.S){
                     
                     if(Matriz5[y+1][x] == 3){
                         if(Matriz5[y+2][x] != 0 && Matriz5[y+2][x] != 3){
                             removeNodeByRowColumnIndex(y+1,x,gridMap5);
                             removeNodeByRowColumnIndex(y,x,gridMap5);
                             Matriz5[y+1][x] = 1;
                             Matriz5[y+2][x] = 3;
                             moverCaja(1);
                             y++;
                             p.setL(1);
                             gridMap5.add(p.getJugador(), x, y);
                             sumarPasos();
                         }else {
                             removeNodeByRowColumnIndex(y,x,gridMap5);
                             p.setL(1);
                             gridMap5.add(p.getJugador(), x, y);
                         }
                     }else if(Matriz5[y+1][x] != 0){
                         removeNodeByRowColumnIndex(y,x,gridMap5);
                         y++;
                         p.setL(1);
                         gridMap5.add(p.getJugador(), x, y);
                         sumarPasos();
                     }else{
                         removeNodeByRowColumnIndex(y,x,gridMap5);
                         p.setL(1);
                         gridMap5.add(p.getJugador(), x, y);
                     }   
                 }
                 if(key.getCode()==KeyCode.W){
                     
                     if(Matriz5[y-1][x] == 3){
                         if(Matriz5[y-2][x] != 0 && Matriz5[y-2][x] != 3){
                             removeNodeByRowColumnIndex(y-1,x,gridMap5);
                             removeNodeByRowColumnIndex(y,x,gridMap5);
                             Matriz5[y-1][x] = 1;
                             Matriz5[y-2][x] = 3;
                             moverCaja(2);
                             y--;
                             p.setL(2);
                             gridMap5.add(p.getJugador(), x, y);
                             sumarPasos();
                         }else {
                             removeNodeByRowColumnIndex(y,x,gridMap5);
                             p.setL(2);
                             gridMap5.add(p.getJugador(), x, y);
                         }
                     }else if(Matriz5[y-1][x] != 0){
                         removeNodeByRowColumnIndex(y,x,gridMap5);
                         y--;
                         p.setL(2);
                         gridMap5.add(p.getJugador(), x, y);
                         sumarPasos();
                     }else{
                         removeNodeByRowColumnIndex(y,x,gridMap5);
                         p.setL(2);
                         gridMap5.add(p.getJugador(), x, y);
                     }   
                 }
                 pasos++;
                 retroceso();
                 validarLogros();
                 validarGane();
                 validarMovimiento();
             }
         });
          
         }
    
    //Metodo para sumar pasos
    private void sumarPasos(){
      double h = Double.parseDouble(txtPasosDados.getText());
      int numa = (int)h;    
      numa++;
      String numCadena= String.valueOf(numa);
      txtPasosDados.setText(numCadena);
    }
    
    //Metodo para restar pasos
    private void restarPasos(){
      double h = Double.parseDouble(txtPasosDados.getText());
      int numa = (int)h;    
      numa++;
      String numCadena= String.valueOf(numa);
      txtPasosDados.setText(numCadena);
    }
    
    //Metodo para mover una caja, recive como parametro un numero indicando hacia cual direccion se desea mover la caja, una vez identificada la direccion
    //se borraria la caja y se agregaria un campo arriva del jugador en la direccion indicada
    private void moverCaja(int di){
        cajas++;
        switch (di) {
            case 1:
                removeNodeByRowColumnIndex(y+1,x,gridMap5);  
                gridMap5.add(ca.getCaja3(), x, y+2); 
                break;
            case 2:
                removeNodeByRowColumnIndex(y-1,x,gridMap5);  
                gridMap5.add(ca.getCaja3(), x, y-2); 
                break;
            case 3:
                removeNodeByRowColumnIndex(y,x-1,gridMap5);  
                gridMap5.add(ca.getCaja3(), x-2, y); 
                break;
            case 4:
                removeNodeByRowColumnIndex(y,x+1,gridMap5);   
                 gridMap5.add(ca.getCaja3(), x+2, y);    
                break;
            default:
                break;
        }
    }
   
    //Metodo para  remover un jugador de una celda en especifico, recive como parametro una fila, una columna y el GridPane escogido
    //una vez hecho esto se agregaran todos los nodos de el grid pane y se comprobara cual de estos nodos concuerda con la fila y la columna enviada,
    //una vez identificada se borraria del grid pane
    public void removeNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {

        ObservableList<Node> childrens = gridPane.getChildren();
        for (Node node : childrens) {
            if (node instanceof ImageView && gridPane.getRowIndex(node) == row && gridPane.getColumnIndex(node) == column) {
                gridPane.getChildren().remove(node);
                break;
            }
        }

    }
    
    //Metodo para validar si ya se ha completado el mapa, verifica si en la matriz logica las diferentes posiciones ya trienen asigando un tres, si es asi enviara un 
    //mensaje que ya se ha ganado el mapa y se pasara hacia la siguiente ventana, tambien se verificara si ya se alcanzo el limite de pasos establecidos, en este caso
    //se reiniciara la partida
    public void validarGane(){
       double pd = Double.parseDouble(txtPasosDados.getText());
      int pasosD = (int)pd;  
      
      double pl = Double.parseDouble(txtPasosPermitidos.getText());
      int pasosL = (int)pl;  
      
      if(pd < pl){
       if(Matriz5[1][7] == 3 && Matriz5[1][8] == 3 && Matriz5[2][6] == 3 && Matriz5[2][7] == 3 && Matriz5[3][5] == 3 && 
          Matriz5[3][6] == 3 && Matriz5[3][8] == 3 && Matriz5[4][4] == 3 && Matriz5[4][5] == 3 && Matriz5[4][7] == 3 && 
          Matriz5[5][4] == 3 && Matriz5[5][5] == 3 && Matriz5[5][6] == 3){
          nivelCompleto = true; 
          validarLogros();
          sms.showModal(Alert.AlertType.INFORMATION, "Juego Ganado", stage, "Felicidades, has ganado el juego");
          FlowController.getInstance().goViewInNewStage("InicioView", stage);
        }   
      }else{

          sms.showModal(Alert.AlertType.ERROR, "Juego Perdido", stage, "Se ha alcanzado el limite de pasos permitidos");
          reiniciarPartida();
      }
    }

    //Boton de accion para reiniciar la partida
    @FXML
    private void onActionReiniciar(ActionEvent event) {
        primera = false;
        primerUltimoNivel = false;
        reiniciarPartida();
        
    }
    
    //Metodo para reiniciar la partida, elimina todo lo que este en el gridPane, reinicia la matriz, los pasos dados y vuelve a agregar todos los datos en el mapa
    private void reiniciarPartida(){
        
        Mapas mp = new Mapas();
        
        int tamanio = gridMap5.getChildren().size()-1;
        
        for(int i = 0; i<= 15; i++){
          for(int j = 0; j<= 18; j++){
              if(Matriz5[i][j] == 2 || Matriz5[i][j] == 3){
                 removeNodeByRowColumnIndex(i,j,gridMap5); 
              }
          }   
      }
        
        removeNodeByRowColumnIndex(y,x,gridMap5); 

        x = 2;
        
        y = 7;
        
        c1 = 10;
        c2 = 15;
        c3 = 14;
        c4 = 14;
        c5 = 3;
        c6 = 5;
        c7 = 2;
        c8 = 6;
        c9 = 3;
        c10 = 4;
        c11 = 6;
        c12 = 4;
        c13 = 3;
    
        y1 = 2;
        y2 = 2;
        y3 = 3;
        y4 = 4;
        y5 = 7;
        y6 = 7;
        y7 = 8;
        y8 = 8;
        y9 = 9;
        y10 = 9;
        y11 = 10;
        y12 = 11;
        y13 = 12;
        
        txtPasosDados.setText("0");
        
        pasarMatriz();
        
        crearJugador();
  
    }
    
    //Metodo que guarda la informacion cuando se quiere retroceder una jugada
    private void retroceso(){
        
        xAF = xA;
        yAF = yA;
        
      for(int i = 0; i<= 15; i++){
          for(int j = 0; j<= 18; j++){
          AuxF[i][j] = AuxP[i][j]; 
          }   
      }
      
      for(int i = 0; i<= 15; i++){
          for(int j = 0; j<= 18; j++){
          AuxP[i][j] = Matriz5[i][j]; 
          }   
      }
      
      xA = x;
      yA = y;
      
      regresar = true;
    }

    //Accion para regresar un movimiento, elimina todo lo que esta en el Grid Pane y agrega la informacion almacenada en retroceso
    @FXML
    private void onMouseClickedRegresar(MouseEvent event) {
        
        primera = false;
        retroceso = false;
        primerUltimoNivel = false;
        if(regresar == true){
          for(int i = 0; i<= 15; i++){
          for(int j = 0; j<= 18; j++){
              if(Matriz5[i][j] == 2 || Matriz5[i][j] == 3){
                 removeNodeByRowColumnIndex(i,j,gridMap5); 
              }
          }   
      }
        
       for(int i = 0; i<= 15; i++){
          for(int j = 0; j<= 18; j++){
          Matriz5[i][j] = AuxF[i][j]; 
          }   
      }
        
       for(int i = 0; i<= 15; i++){
          for(int j = 0; j<= 18; j++){
              if(Matriz5[i][j] == 3){
                gridMap5.add(ca.getCaja3(), j, i);
              }
          }   
      } 
       
        removeNodeByRowColumnIndex(y,x,gridMap5); 
        x = xAF;
        y = yAF;
        gridMap5.add(p.getJugador(), x, y);
        restarPasos();  
        regresar = false;
        }
    }
    
    //Metodo para verificar si se ha realizado un mal movimiento, analizando si en la matriz logica se ha movido una caja en el lugar no indicado,
    //se enviara un mensaje al jugador indicando que la jugada esta mal hecha
    private void validarMovimiento(){
        
    
     if(Matriz5[1][12] == 3 || Matriz5[1][14] == 3 || Matriz5[1][15] == 3 || Matriz5[2][16] == 3 || Matriz5[3][10] == 3 || Matriz5[3][12] == 3 || Matriz5[4][9] == 3 ||
        Matriz5[4][11] == 3 || Matriz5[4][13] == 3 || Matriz5[4][16] == 3 || Matriz5[5][8] == 3 || Matriz5[6][1] == 3 || Matriz5[6][2] == 3 || Matriz5[6][7] == 3 ||
        Matriz5[6][16] == 3 || Matriz5[7][6] == 3 || Matriz5[7][10] == 3 || Matriz5[7][11] == 3 || Matriz5[8][1] == 3 || Matriz5[8][9] == 3 || Matriz5[8][15] == 3 ||
        Matriz5[9][15] == 3 || Matriz5[10][9] == 3 || Matriz5[11][12] == 3 || Matriz5[11][17] == 3 || Matriz5[11][16] == 3 || Matriz5[11][15] == 3|| Matriz5[11][14] == 3 || 
        Matriz5[12][6] == 3 || Matriz5[12][12] == 3 || Matriz5[12][17] == 3 || Matriz5[10][3] == 3 || Matriz5[11][3] == 3 || Matriz5[13][3] == 3 || Matriz5[13][4] == 3 ||
        Matriz5[13][13] == 3 || Matriz5[13][17] == 3 || Matriz5[14][14] == 3 || Matriz5[14][15] == 3 || Matriz5[14][16] == 3){
        information("Precaución","Has realizado un mal movimiento, se recomiensa retroceder o reiniciar el nivel");          
      }
     
    }

    //Metodo para guardar la informacion en base de datos, mediante el service se enviara toda la informacion del nivel a la tabla Mapa, y por medio 
    //del nombre se podra consultar a base de datos para cargar la partida
    @FXML
    private void onActionGuardar(ActionEvent event) {
        
        String posicionJugador = x+"-"+y;
        String guardarFilas = "";
        String guardarColumnas = "";
        
        for(int i = 0; i<= 15; i++){
           for(int j = 0; j<= 18; j++){
               if(Matriz5[i][j] == 3){
               guardarFilas += i+"-";
               guardarColumnas += j+"-";
              }
           }
        } 
 
         SokobanService service = new SokobanService();
         SeleccionViewController ct = new SeleccionViewController();

         Respuesta respuesta = service.crearJuego(txtNombreJugador.getText(),(long)5,dificultad,true);
         if(!respuesta.getEstado()){
            new Mensajes().showModal(Alert.AlertType.ERROR, "Guardar paciente", stage, respuesta.getMensaje());
           }else{
          //new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar paciente", stage, "Paciente actualizado correctamente.");
          }
         
         SokobanService map = new SokobanService();
         Respuesta rp = map.crearMapa(txtNombreJugador.getText(),Long.valueOf(txtPasosDados.getText()), posicionJugador, guardarFilas, guardarColumnas, true);
         
         if(!rp.getEstado()){
            new Mensajes().showModal(Alert.AlertType.ERROR, "Guardar paciente", stage, respuesta.getMensaje());
           }else{
           new Mensajes().showModal(Alert.AlertType.INFORMATION, "Guardar paciente", stage, "Paciente actualizado correctamente.");
          }
         
    }
    
    //Metodo para cargar la informacion desde base de datos, mediante el nombre del jugadorse cargara toda la infotmacion referente al jugador en el MapaDto,
    //y despues esta se usara para agregar el mapa, las cajas, el jugador,nombre y pasos dados en el juego
    private void posicionesCajas(){

        SokobanService service = new SokobanService();
        Respuesta respuesta = service.getMapa(txtNombreJugador.getText());
        JuegoDto juego = new JuegoDto();
        ArrayList<Integer> lista = new ArrayList();       
        if (respuesta.getEstado()) {
            juego = (JuegoDto) respuesta.getResultado("Partida");
            String cadena = juego.getJgFilasCajas();
            for(int i = 0; i< cadena.length(); i ++)
            {
                
                if(Character.isDigit(cadena.charAt(i))){
                    if(i+1 < cadena.length()){
                    if(Character.isDigit(cadena.charAt(i+1))){
                        char num1 = cadena.charAt(i);
                        char num2 = cadena.charAt(i+1);
                        String n = String.valueOf(num1) + String.valueOf(num2);
                        int numEntero = Integer.parseInt(n);
                        i++;
                        lista.add(numEntero);
                     }else{
                      lista.add(Character.getNumericValue(cadena.charAt(i)));  
                    }
                    }else{
                        lista.add(Character.getNumericValue(cadena.charAt(i)));
                    }
                } 
            } 
            y1 = lista.get(0);
            y2 = lista.get(1);
            y3 = lista.get(2);
            y4 = lista.get(3);
            y5 = lista.get(4);
            y6 = lista.get(5);
            y7 = lista.get(6);
            y8 = lista.get(7);
            y9 = lista.get(8);
            y10 = lista.get(9);
            y11 = lista.get(10);
            y12 = lista.get(11);
            
            lista.clear();
            cadena = juego.getJgColumnasCajas();
            
            for(int i = 0; i< cadena.length(); i ++)
            {
                
                if(Character.isDigit(cadena.charAt(i))){
                    if(i+1 < cadena.length()){
                    if(Character.isDigit(cadena.charAt(i+1))){
                        char num1 = cadena.charAt(i);
                        char num2 = cadena.charAt(i+1);
                        String n = String.valueOf(num1) + String.valueOf(num2);
                        int numEntero = Integer.parseInt(n);
                        i++;
                        lista.add(numEntero);
                     }else{
                      lista.add(Character.getNumericValue(cadena.charAt(i)));  
                    }
                    }else{
                        lista.add(Character.getNumericValue(cadena.charAt(i)));
                    }
                } 
            } 
            
            c1 = lista.get(0);
            c2 = lista.get(1);
            c3 = lista.get(2);
            c4 = lista.get(3);
            c5 = lista.get(4);
            c6 = lista.get(5);
            c7 = lista.get(6);
            c8 = lista.get(7);
            c9 = lista.get(8);
            c10 = lista.get(9);
            c11 = lista.get(10);
            c12 = lista.get(11);
            
            lista.clear();
            cadena = juego.getJgPosicionJugador();
            
            for(int i = 0; i< cadena.length(); i ++)
            {
                
                if(Character.isDigit(cadena.charAt(i))){
                    if(i+1 < cadena.length()){
                    if(Character.isDigit(cadena.charAt(i+1))){
                        char num1 = cadena.charAt(i);
                        char num2 = cadena.charAt(i+1);
                        String n = String.valueOf(num1) + String.valueOf(num2);
                        int numEntero = Integer.parseInt(n);
                        i++;
                        lista.add(numEntero);
                     }else{
                      lista.add(Character.getNumericValue(cadena.charAt(i)));  
                    }
                    }else{
                        lista.add(Character.getNumericValue(cadena.charAt(i)));
                    }
                } 
            }
            
            x = lista.get(0);
            y = lista.get(1);
            
            borrarCaja();
           
            Matriz5[y1][c1] = 3;
            Matriz5[y2][c2] = 3;
            Matriz5[y3][c3] = 3;
            Matriz5[y4][c4] = 3;
            Matriz5[y5][c5] = 3;
            Matriz5[y6][c6] = 3;
            Matriz5[y7][c7] = 3;
            Matriz5[y8][c8] = 3;
            Matriz5[y9][c9] = 3;
            Matriz5[y10][c10] = 3;
            Matriz5[y11][c11] = 3;
            Matriz5[y12][c12] = 3;
            
            txtPasosDados.setText(juego.getJgPasos());
        } else {
            new Mensajes().showModal(Alert.AlertType.ERROR, "Cargar paciente", getStage(), respuesta.getMensaje());
        }  
    }
    
    //Metodo para borrar las cajas de la matriz logica
    private void borrarCaja(){
     for(int i = 0; i<= 15; i++){
          for(int j = 0; j<= 18; j++){
              if(Matriz5[i][j] == 3){
                 Matriz5[i][j] = 1;
              }
          }   
      }   
    }

    //Metodo para salir de la partida y volver a menu
    @FXML
    private void onActionSalir(ActionEvent event) {
        FlowController.getInstance().goViewInNewStage("InicioView", stage);
    }
}
