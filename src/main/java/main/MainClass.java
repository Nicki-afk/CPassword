package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import logic.Pass;
import managers.CryptoManager;

import java.io.IOException;


public class MainClass extends Application {


    public static void main(String[] args) throws IOException {




         launch();






    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Pane pane = FXMLLoader.load(getClass().getResource(isFirstStart() ? "/fxml/info.fxml" : "/fxml/sin.fxml"));
        Scene scene = new Scene(pane );
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();


    }


    public boolean isFirstStart(){

        CryptoManager manager = CryptoManager.getInstance();
        manager.init();


        return manager.getRecords().size() < 3;


    }


}
