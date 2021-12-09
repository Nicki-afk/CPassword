package controllers;

import java.io.File;
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
import managers.FileManager;

public class SController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane mainPane;

    @FXML
    private Text versionText;

    @FXML
    private TextField nameFiled;

    @FXML
    private TextField firstNameFiled;

    @FXML
    private TextField eMailFiled;

    @FXML
    private TextField codeWordFiled;

    @FXML
    private Button sinUpButton;

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


        sinUpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String[]arr = {

                        nameFiled.getText() ,
                        firstNameFiled.getText() ,
                        eMailFiled.getText()

                };

                FileManager fileManager = new FileManager(arr);
                fileManager.createUser(); Encrypt encrypt = Encrypt.getInstance(codeWordFiled.getText());
                encrypt.encrypt();


                sinUpButton.getScene().getWindow().hide();


            }
        });




    }
}
