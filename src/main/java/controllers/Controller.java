package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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

    private Password password;

    @FXML
    void initialize() {


        mainPane.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                password = new Password(mainPane.getScene(), passworUser , percentageText);
               // percentageText.setStyle("-fx-fill : rgb(203 , 255 , 24)");
                password.listen();
            }
        });





    }
}
