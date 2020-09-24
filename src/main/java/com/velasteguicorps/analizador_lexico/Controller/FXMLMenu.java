package com.velasteguicorps.analizador_lexico.Controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author luis
 */
public class FXMLMenu implements Initializable {
    
    FXMLPrincipal fxmlPrincipal;
    
    @FXML
    private BorderPane borderPanePrincipal;
     
    @FXML
    private MenuItem menuItemAbrir;

    @FXML
    private MenuItem menuItemSalir;

    @FXML
    private MenuItem menuItemSobre;

  
    @FXML
    void handleArchivo(ActionEvent event) {
        if(menuItemAbrir == event.getSource()){
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Abrir programa");
            File file = fileChooser.showOpenDialog(new Stage());
            if(file != null)
                fxmlPrincipal.openFile(file);
        }
        if(menuItemSalir == event.getSource()){
            Platform.exit();
        }
    }

    @FXML
    void handleSobre(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText(null);
        alert.setContentText("CompilerX v1.0\nTodos los derechos reservados\n"
                + "velasteguicorps\n2018");

        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarPrincipal();
    }    
    
    private void cargarPrincipal(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Principal.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            fxmlPrincipal = fxmlLoader.getController();
            borderPanePrincipal.setCenter(root);
        } catch (IOException ex) {}
    }
}
