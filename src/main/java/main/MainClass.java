package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.Password;

public class MainClass extends Application {


    public static void main(String[] args) {

        //launch();


        Password p = new Password("NOCKERS_1234");
        System.out.println(p.howStrongIsThePassword());



    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Pane pane = FXMLLoader.load(getClass().getResource("/fxml/style.fxml"));
        Scene scene = new Scene(pane , Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
        com.sun.glass.ui.Window.getWindows().get(0).setUndecoratedMoveRectangle(22);


    }
}
