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
import logic.PSystem;
import managers.CryptoManager;

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
    private TextField codeWordFiled;

    @FXML
    private Button sinUpButton;

    @FXML
    private Button exitButton;

    @FXML
    void initialize() {


        try {

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


                    CryptoManager manager = CryptoManager.getInstance();
                    manager.setData(codeWordFiled.getText());
                    manager.writeCryptoWord();

                    sinUpButton.getScene().getWindow().hide();


                }
            });

        }catch (Exception e){

            System.out.println("ERROR IN THE APP WINDOW (" + e + "):(" + this.getClass() + ")");

        }




    }
}
