package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import logic.Encrypt;
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
    private TextField codeWordFiled;

    @FXML
    private Button logInButton;

    @FXML
    private Button sinInButton;

    @FXML
    private Text ForgotText;

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

                Encrypt encrypt = new Encrypt();
                if(encrypt.decrypt(codeWordFiled.getText())){

                    logInButton.getScene().getWindow().hide();

                }


            }
        });



    }
}
