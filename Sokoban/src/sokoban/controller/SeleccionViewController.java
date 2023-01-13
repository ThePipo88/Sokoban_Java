/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sokoban.Dto.MenuDto;
import sokoban.clases.Dificultad;
import sokoban.clases.Jugador;
import sokoban.service.SokobanService;
import sokoban.util.FlowController;
import sokoban.util.Mensajes;
import sokoban.util.Respuesta;

/**
 * FXML Controller class
 *
 * @author Pipo
 */
public class SeleccionViewController extends Controller implements Initializable {

    InicioViewController init = new InicioViewController();
    
    @FXML
    private JFXCheckBox chkNivel1;
    @FXML
    private JFXCheckBox chkNivel2;
    @FXML
    private JFXCheckBox chkNivel3;
    @FXML
    private JFXCheckBox chkNivel4;
    @FXML
    private JFXCheckBox chkNivel5;
    @FXML
    private JFXComboBox<String> cbDificultad;
    private JFXCheckBox chkPersonaje1;
    private JFXCheckBox chkPersonaje2;
    private JFXCheckBox chkPersonaje3;
    @FXML
    private JFXTextField txtNombreJugador;
    
    private static String nombre;
    
    private static String dificultadT;
    
    private boolean cmpNivel;
    
    private boolean cmpPersonaje;
    
    private static int nivel;
    
    private static int personaje;
    
    private static int dificultad = 0;
    
    private String jugador;
    
    private String filas;
    
    private String columnas;
    
    @FXML
    private VBox VBNuevaPartida;
    @FXML
    private JFXButton btnRegresarN;
    @FXML
    private VBox VBCargarPartida;
    @FXML
    private TableColumn txtColumnaNombre;
    @FXML
    private TableColumn txtColumnaNivel;
    @FXML
    private TableColumn txtColumnaDificultad;
    @FXML
    private JFXTextField txtCargarPartida;
    @FXML
    private JFXButton btnRegresar;
    @FXML
    private JFXButton btnComenzarN;
    @FXML
    private JFXButton btnCargarPartida;
    @FXML
    private TableView<Jugador> gridListaJugadores;
    
    public ObservableList<MenuDto> equipos = FXCollections.observableArrayList();
    private ObservableList<Jugador> equipo = FXCollections.observableArrayList();
    private ObservableList<Dificultad> dif = FXCollections.observableArrayList();
    @FXML
    private TableView<Dificultad> tableDificultad;
    @FXML
    private TableColumn columnNivel;
    @FXML
    private TableColumn columnFacil;
    @FXML
    private TableColumn columnMedio;
    @FXML
    private TableColumn columnDificil;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Si se va a cargar la partida, se oculta la ventana de cargar partida, y se activa los componentes de cargar partida, en caso contrario de deja igual
        if(init.getCargar() == true){
      VBNuevaPartida.setVisible(false);
      VBCargarPartida.setVisible(true);
      SokobanService service = new SokobanService();
      Respuesta respuesta = service.getPartidas();
      List<MenuDto> jugadores = new ArrayList<>();
      jugadores = (List) respuesta.getResultado("Menu");
      int tamanio = jugadores.size();
      for(int i = 0; i < tamanio; i++){
          Jugador jug = new Jugador(jugadores.get(i).getMnNombre(),jugadores.get(i).getMnMapa(),jugadores.get(i).getMnDificultad());
          equipo.add(jug);
      }
      agregarDatos();
        }else{
            //Se carga la informacion para crear una nueva partida
            VBNuevaPartida.setVisible(true);
            VBCargarPartida.setVisible(false);
            cbDificultad.getItems().add("Facil");
            cbDificultad.getItems().add("Medio");
            cbDificultad.getItems().add("Dificil");
            Dificultad d1 = new Dificultad("Nivel 1","68","120","150");
            dif.add(d1);
            Dificultad d2 = new Dificultad("Nivel 2","265","300","350");
            dif.add(d2);
            Dificultad d3 = new Dificultad("Nivel 3","760","860","960");
            dif.add(d3);
            Dificultad d4 = new Dificultad("Nivel 4","1000","1100","1200");
            dif.add(d4);
            Dificultad d5 = new Dificultad("Nivel 5","1100","1200","1300");
            dif.add(d5);
            agregarDatosDificultad();
        }
        
    }    

    @Override
    public void initialize() {

    }
    
    

    //Si en el el check se selecciona nivel 1, se cargan los datos necesario en nivel y cmpNivel y se desmarcan los demas check
    @FXML
    private void onActionNivel1(ActionEvent event) {
        if(chkNivel1.isSelected() == true){
            nivel = 1;
            cmpNivel = true;
        }else{
            cmpNivel = false;
            nivel = 0;
        }
        chkNivel2.setSelected(false);
        chkNivel3.setSelected(false);
        chkNivel4.setSelected(false);
        chkNivel5.setSelected(false); 

    }

    //Si en el el check se selecciona nivel 2, se cargan los datos necesario en nivel y cmpNivel y se desmarcan los demas check
    @FXML
    private void onActionNivel2(ActionEvent event) {
        if(chkNivel2.isSelected() == true){
            nivel = 2;
            cmpNivel = true;
        }else{
            cmpNivel = false;
            nivel = 0;
        }
        chkNivel1.setSelected(false);
        chkNivel3.setSelected(false);
        chkNivel4.setSelected(false);
        chkNivel5.setSelected(false);
    }

    //Si en el el check se selecciona nivel 3, se cargan los datos necesario en nivel y cmpNivel y se desmarcan los demas check
    @FXML
    private void onActionNivel3(ActionEvent event) {
        if(chkNivel3.isSelected() == true){
            nivel = 3;
            cmpNivel = true;
        }else{
            cmpNivel = false;
            nivel = 0;
        }
        chkNivel1.setSelected(false);
        chkNivel2.setSelected(false);
        chkNivel4.setSelected(false);
        chkNivel5.setSelected(false);
    }

    //Si en el el check se selecciona nivel 4, se cargan los datos necesario en nivel y cmpNivel y se desmarcan los demas check
    @FXML
    private void onActionNivel4(ActionEvent event) {
        if(chkNivel4.isSelected() == true){
            nivel = 4;
            cmpNivel = true;
        }else{
            cmpNivel = false;
            nivel = 0;
        }
        chkNivel1.setSelected(false);
        chkNivel2.setSelected(false);
        chkNivel3.setSelected(false);
        chkNivel5.setSelected(false);
    }

    //Si en el el check se selecciona nivel 5, se cargan los datos necesario en nivel y cmpNivel y se desmarcan los demas check
    @FXML
    private void onActionNivel5(ActionEvent event) {
        if(chkNivel5.isSelected() == true){
            nivel = 5;
            cmpNivel = true;
        }else{
            cmpNivel = false;
            nivel = 0;
        }
        chkNivel1.setSelected(false);
        chkNivel2.setSelected(false);
        chkNivel3.setSelected(false);
        chkNivel4.setSelected(false);
    }

    
    private void onActionPersonaje1(ActionEvent event) {
        if(chkPersonaje1.isSelected() == true){
            personaje = 1;
            cmpPersonaje = true;
        }else{
            cmpPersonaje = false;
            personaje = 0;
        }
        chkPersonaje2.setSelected(false);
        chkPersonaje3.setSelected(false);
    }
    
    //Si la dificultad seleccionada es facil, medio o dificil se cargaran los datos necesarios en dificultas y dificultadT
    @FXML
    private void onActionDificultad(ActionEvent event) {
        
        if(null != cbDificultad.getSelectionModel().getSelectedItem())switch (cbDificultad.getSelectionModel().getSelectedItem()) {
            case "Facil":
                dificultad = 1;
                dificultadT = "Facil";
                break;
            case "Medio":
                dificultad = 2;
                dificultadT = "Medio";
                break;
            case "Dificil":
                dificultad = 3;
                dificultadT = "Dificil";
                break;
            default:
                break;
        }
        
    }
    
    //Metodo para retornar el nombre
    public String getNombre(){
        return nombre;
    }
    
    //Metodo para retornar la dificultad
    public int getDificultad(){
        return dificultad;
    }
    
    //Metodo para agregar datos en el TableView de cargar partida
    private void agregarDatos(){
      this.txtColumnaNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
      this.txtColumnaNivel.setCellValueFactory(new PropertyValueFactory("mapa"));
      this.txtColumnaDificultad.setCellValueFactory(new PropertyValueFactory("dificultad"));
      this.gridListaJugadores.setItems(equipo);
    }
    
    //Metodo para agregar datos en el TableView de dificultad
    private void agregarDatosDificultad(){
    columnNivel.setCellValueFactory(new PropertyValueFactory("mapa"));
    columnFacil.setCellValueFactory(new PropertyValueFactory("facil"));
    columnMedio.setCellValueFactory(new PropertyValueFactory("medio"));
    columnDificil.setCellValueFactory(new PropertyValueFactory("dificil"));
    this.tableDificultad.setItems(dif);
    }

    //Accion para regresar al menu
    @FXML
    private void onActionRegresarN(ActionEvent event) {
        FlowController.getInstance().goViewInNewStage("InicioView", stage); 
    }

    //Accion para regresar al menu
    @FXML
    private void onActionRegresar(ActionEvent event) {
        FlowController.getInstance().goViewInNewStage("InicioView", stage);
    }

    //Metodo para comenzar una nueva partida, se verifica que se tengan todos los datos necesarios para iniciar un juego nuevo, una vez verificado estos datos se ingresa a el mapa seleccionado
    @FXML
    private void onActionComenzarN(ActionEvent event) {
        
        boolean valido;
        boolean primero = true;
        String requeridos = "";
        Mensajes sms = new Mensajes();
        
        nombre = txtNombreJugador.getText();
        
        if(nivel != 0 && dificultad != 0 && txtNombreJugador.getText() != null){
            valido = true;
        }else{
           valido = false;
           if(nivel == 0 && primero == true){
               if(primero){
                requeridos += "Nivel";
                primero = false;   
               }else{    
               requeridos += "," + "Nivel";
            } 
           }
           if(personaje == 0){
               if(primero){
                requeridos += "Personaje";
                primero = false;   
               }else{
                requeridos += "," + "Personaje";
            }
           }
           if(dificultad == 0){
               if(primero){
                 requeridos += "Dificultad";
                 primero = false;   
               }else{
                 requeridos += "," + "Dificultad";
             }  
           }
           if(txtNombreJugador.getText().isEmpty()){
              if(primero){
                 requeridos += "Nombre";
                 primero = false;   
               }else{
                 requeridos += "," + "Nombre";
             } 
           }
           sms.showModal(Alert.AlertType.ERROR,"Error", stage, "Faltan datos por ingresar: "+requeridos);
           requeridos = "";
           primero = true;
        }
        
        if(valido == true){
            
          guardarPartida();  
          
            switch (nivel) {
                case 1:
                    FlowController.getInstance().goViewInNewStage("Nivel1View", stage);
                    break;
                case 2:
                    FlowController.getInstance().goViewInNewStage("Nivel2View", stage);
                    break;
                case 3:
                    FlowController.getInstance().goViewInNewStage("Nivel3View", stage);
                    break;
                case 4:
                    FlowController.getInstance().goViewInNewStage("Nivel4View", stage);
                    break;
                case 5:
                    FlowController.getInstance().goViewInNewStage("Nivel5View", stage);
                    break;
                default:
                    break;
            }
          
        }
    }
    
    //Si se crea una partida nueva, se crea el jugador, los logros y el mapa en base de datos.
    private void guardarPartida(){
        SokobanService service = new SokobanService();
        Respuesta respuesta = service.crearJuego(txtNombreJugador.getText(), (long)nivel, dificultadT,false);
         
     if(!respuesta.getEstado()){
                    new Mensajes().showModal(Alert.AlertType.ERROR, "Crear Partida", stage, respuesta.getMensaje());
                }else{
        SokobanService logros = new SokobanService();
        Respuesta respLogros = logros.crearLogros(txtNombreJugador.getText(), "No logrado", "No logrado", "No logrado", "No logrado", "No logrado", "No logrado", false);
                
        SokobanService serviceN = new SokobanService();
        validarPosiciones();
        Respuesta respuestaN = serviceN.crearMapa(txtNombreJugador.getText(), (long)0, jugador,filas,columnas,false);
                    new Mensajes().showModal(Alert.AlertType.INFORMATION, "Crear Partida", stage, "Partida creada con exito"); 
                }   
    }
    
    //Setea las posiciones iniciales de las cajas y el jugador en base de datos
    private void validarPosiciones(){
        switch (nivel) {
            case 1:
                jugador = "1-1";
                filas = "4-5-5-6";
                columnas = "7-6-8-7";
                break;
            case 2:
                
                jugador = "5-1";
                filas = "5-7-5-7-2-5";
                columnas = "2-3-4-4-7-7";
                break;
            case 3:
                jugador = "8-3";
                filas = "10-5-7-8-5-5-7-8-5-8";
                columnas = "3-4-4-4-5-6-6-6-7-9";
                
                break;
            case 4:
                jugador = "8-2";
                filas = "10-9-4-4-7-14-14-9-3-7-10";
                columnas = "2-3-5-6-5-4-8-9-10-10-10";
                break;
            case 5:
                jugador = "2-7";
                filas = "10-15-14-14-3-5-2-6-3-4-6-4-3";
                columnas = "2-2-3-4-7-7-8-8-9-9-10-11-12";
                break;
            default:
                break;
        }
        
    }
    
    //Metodo para cargar los datos al juego desde base de datos, mediante el nombre se carga la partida en SokobanDto y con esto se setea el nombre del jugador, el mapa actual
    //y los pasos dados por este
    @FXML
    private void onActionCargarPartida(ActionEvent event) {
        
        SokobanService service = new SokobanService();
        Respuesta respuesta = service.getPartida(txtCargarPartida.getText());
        MenuDto menu = new MenuDto();
        
        if (respuesta.getEstado()) {
            menu = (MenuDto) respuesta.getResultado("Partida");
            cargarDatos(menu);
            new Mensajes().showModal(Alert.AlertType.INFORMATION, "Cargar",stage, "Partida cargada con exito");
        } else {
            new Mensajes().showModal(Alert.AlertType.ERROR, "Cargar ", getStage(), respuesta.getMensaje());
        }
    }
    
    //Metodo usado para cargar datos como la dificultad y el mapa a jugar desde base de datos
    private void cargarDatos(MenuDto menu){
        nombre = menu.getMnNombre();
        
        switch(menu.getMnDificultad()) {
            case "Facil":
                dificultad = 1;
                dificultadT = "Facil";
                break;
            case "Medio":
                dificultad = 2;
                dificultadT = "Medio";
                break;
            case "Dificil":
                dificultad = 3;
                dificultadT = "Dificil";
                break;
            default:
                break;
        }
        
        switch (Integer.valueOf(menu.getMnMapa())) {
                case 1:
                    FlowController.getInstance().goViewInNewStage("Nivel1View", stage);
                    break;
                case 2:
                    FlowController.getInstance().goViewInNewStage("Nivel2View", stage);
                    break;
                case 3:
                    FlowController.getInstance().goViewInNewStage("Nivel3View", stage);
                    break;
                case 4:
                    FlowController.getInstance().goViewInNewStage("Nivel4View", stage);
                    break;
                case 5:
                    FlowController.getInstance().goViewInNewStage("Nivel5View", stage);
                    break;
                default:
                    break;
            }
        
        
    }
}
