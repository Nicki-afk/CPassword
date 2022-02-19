package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import logic.PSystem;
import managers.CryptoManager;
import managers.WinManager;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text versionText;

    @FXML
    private PasswordField codeWordFiled;

    @FXML
    private Button quationButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Button inputButton;

    @FXML
    private Button registerButton;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Text warningText;


    CryptoManager cryptoManager = CryptoManager.getInstance();

    @FXML
    void initialize() {

        try {


            versionText.setText(PSystem.getVersion());

//        cryptoManager.init();


            if (cryptoManager.getRecords().size() < 3) {

                settingsButton.setDisable(true);
                inputButton.setDisable(true);
                codeWordFiled.setDisable(true);
                warningText.setVisible(true);

            } else {

                registerButton.setDisable(true);

            }


            mainPane.setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {

                    KeyController keyController = new KeyController(mainPane.getScene(), codeWordFiled);
                    keyController.listen();

                }
            });

            registerButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {


                    registerButton.getScene().getWindow().hide();
                    WinManager.loadWindow("/fxml/reg.fxml");


                }
            });

            inputButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    if (!cryptoManager.isCodeWord(codeWordFiled.getText())) {

                        codeWordFiled.setStyle("-fx-border-radius : 2; -fx-border-color: red; -fx-border-width: 1;");

                    } else {


                        inputButton.getScene().getWindow().hide();
                        WinManager.loadWindow("/fxml/loadWindow.fxml");
                    }

                }
            });

        }catch (Exception e){


            System.out.println("ERROR IN THE APP WINDOW (" + e + "):(" + this.getClass() + ")");
        }



    }
}class KeyController{




    private Scene scene;
    private PasswordField field;



    public KeyController(Scene scene , PasswordField field){


        this.field = field;
        this.scene = scene;

    }

    public void listen(){


        this.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {


                if(event.getCode() == KeyCode.ENTER){


                    CryptoManager manager = CryptoManager.getInstance();

                    if(!manager.isCodeWord(field.getText())){

                        field.setStyle("-fx-border-radius : 2; -fx-border-color: red; -fx-border-width: 1;");

                    }else{

                        scene.getWindow().hide();
                        WinManager.loadWindow("/fxml/loadWindow.fxml");
                    }



                }



            }
        });


    }




}
