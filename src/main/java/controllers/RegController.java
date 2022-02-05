package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import logic.PSystem;
import managers.CryptoManager;

public class RegController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Text versionText;

    @FXML
    private Button settingsButton;

    @FXML
    private Button registerButton;

    @FXML
    private Text warningText;

    @FXML
    private TextField codeWordFiled;

    @FXML
    void initialize() {

        versionText.setText(PSystem.getVersion());




        mainPane.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                RegKeyController regKeyController = new RegKeyController(mainPane.getScene());
                regKeyController.listen(codeWordFiled , warningText);

            }
        });


        registerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (codeWordFiled.getText().equals("") || codeWordFiled.getText().length() < 6) {

                    warningText.setVisible(true);
                    codeWordFiled.setStyle("-fx-background-color: #ff9800;");

                } else {

                    CryptoManager cryptoManager = CryptoManager.getInstance();
                    cryptoManager.setData(codeWordFiled.getText());
                    cryptoManager.writeCryptoWord();

                    registerButton.getScene().getWindow().hide();

                }

            }
        });




    }
}class RegKeyController{



    private Scene scene;
    private CryptoManager cryptoManager = CryptoManager.getInstance();


    public RegKeyController(Scene scene ){


        this.scene = scene;

    }

    public void listen(TextField field, Text text){


        this.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                if(event.getCode() == KeyCode.ENTER) {

                    if (field.getText().equals("") || field.getText().length() < 6) {

                        text.setVisible(true);
                        field.setStyle("-fx-background-color: #ff9800;");

                    } else {

                        cryptoManager.setData(field.getText());
                        cryptoManager.writeCryptoWord();

                        scene.getWindow().hide();

                    }
                }
            }
        });




    }







}
