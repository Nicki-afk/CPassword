package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.Decrypt;
import logic.Encrypt;
import logic.Pass;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;


public class MainClass extends Application {


    public static void main(String[] args) throws IOException {



        byte[]salt = new byte[16];
        byte[]iv = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        random.nextBytes(iv);

        Pass pass = new Pass(0 , "mail" , "mail.ru" , "22:01:1222" , "dwdwdw");

        Encrypt encrypt = new Encrypt("DOG" , pass , salt , new IvParameterSpec(iv));
        System.out.println(encrypt.enc());




        // launch();






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
