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
import javafx.scene.layout.Pane;
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
public class Nivel3ViewController extends Controller implements Initializable {
     // Se crean instancias que se ocuparan a lo largo del proyecto
    @FXML
    private AnchorPane anchorNivel3;
    @FXML
    private JFXButton btnC;
    @FXML
    private AnchorPane anchorJuego1;
    @FXML
    private GridPane gridMap3;
    
    Mapas mp = new Mapas();
     
    Personaje p = new Personaje(1,1,0,0);
    
    Caja ca = new Caja(1);
    
    Mensajes sms = new Mensajes();
    
    InicioViewController init = new InicioViewController();
    
    //Datos del proyecto como posicion del jugador, posicion cajas, matrices, Booleanos para validar ganes etc.
    
    private int x = 8;
    private int y = 3;
    private int c1 = 5;
    private int c2 = 5;
    private int c3 = 5;
    private int c4 = 5;
    private int c5 = 7;
    private int c6 = 8;
    private int c7 = 7;
    private int c8 = 8;
    private int c9 = 8;
    private int c10 = 10;
    
    private int y1 = 4;
    private int y2 = 5;
    private int y3 = 6;
    private int y4 = 7;
    private int y5 = 4;
    private int y6 = 4;
    private int y7 = 6;
    private int y8 = 6;
    private int y9 = 9;
    private int y10 = 3;
    
    private boolean nivelCompleto = false;
    private boolean primera = true;
    private boolean retroceso = true;
    private static int cajas = 0;
    private static int pasos = 0;
    
    private int xA,xAF;
    private int yA,yAF;
    private boolean regresar;
    private String dificultad;
    private final int Matriz3[][] = new int[15][19];
    private final int AuxP[][] = new int[15][19];
    private final int AuxF[][] = new int[15][19];
    private static Boolean cargarP = true;
    
    //Componentes JFX necesarios
    
    @FXML
    private Text txtPasosDados;
    @FXML
    private Text txtPasosPermitidos;
    @FXML
    private Pane paneJugador;
    @FXML
    private Text txtNombreJugador;
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
     Nivel2ViewController nivel2 = new Nivel2ViewController();
     cajas = nivel2.getCajas();
     pasos = nivel2.getPasos();
      if(init.getCargar() == true && nivel2.getCargarP() == true){
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
        
      for(int i = 0; i<= 14; i++){
          for(int j = 0; j<= 18; j++){
          AuxF[i][j] = AuxP[i][j]; 
          }   
      }
      
      for(int i = 0; i<= 14; i++){
          for(int j = 0; j<= 18; j++){
          AuxP[i][j] = Matriz3[i][j]; 
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
    gridMap3.add(ca.getCaja3(), c1, y1);
    gridMap3.add(ca.getCaja3(), c2, y2);
    gridMap3.add(ca.getCaja3(), c3, y3);
    gridMap3.add(ca.getCaja3(), c4, y4);
    gridMap3.add(ca.getCaja3(), c5, y5);
    gridMap3.add(ca.getCaja3(), c6, y6);
    gridMap3.add(ca.getCaja3(), c7, y7);
    gridMap3.add(ca.getCaja3(), c8, y8);
    gridMap3.add(ca.getCaja3(), c9, y9);
    gridMap3.add(ca.getCaja3(), c10, y10);
    gridMap3.add(p.getJugador(), x, y);
    }
    
    //Se pasa la matriz logica de el mapa desde la clase mapas a una matriz del juego
    public void pasarMatriz(){

      for(int i = 0; i<= 14; i++){
          for(int j = 0; j<= 18; j++){
              Matriz3[i][j] = mp.getMapa3(i, j);
          }   
      }

    }
   
    //Se configuran datos como el nombre del jugador, la cantidad de pasos y la cantidad de pasos a dar dependiendo de la dificultad
    private void configInterfaz(){
        SeleccionViewController ct = new SeleccionViewController();
        txtNombreJugador.setText(ct.getNombre());
        
        txtPasosDados.setText("0");
        
        txtTiempo.setText("Mapa 3");
        
        if(ct.getDificultad() == 1){
           txtPasosPermitidos.setText("960");
           dificultad = "Facil";
           
        }else if(ct.getDificultad() == 2){
           txtPasosPermitidos.setText("860"); 
           dificultad = "Medio";
           
        }else if(ct.getDificultad() == 3){
           txtPasosPermitidos.setText("760");
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
         anchorNivel3.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
             @Override
             public void handle(KeyEvent key) {
                 if(key.getCode()==KeyCode.A){
                     
                     if(Matriz3[y][x-1] == 3){
                         if(Matriz3[y][x-2] != 0 && Matriz3[y][x-2] != 3){
                             removeNodeByRowColumnIndex(y,x-1,gridMap3);
                             removeNodeByRowColumnIndex(y,x,gridMap3);
                             Matriz3[y][x-1] = 1;
                             Matriz3[y][x-2] = 3;
                             moverCaja(3);
                             x--;
                             p.setL(3);
                             gridMap3.add(p.getJugador(), x, y);
                             sumarPasos();
                             retroceso();
                         }else {
                             removeNodeByRowColumnIndex(y,x,gridMap3);
                             p.setL(3);
                             gridMap3.add(p.getJugador(), x, y);
                         }
                     }else if(Matriz3[y][x-1] != 0){
                         removeNodeByRowColumnIndex(y,x,gridMap3);
                         x--;
                         p.setL(3);
                         gridMap3.add(p.getJugador(), x, y);
                         sumarPasos();
                         retroceso();
                     }else{
                         removeNodeByRowColumnIndex(y,x,gridMap3);   
                         p.setL(3);
                         gridMap3.add(p.getJugador(), x, y);
                     }
                 }
                 if(key.getCode()==KeyCode.D){
                     
                     if(Matriz3[y][x+1] == 3){
                         if(Matriz3[y][x+2] != 0 && Matriz3[y][x+2] != 3){
                             removeNodeByRowColumnIndex(y,x+1,gridMap3);
                             removeNodeByRowColumnIndex(y,x,gridMap3);
                             Matriz3[y][x+1] = 1;
                             Matriz3[y][x+2] = 3;
                             moverCaja(4);
                             x++;
                             p.setL(4);
                             gridMap3.add(p.getJugador(), x, y);
                             sumarPasos();
                             retroceso();
                         }else {
                             removeNodeByRowColumnIndex(y,x,gridMap3);
                             p.setL(4);
                             gridMap3.add(p.getJugador(), x, y);
                         }
                     }else if(Matriz3[y][x+1] != 0){
                         removeNodeByRowColumnIndex(y,x,gridMap3);
                         x++;
                         p.setL(4);
                         gridMap3.add(p.getJugador(), x, y);
                         sumarPasos();
                         retroceso();
                     }else{
                         removeNodeByRowColumnIndex(y,x,gridMap3);
                         p.setL(4);
                         gridMap3.add(p.getJugador(), x, y);
                     }   
                 }
                 if(key.getCode()==KeyCode.S){
                     
                     if(Matriz3[y+1][x] == 3){
                         if(Matriz3[y+2][x] != 0 && Matriz3[y+2][x] != 3){
                             removeNodeByRowColumnIndex(y+1,x,gridMap3);
                             removeNodeByRowColumnIndex(y,x,gridMap3);
                             Matriz3[y+1][x] = 1;
                             Matriz3[y+2][x] = 3;
                             moverCaja(1);
                             y++;
                             p.setL(1);
                             gridMap3.add(p.getJugador(), x, y);
                             sumarPasos();
                             retroceso();
                         }else {
                             removeNodeByRowColumnIndex(y,x,gridMap3);
                             p.setL(1);
                             gridMap3.add(p.getJugador(), x, y);
                         }
                     }else if(Matriz3[y+1][x] != 0){
                         removeNodeByRowColumnIndex(y,x,gridMap3);
                         y++;
                         p.setL(1);
                         gridMap3.add(p.getJugador(), x, y);
                         sumarPasos();
                         retroceso();
                     }else{
                         removeNodeByRowColumnIndex(y,x,gridMap3);
                         p.setL(1);
                         gridMap3.add(p.getJugador(), x, y);
                     }   
                 }
                 if(key.getCode()==KeyCode.W){
                     
                     if(Matriz3[y-1][x] == 3){
                         if(Matriz3[y-2][x] != 0 && Matriz3[y-2][x] != 3){
                             removeNodeByRowColumnIndex(y-1,x,gridMap3);
                             removeNodeByRowColumnIndex(y,x,gridMap3);
                             Matriz3[y-1][x] = 1;
                             Matriz3[y-2][x] = 3;
                             moverCaja(2);
                             y--;
                             p.setL(2);
                             gridMap3.add(p.getJugador(), x, y);
                             sumarPasos();
                             retroceso();
                         }else {
                             removeNodeByRowColumnIndex(y,x,gridMap3);
                             p.setL(2);
                             gridMap3.add(p.getJugador(), x, y);
                         }
                     }else if(Matriz3[y-1][x] != 0){
                         removeNodeByRowColumnIndex(y,x,gridMap3);
                         y--;
                         p.setL(2);
                         gridMap3.add(p.getJugador(), x, y);
                         sumarPasos();
                         retroceso();
                     }else{
                         removeNodeByRowColumnIndex(y,x,gridMap3);
                         p.setL(2);
                         gridMap3.add(p.getJugador(), x, y);
                     }   
                 }
                 pasos++;
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
                removeNodeByRowColumnIndex(y+1,x,gridMap3);  
                gridMap3.add(ca.getCaja3(), x, y+2); 
                break;
            case 2:
                removeNodeByRowColumnIndex(y-1,x,gridMap3);  
                gridMap3.add(ca.getCaja3(), x, y-2); 
                break;
            case 3:
                removeNodeByRowColumnIndex(y,x-1,gridMap3);  
                gridMap3.add(ca.getCaja3(), x-2, y); 
                break;
            case 4:
                removeNodeByRowColumnIndex(y,x+1,gridMap3);   
                 gridMap3.add(ca.getCaja3(), x+2, y);    
                break;
            default:
                break;
        }
    }
    
    //Metodo para  remover un jugador de una celda en especifico, recive como parametro una fila, una columna y el GridPane escogido
    //una vez hecho esto se agregaran todos los nodos de el grid pane y se comprobara cual de estos nodos concuerda con la fila y la columna enviada,
    //una vez identificada se borraria del grid pane.
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
       if(Matriz3[11][5] == 3 && Matriz3[12][1] == 3 && Matriz3[12][2] == 3 && Matriz3[12][3] == 3 && Matriz3[12][5] == 3 && Matriz3[13][1] == 3 &&
          Matriz3[13][2] == 3 && Matriz3[13][3] == 3 && Matriz3[13][4] == 3 && Matriz3[13][5] == 3){
          nivelCompleto = true;
          validarLogros();
          sms.showModal(Alert.AlertType.INFORMATION, "Mapa Ganado", stage, "El nivel se ha completado con exito, precione aceptar para continiar");
          FlowController.getInstance().goViewInNewStage("Nivel4View", stage);
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
        
        int tamanio = gridMap3.getChildren().size()-1;
        
        for(int i = 0; i<= 14; i++){
          for(int j = 0; j<= 18; j++){
              if(Matriz3[i][j] == 3){
                  System.out.print("dentro");
                 removeNodeByRowColumnIndex(i,j,gridMap3); 
              }
          }   
      }
        
        removeNodeByRowColumnIndex(y,x,gridMap3); 

        x = 8;
        
        y = 3;
        
        c1 = 5;
        c2 = 5;
        c3 = 5;
        c4 = 5;
        c5 = 7;
        c6 = 8;
        c7 = 7;
        c8 = 8;
        c9 = 8;
        c10 = 10;
    
        y1 = 4;
        y2 = 5;
        y3 = 6;
        y4 = 7;
        y5 = 4;
        y6 = 4;
        y7 = 6;
        y8 = 6;
        y9 = 9;
        y10 = 3;
        
        txtPasosDados.setText("0");
        
        pasarMatriz();
        
        crearJugador();
  
    }
    
    //Metodo que guarda la informacion cuando se quiere retroceder una jugada
     private void retroceso(){
        
        xAF = xA;
        yAF = yA;
        
      for(int i = 0; i<= 14; i++){
          for(int j = 0; j<= 18; j++){
          AuxF[i][j] = AuxP[i][j]; 
          }   
      }
      
      for(int i = 0; i<= 14; i++){
          for(int j = 0; j<= 18; j++){
          AuxP[i][j] = Matriz3[i][j]; 
          }   
      }
      
      xA = x;
      yA = y;
      
      regresar = true;
    }

     //Accion para regresar un movimiento, elimina todo lo que esta en el Grid Pane y agrega la informacion almacenada en retroceso
    @FXML
    private void onMouseClickedRegresar(MouseEvent event) {
        retroceso = false;
        primera = false;
        if(regresar == true){
          for(int i = 0; i<= 14; i++){
          for(int j = 0; j<= 18; j++){
              if(Matriz3[i][j] == 2 || Matriz3[i][j] == 3){
                 removeNodeByRowColumnIndex(i,j,gridMap3); 
              }
          }   
      }
        
       for(int i = 0; i<= 14; i++){
          for(int j = 0; j<= 18; j++){
          Matriz3[i][j] = AuxF[i][j]; 
          }   
      }
        
       for(int i = 0; i<= 14; i++){
          for(int j = 0; j<= 18; j++){
              if(Matriz3[i][j] == 3){
                gridMap3.add(ca.getCaja3(), j, i);
              }
          }   
      } 
       
        removeNodeByRowColumnIndex(y,x,gridMap3); 
        x = xAF;
        y = yAF;
        gridMap3.add(p.getJugador(), x, y);
        restarPasos();  
        regresar = false;
        }
    }
    
    //Metodo para verificar si se ha realizado un mal movimiento, analizando si en la matriz logica se ha movido una caja en el lugar no indicado,
    //se enviara un mensaje al jugador indicando que la jugada esta mal hecha
    private void validarMovimiento(){
        
    
     if(Matriz3[1][11] == 3 || Matriz3[1][12] == 3 || Matriz3[2][6] == 3 || Matriz3[2][7] == 3 || Matriz3[3][4] == 3 || Matriz3[3][12] == 3 || Matriz3[4][3] == 3 ||
        Matriz3[5][2] == 3 || Matriz3[5][12] == 3 || Matriz3[6][4] == 3 || Matriz3[7][2] == 3 || Matriz3[7][8] == 3 || Matriz3[7][13] == 3 || Matriz3[8][7] == 3 ||
        Matriz3[9][7] == 3 || Matriz3[9][17] == 3 || Matriz3[10][9] == 3 || Matriz3[10][10] == 3 || Matriz3[10][1] == 3 || Matriz3[10][5] == 3){
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
        
        for(int i = 0; i<= 14; i++){
           for(int j = 0; j<= 18; j++){
               if(Matriz3[i][j] == 3){
               guardarFilas += i+"-";
               guardarColumnas += j+"-";
              }
           }
        } 
 
         SokobanService service = new SokobanService();
         SeleccionViewController ct = new SeleccionViewController();

         Respuesta respuesta = service.crearJuego(txtNombreJugador.getText(),(long)3,dificultad,true);
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
           new Mensajes().showModal(Alert.AlertType.INFORMATION, "Guardar paciente", stage, "Partida guardada correctamente.");
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
           
            Matriz3[y1][c1] = 3;
            Matriz3[y2][c2] = 3;
            Matriz3[y3][c3] = 3;
            Matriz3[y4][c4] = 3;
            Matriz3[y5][c5] = 3;
            Matriz3[y6][c6] = 3;
            Matriz3[y7][c7] = 3;
            Matriz3[y8][c8] = 3;
            Matriz3[y9][c9] = 3;
            Matriz3[y10][c10] = 3;
            
            txtPasosDados.setText(juego.getJgPasos());
        } else {
            new Mensajes().showModal(Alert.AlertType.ERROR, "Cargar paciente", getStage(), respuesta.getMensaje());
        }  
    }
     
    //Metodo para borrar las cajas de la matriz logica
    private void borrarCaja(){
     for(int i = 0; i<= 14; i++){
          for(int j = 0; j<= 18; j++){
              if(Matriz3[i][j] == 3){
                 Matriz3[i][j] = 1;
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
    private void onActionSsalir(ActionEvent event) {
         FlowController.getInstance().goViewInNewStage("InicioView", stage);
    }
 
}
