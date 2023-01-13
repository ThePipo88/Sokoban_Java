/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import sokoban.Dto.LogrosDto;
import sokoban.Dto.MenuDto;
import sokoban.clases.Jugador;
import sokoban.clases.Logro;
import sokoban.model.Logros;
import sokoban.service.SokobanService;
import sokoban.util.FlowController;
import sokoban.util.Respuesta;

/**
 * FXML Controller class
 *
 * @author Pipo
 */
public class LogrosViewController extends Controller implements Initializable {

    @FXML
    private Pane panePrimerLogro;
    @FXML
    private JFXButton btnVolver;
    @FXML
    private Pane panePrimerLogro1;
    @FXML
    private Pane panePrimerLogro2;
    @FXML
    private Pane panePrimerLogro3;
    @FXML
    private Pane panePrimerLogro4;
    @FXML
    private Pane panePrimerLogro5;
    
    private ObservableList<Logro> equipo = FXCollections.observableArrayList();
    
    @FXML
    private TableView<Logro> gridListaLogros;
    @FXML
    private TableColumn txtColumnaNombre;
    @FXML
    private TableColumn txtColumnaLogro1;
    @FXML
    private TableColumn txtColumnaLogro2;
    @FXML
    private TableColumn txtColumnaLogro3;
    @FXML
    private TableColumn txtColumnaLogro4;
    @FXML
    private TableColumn txtColumnaLogro5;
    @FXML
    private TableColumn txtColumnaLogro6;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

      //Carga los datos de los logros en LogrosDto y los agrega a una ObservableList, con la cual de agregaran a el TableView
      SokobanService service = new SokobanService();
      Respuesta respuesta = service.getLogros();
      List<LogrosDto> logros = new ArrayList<>();
      logros = (List) respuesta.getResultado("Logro");
      int tamanio = logros.size();
      for(int i = 0; i < tamanio; i++){
        Logro logr = new Logro(logros.get(i).getLgNombre(),logros.get(i).getLgPrimerLogro(),logros.get(i).getLgSegundoLogro(),logros.get(i).getLgTercerLogro(),
        logros.get(i).getLgCuartoLogro(),logros.get(i).getLgQuintoLogro(),logros.get(i).getLgSextoLogro());  
        equipo.add(logr);
      }
      //Metodo para cargar datos a un TableView
      cargarDatos();
      
    }    

    @Override
    public void initialize() {
       
    }

    //Metodo para volver a manu
    @FXML
    private void onActionVolver(ActionEvent event) {
        FlowController.getInstance().goViewInNewStage("InicioView", stage); 
    }
    //Carga los datos a la TableView
    private void cargarDatos(){
      this.txtColumnaNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
      this.txtColumnaLogro1.setCellValueFactory(new PropertyValueFactory("primerLogro"));
      this.txtColumnaLogro2.setCellValueFactory(new PropertyValueFactory("segundoLogro"));
      this.txtColumnaLogro3.setCellValueFactory(new PropertyValueFactory("tercerLogro"));
      this.txtColumnaLogro4.setCellValueFactory(new PropertyValueFactory("cuartoLogro"));
      this.txtColumnaLogro5.setCellValueFactory(new PropertyValueFactory("quintoLogro"));
      this.txtColumnaLogro6.setCellValueFactory(new PropertyValueFactory("sextoLogro"));
      this.gridListaLogros.setItems(equipo);
    }

    
}
