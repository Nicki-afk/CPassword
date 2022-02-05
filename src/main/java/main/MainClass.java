package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import logic.Pass;

import java.io.IOException;


public class MainClass extends Application {


    public static void main(String[] args) throws IOException {



//        byte[]salt = new byte[16];
//        byte[]iv = new byte[16];
//        SecureRandom random = new SecureRandom();
//        random.nextBytes(salt);
//        random.nextBytes(iv);
//
//        Pass pass = new Pass(0 , "mail" , "mail.ru" , "22:01:1222" , "dwdwdw");
//
//        Encrypt encrypt = new Encrypt("DOG" , pass , salt , new IvParameterSpec(iv));
//        System.out.println(encrypt.enc());



         launch();






    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Pane pane = FXMLLoader.load(getClass().getResource("/fxml/sin.fxml"));
        Scene scene = new Scene(pane );
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
//        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
      //  com.sun.glass.ui.Window.getWindows().get(0).setUndecoratedMoveRectangle(22);


    }
}
