/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import sokoban.util.FlowController;

/**
 * FXML Controller class
 *
 * @author Pipo
 */
public class AcercaDeViewController extends Controller implements Initializable {

    @FXML
    private JFXButton btnVolverAD;

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

    //Metodo para volver al menu
    @FXML
    private void onActionVolverAD(ActionEvent event) {
     FlowController.getInstance().goViewInNewStage("InicioView", stage); 
    }
    
}
