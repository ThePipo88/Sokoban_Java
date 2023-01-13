/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sokoban.util.FlowController;
import com.jfoenix.controls.JFXButton;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Pipo
 */
public class InicioViewController extends Controller implements Initializable {

    @FXML
    private JFXButton btnJugar;
    @FXML
    private JFXButton btnAcercaDe;
    @FXML
    private JFXButton btnSalir;
    @FXML
    private VBox vbCenter;
    @FXML
    private BorderPane bpInicio;
    @FXML
    private VBox vbLado;
    
    private static Boolean cargar;
    @FXML
    private JFXButton btnCargarPartida;
    @FXML
    private JFXButton btnLogros;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void initialize() {
        
    }

    //Metodo para ingresar a el metodo de seleccion de partida
    @FXML
    private void onActionJugar(ActionEvent event) {
        cargar = false;
        FlowController.getInstance().goViewInNewStage("SeleccionView",stage);
    }

    private void onActionCargar(ActionEvent event) {
        
    }

    //Metodo para ingresar a el metodo de AcercaDeView
    @FXML
    private void onActionAcercaDe(ActionEvent event) {
        FlowController.getInstance().goViewInNewStage("AcercaDeView",stage);
    }

    //Metodo para cerrar el programa
    @FXML
    private void onActionSalir(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
    //Metodo para retornar si se esta cargando una partida
    public Boolean getCargar(){
        return cargar;
    }

    //Metodo para ingresar a la vista de cargar partida
    @FXML
    private void onActionCargarPartida(ActionEvent event) {
        cargar = true;
        FlowController.getInstance().goViewInNewStage("SeleccionView",stage);
    }

    //Metodo para ingresar a los logros
    @FXML
    private void onActionLogros(ActionEvent event) {
        FlowController.getInstance().goViewInNewStage("LogrosView",stage);
    }
}
