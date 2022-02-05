package managers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class WinManager {



    // gui -> controller -> processing



    public static void loadWindow(String fxml){

        try {

            Stage stage = new Stage();
            Pane pane = FXMLLoader.load(WinManager.class.getResource(fxml));
            stage.setResizable(false);
            Scene scene = new Scene(pane );
            stage.setScene(scene);
            stage.showAndWait();
           //com.sun.glass.ui.Window.getWindows().get(0).setUndecoratedMoveRectangle(22);

        }catch (IOException e){
            e.printStackTrace();
        }


    }




}
