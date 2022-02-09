package managers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class WinManager {




    public static void loadWindow(String fxml){

        try {

            Stage stage = new Stage();
            Pane pane = FXMLLoader.load(WinManager.class.getResource(fxml));
            stage.setResizable(false);
            Scene scene = new Scene(pane );
            stage.setScene(scene);
            stage.showAndWait();

        }catch (IOException e){


            System.err.println("ERROR LOAD WINDOW (" + e + "):( WinManage.class )");


        }


    }




}
