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
import logic.Password;


public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane mainPane;

    @FXML
    private TextField passworUser;

    @FXML
    private TextField recommendedPassword;

    @FXML
    private Button CopyButton;

    @FXML
    private Button exitButton;

    @FXML
    private Text percentageText;

    @FXML
    private Text versionText;


    private Password password;

    @FXML
    void initialize() {

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


        CopyButton.setOnAction(new EventHandler<ActionEvent>() {
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





    }
}
