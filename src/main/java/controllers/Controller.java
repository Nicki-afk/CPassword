package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import logic.PSystem;
import logic.Password;
import managers.WinManager;


public class Controller {
    @FXML
    private Pane mainPane;

    @FXML
    private TextField passworUser;

    @FXML
    private TextField recommendedPassword;

    @FXML
    private Button exitButton;

    @FXML
    private Text percentageText;

    @FXML
    private Text versionText;

    @FXML
    private Button copyButton;

    @FXML
    private Button reloadButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button getButton;


    private Password password;


    @FXML
    void initialize() {

        versionText.setText(PSystem.getVersion());

        recommendedPassword.setText(new Password().generatePassword());


        mainPane.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                password = new Password(mainPane.getScene(), passworUser ,   percentageText);
                password.listen();
            }
        });


        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });


        copyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {

                    ClipboardContent clipboardContent = new ClipboardContent();
                    clipboardContent.putString(recommendedPassword.getText());
                    // set clipboard content
                    Clipboard.getSystemClipboard().setContent(clipboardContent);

                    recommendedPassword.setStyle("-fx-border-color: #00FF7F; -fx-text-fill : #00FF7F; -fx-background-color : #C0C0C0; -fx-border-radius : 4;");




                }catch (Exception e){
                    e.printStackTrace();
                }


            }
        });


        reloadButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                recommendedPassword.setText(new Password().generatePassword());
            }
        });




        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


                saveButton.getScene().getWindow().hide();
                WinManager.loadWindow("/fxml/sinWindow.fxml");



            }
        });




    }
}
