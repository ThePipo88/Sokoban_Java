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
public class Nivel4ViewController extends Controller implements Initializable {
    
    // Se crean instancias que se ocuparan a lo largo del proyecto
    @FXML
    private JFXButton btnC;
    @FXML
    private AnchorPane anchorJuego;
    @FXML
    private AnchorPane anchorNivel4;
    @FXML
    private GridPane gridMap4;
    
    Mapas mp = new Mapas();
     
    Personaje p = new Personaje(1,1,0,0);
    
    Caja ca = new Caja(1);
    
    Mensajes sms = new Mensajes();
    
    InicioViewController init = new InicioViewController();
    
    //Datos del proyecto como posicion del jugador, posicion cajas, matrices, Booleanos para validar ganes etc.
    
    private int x = 8;
    private int y = 2;
    
    private int c1 = 10;
    private int c2 = 9;
    private int c3 = 4;
    private int c4 = 4;
    private int c5 = 7;
    private int c6 = 14;
    private int c7 = 14;
    private int c8 = 9;
    private int c9 = 3;
    private int c10 = 7;
    private int c11 = 10;
    
    private boolean nivelCompleto = false;
    private boolean primera = true;
    private boolean retroceso = true;
    private static int cajas = 0;
    private static int pasos = 0;
    
    private int y1 = 2;
    private int y2 = 3;
    private int y3 = 5;
    private int y4 = 6;
    private int y5 = 5;
    private int y6 = 4;
    private int y7 = 8;
    private int y8 = 9;
    private int y9 = 10;
    private int y10 = 10;
    private int y11 = 10;
    private static Boolean cargarP = true;
    private int xA,xAF;
    private int yA,yAF;
    private boolean regresar;
    private String dificultad;
    private final int Matriz4[][] = new int[16][19];
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
       
      Nivel3ViewController nivel3 = new Nivel3ViewController();
      pasos = nivel3.getPasos();
      cajas = nivel3.getCajas();
      if(init.getCargar() == true && nivel3.getCargarP() == true){
       cargarP = false;
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
          AuxP[i][j] = Matriz4[i][j]; 
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
    gridMap4.add(ca.getCaja3(), c1, y1);
    gridMap4.add(ca.getCaja3(),c2, y2);
    gridMap4.add(ca.getCaja3(), c3, y3);
    gridMap4.add(ca.getCaja3(), c4, y4);
    gridMap4.add(ca.getCaja3(), c5, y5);
    gridMap4.add(ca.getCaja3(), c6, y6);
    gridMap4.add(ca.getCaja3(), c7, y7);
    gridMap4.add(ca.getCaja3(), c8, y8);
    gridMap4.add(ca.getCaja3(), c9, y9);
    gridMap4.add(ca.getCaja3(), c10, y10);
    gridMap4.add(ca.getCaja3(), c11, y11);
    gridMap4.add(p.getJugador(), x, y);
    }
    
    //Se pasa la matriz logica de el mapa desde la clase mapas a una matriz del juego
    public void pasarMatriz(){

      for(int i = 0; i<= 15; i++){
          for(int j = 0; j<= 18; j++){
              Matriz4[i][j] = mp.getMapa4(i, j);
          }   
      }

    }
    
    //Se configuran datos como el nombre del jugador, la cantidad de pasos y la cantidad de pasos a dar dependiendo de la dificultad
    private void configInterfaz(){
        SeleccionViewController ct = new SeleccionViewController();
        txtNombreJugador.setText(ct.getNombre());
        
        txtPasosDados.setText("0");
        
        txtTiempo.setText("Mapa 4");
        
        if(ct.getDificultad() == 1){
           txtPasosPermitidos.setText("1200");
           dificultad = "Facil";
        }else if(ct.getDificultad() == 2){
           txtPasosPermitidos.setText("1100"); 
           dificultad = "Media";
           
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
    }
    
    //Metodo para mover el personaje, se controla mediante un EventFilter, primero se comprueba hacia que direccion se quiere mover el personaje
    //una vez se comprueba esto se comprueba si hacia la direccion que se desea mover el jugador hay una caja, si hay una caja se cpmprueba si delante
    //de la caja hay una caja o un muro, si lo hay no se realiza el movimiento, si no hay una caja se comprueba que en esa direccion no haya un muro, 
    //si no lo hay se mueve el jugador, si hay un muro no se mueve, tambien se ira validando los pasos dados, logros o movimientos mal ejecutados.
    public void mover() {
         anchorNivel4.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
             @Override
             public void handle(KeyEvent key) {
                 if(key.getCode()==KeyCode.A){
                     
                     if(Matriz4[y][x-1] == 3){
                         if(Matriz4[y][x-2] != 0 && Matriz4[y][x-2] != 3){
                             removeNodeByRowColumnIndex(y,x-1,gridMap4);
                             removeNodeByRowColumnIndex(y,x,gridMap4);
                             Matriz4[y][x-1] = 1;
                             Matriz4[y][x-2] = 3;
                             moverCaja(3);
                             x--;
                             p.setL(3);
                             gridMap4.add(p.getJugador(), x, y);
                             sumarPasos();
                         }else {
                             removeNodeByRowColumnIndex(y,x,gridMap4);
                             p.setL(3);
                             gridMap4.add(p.getJugador(), x, y);
                         }
                     }else if(Matriz4[y][x-1] != 0){
                         removeNodeByRowColumnIndex(y,x,gridMap4);
                         x--;
                         p.setL(3);
                         gridMap4.add(p.getJugador(), x, y);
                         sumarPasos();
                     }else{
                         removeNodeByRowColumnIndex(y,x,gridMap4);   
                         p.setL(3);
                         gridMap4.add(p.getJugador(), x, y);
                     }
                 }
                 if(key.getCode()==KeyCode.D){
                     
                     if(Matriz4[y][x+1] == 3){
                         if(Matriz4[y][x+2] != 0 && Matriz4[y][x+2] != 3){
                             removeNodeByRowColumnIndex(y,x+1,gridMap4);
                             removeNodeByRowColumnIndex(y,x,gridMap4);
                             Matriz4[y][x+1] = 1;
                             Matriz4[y][x+2] = 3;
                             moverCaja(4);
                             x++;
                             p.setL(4);
                             gridMap4.add(p.getJugador(), x, y);
                             sumarPasos();
                         }else {
                             removeNodeByRowColumnIndex(y,x,gridMap4);
                             p.setL(4);
                             gridMap4.add(p.getJugador(), x, y);
                         }
                     }else if(Matriz4[y][x+1] != 0){
                         removeNodeByRowColumnIndex(y,x,gridMap4);
                         x++;
                         p.setL(4);
                         gridMap4.add(p.getJugador(), x, y);
                         sumarPasos();
                     }else{
                         removeNodeByRowColumnIndex(y,x,gridMap4);
                         p.setL(4);
                         gridMap4.add(p.getJugador(), x, y);
                     }   
                 }
                 if(key.getCode()==KeyCode.S){
                     
                     if(Matriz4[y+1][x] == 3){
                         if(Matriz4[y+2][x] != 0 && Matriz4[y+2][x] != 3){
                             removeNodeByRowColumnIndex(y+1,x,gridMap4);
                             removeNodeByRowColumnIndex(y,x,gridMap4);
                             Matriz4[y+1][x] = 1;
                             Matriz4[y+2][x] = 3;
                             moverCaja(1);
                             y++;
                             p.setL(1);
                             gridMap4.add(p.getJugador(), x, y);
                             sumarPasos();
                         }else {
                             removeNodeByRowColumnIndex(y,x,gridMap4);
                             p.setL(1);
                             gridMap4.add(p.getJugador(), x, y);
                         }
                     }else if(Matriz4[y+1][x] != 0){
                         removeNodeByRowColumnIndex(y,x,gridMap4);
                         y++;
                         p.setL(1);
                         gridMap4.add(p.getJugador(), x, y);
                         sumarPasos();
                     }else{
                         removeNodeByRowColumnIndex(y,x,gridMap4);
                         p.setL(1);
                         gridMap4.add(p.getJugador(), x, y);
                     }   
                 }
                 if(key.getCode()==KeyCode.W){
                     
                     if(Matriz4[y-1][x] == 3){
                         if(Matriz4[y-2][x] != 0 && Matriz4[y-2][x] != 3){
                             removeNodeByRowColumnIndex(y-1,x,gridMap4);
                             removeNodeByRowColumnIndex(y,x,gridMap4);
                             Matriz4[y-1][x] = 1;
                             Matriz4[y-2][x] = 3;
                             moverCaja(2);
                             y--;
                             p.setL(2);
                             gridMap4.add(p.getJugador(), x, y);
                             sumarPasos();
                         }else {
                             removeNodeByRowColumnIndex(y,x,gridMap4);
                             p.setL(2);
                             gridMap4.add(p.getJugador(), x, y);
                         }
                     }else if(Matriz4[y-1][x] != 0){
                         removeNodeByRowColumnIndex(y,x,gridMap4);
                         y--;
                         p.setL(2);
                         gridMap4.add(p.getJugador(), x, y);
                         sumarPasos();
                     }else{
                         removeNodeByRowColumnIndex(y,x,gridMap4);
                         p.setL(2);
                         gridMap4.add(p.getJugador(), x, y);
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
      numa--;
      String numCadena= String.valueOf(numa);
      txtPasosDados.setText(numCadena);
    }
    
    //Metodo para mover una caja, recive como parametro un numero indicando hacia cual direccion se desea mover la caja, una vez identificada la direccion
    //se borraria la caja y se agregaria un campo arriva del jugador en la direccion indicada
    private void moverCaja(int di){
        cajas++;
        switch (di) {
            case 1:
                removeNodeByRowColumnIndex(y+1,x,gridMap4);  
                gridMap4.add(ca.getCaja3(), x, y+2); 
                break;
            case 2:
                removeNodeByRowColumnIndex(y-1,x,gridMap4);  
                gridMap4.add(ca.getCaja3(), x, y-2); 
                break;
            case 3:
                removeNodeByRowColumnIndex(y,x-1,gridMap4);  
                gridMap4.add(ca.getCaja3(), x-2, y); 
                break;
            case 4:
                removeNodeByRowColumnIndex(y,x+1,gridMap4);   
                 gridMap4.add(ca.getCaja3(), x+2, y);    
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
        if(Matriz4[8][17] == 3 && Matriz4[9][17] == 3 && Matriz4[10][16] == 3 && Matriz4[10][17] == 3 && Matriz4[11][17] == 3 && Matriz4[12][16] == 3 &&
          Matriz4[12][17] == 3 && Matriz4[13][14] == 3 && Matriz4[13][15] == 3 && Matriz4[13][16] == 3 && Matriz4[13][17] == 3){
          nivelCompleto = true; 
          validarLogros();
          sms.showModal(Alert.AlertType.INFORMATION, "Mapa Ganado", stage, "El nivel se ha completado con exito, precione aceptar para continiar");
          FlowController.getInstance().goViewInNewStage("Nivel5View", stage);
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
       reiniciarPartida(); 
    }
    
    //Metodo para reiniciar la partida, elimina todo lo que este en el gridPane, reinicia la matriz, los pasos dados y vuelve a agregar todos los datos en el mapa
    private void reiniciarPartida(){
        
        Mapas mp = new Mapas();
        
        int tamanio = gridMap4.getChildren().size()-1;
        
        for(int i = 0; i<= 15; i++){
          for(int j = 0; j<= 18; j++){
              if(Matriz4[i][j] == 3){
                 removeNodeByRowColumnIndex(i,j,gridMap4); 
              }
          }   
      }
        
        removeNodeByRowColumnIndex(y,x,gridMap4); 

        x = 8;
        
        y = 2;
        
        c1 = 10;
        c2 = 9;
        c3 = 4;
        c4 = 4;
        c5 = 7;
        c6 = 14;
        c7 = 14;
        c8 = 9;
        c9 = 3;
        c10 = 7;
        c11 = 10;
    
        y1 = 2;
        y2 = 3;
        y3 = 5;
        y4 = 6;
        y5 = 5;
        y6 = 4;
        y7 = 8;
        y8 = 9;
        y9 = 10;
        y10 = 10;
        y11 = 10;
    
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
          AuxP[i][j] = Matriz4[i][j]; 
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
        if(regresar == true){
          for(int i = 0; i<= 15; i++){
          for(int j = 0; j<= 18; j++){
              if(Matriz4[i][j] == 2 || Matriz4[i][j] == 3){
                 removeNodeByRowColumnIndex(i,j,gridMap4); 
              }
          }   
      }
        
       for(int i = 0; i<= 15; i++){
          for(int j = 0; j<= 18; j++){
          Matriz4[i][j] = AuxF[i][j]; 
          }   
      }
        
       for(int i = 0; i<= 15; i++){
          for(int j = 0; j<= 18; j++){
              if(Matriz4[i][j] == 3){
                gridMap4.add(ca.getCaja3(), j, i);
              }
          }   
      } 
       
        removeNodeByRowColumnIndex(y,x,gridMap4); 
        x = xAF;
        y = yAF;
        gridMap4.add(p.getJugador(), x, y);
        restarPasos();  
        regresar = false;
        }
    }
    
    //Metodo para verificar si se ha realizado un mal movimiento, analizando si en la matriz logica se ha movido una caja en el lugar no indicado,
    //se enviara un mensaje al jugador indicando que la jugada esta mal hecha
    private void validarMovimiento(){
        
    
     if(Matriz4[1][5] == 3 || Matriz4[1][6] == 3 || Matriz4[1][7] == 3 || Matriz4[1][8] == 3 || Matriz4[1][9] == 3 || Matriz4[1][10] == 3 || Matriz4[1][11] == 3 ||
        Matriz4[1][12] == 3 || Matriz4[2][7] == 3 || Matriz4[2][12] == 3 || Matriz4[2][14] == 3 || Matriz4[2][15] == 3 || Matriz4[4][3] == 3 || Matriz4[4][4] == 3 ||
        Matriz4[4][11] == 3 || Matriz4[4][12] == 3 || Matriz4[4][17] == 3 || Matriz4[4][5] == 3 || Matriz4[4][6] == 3 || Matriz4[5][3] == 3 || Matriz4[6][3] == 3 ||
        Matriz4[6][6] == 3 || Matriz4[7][3] == 3 || Matriz4[7][4] == 3 || Matriz4[8][5] == 3 || Matriz4[8][6] == 3 || Matriz4[8][9] == 3|| Matriz4[8][11] == 3 || 
        Matriz4[8][12] == 3 || Matriz4[9][3] == 3 || Matriz4[10][12] == 3 || Matriz4[11][6] == 3 || Matriz4[11][8] == 3 || Matriz4[11][12] == 3 ||
        Matriz4[12][12] == 3 || Matriz4[13][10] == 3 || Matriz4[14][4] == 3 || Matriz4[14][4] == 3 || Matriz4[14][5] == 3 || Matriz4[14][9] == 3){
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
               if(Matriz4[i][j] == 3){
               guardarFilas += i+"-";
               guardarColumnas += j+"-";
              }
           }
        } 
 
         SokobanService service = new SokobanService();
         SeleccionViewController ct = new SeleccionViewController();

         Respuesta respuesta = service.crearJuego(txtNombreJugador.getText(),(long)4,dificultad,true);
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
           
            Matriz4[y1][c1] = 3;
            Matriz4[y2][c2] = 3;
            Matriz4[y3][c3] = 3;
            Matriz4[y4][c4] = 3;
            Matriz4[y5][c5] = 3;
            Matriz4[y6][c6] = 3;
            Matriz4[y7][c7] = 3;
            Matriz4[y8][c8] = 3;
            Matriz4[y9][c9] = 3;
            Matriz4[y10][c10] = 3;
            Matriz4[y11][c11] = 3;
            
            txtPasosDados.setText(juego.getJgPasos());
        } else {
            new Mensajes().showModal(Alert.AlertType.ERROR, "Cargar paciente", getStage(), respuesta.getMensaje());
        }  
    }
    
    //Metodo para borrar las cajas de la matriz logica
    private void borrarCaja(){
     for(int i = 0; i<= 15; i++){
          for(int j = 0; j<= 18; j++){
              if(Matriz4[i][j] == 3){
                 Matriz4[i][j] = 1;
              }
          }   
      }   
    }
    
    //Metodo para retornar si la partida es cargada o no
    public Boolean getCargarP(){      
        return cargarP;  
    }
    
    //Metodo para retornar los pasos pasos
    public int getPasos(){
        return pasos;
    }   
    
    //Metodo para retornar las cajas
    public int getCajas(){
        return cajas;
    } 

    //Metodo para salir de la partida y volver a menu
    @FXML
    private void onActionSalir(ActionEvent event) {
        FlowController.getInstance().goViewInNewStage("InicioView", stage);
    }
    
}
