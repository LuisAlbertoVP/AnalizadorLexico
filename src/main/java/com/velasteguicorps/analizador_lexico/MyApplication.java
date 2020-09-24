package com.velasteguicorps.analizador_lexico;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author luis
 */
public class MyApplication extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));   
        Scene scene = new Scene(root); 
        stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/icon/x.png")));
        stage.setTitle("CompilerX");
        stage.setResizable(false); 
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}
