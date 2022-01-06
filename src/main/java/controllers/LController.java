package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import logic.Crypto;
import logic.PSystem;
import managers.WinManager;

public class LController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane mainPane;

    @FXML
    private Text versionText;

    @FXML
    private PasswordField codeWordFiled;

    @FXML
    private Button logInButton;

    @FXML
    private Button sinInButton;

    @FXML
    private Button exitButton;

    @FXML
    void initialize() {

        versionText.setText(PSystem.getVersion());


        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        sinInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sinInButton.getScene().getWindow().hide();
                WinManager.loadWindow("/fxml/sinUpWindow.fxml");
            }
        });

        logInButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                Crypto crypto = new Crypto();
                if(crypto.isCodeWord(codeWordFiled.getText())){

                    logInButton.getScene().getWindow().hide();
                    WinManager.loadWindow("/fxml/savePWindow.fxml");

                }else{

                    codeWordFiled.setStyle(
                            "-fx-border-color :  #ed6a6a; -fx-background-color :  #2A2A27; " +
                                    "-fx-border-radius : 4; -fx-background-radius : 4; -fx-text-fill : #ed6a6a;"
                    );

                }


            }
        });



    }
}




//versionText.setText(PSystem.getVersion());
//
//
//        exitButton.setOnAction(new EventHandler<ActionEvent>() {
//@Override
//public void handle(ActionEvent event) {
//        System.exit(0);
//        }
//        });
//
//        sinInButton.setOnAction(new EventHandler<ActionEvent>() {
//@Override
//public void handle(ActionEvent event) {
//        sinInButton.getScene().getWindow().hide();
//        WinManager.loadWindow("/fxml/sinUpWindow.fxml");
//        }
//        });
//
//        logInButton.setOnAction(new EventHandler<ActionEvent>() {
//@Override
//public void handle(ActionEvent event) {
//
//        Encrypt encrypt = new Encrypt();
//        if(encrypt.decrypt(codeWordFiled.getText())){
//
//        logInButton.getScene().getWindow().hide();
//        WinManager.loadWindow("/fxml/savePWindow.fxml");
//
//        }else{
//
//        codeWordFiled.setStyle(
//        "-fx-border-color :  #ed6a6a; -fx-background-color :  #C0C0C0; " +
//        "-fx-border-radius : 4; -fx-background-radius : 4"
//        );
//
//        }
//
//
//        }
//        });
