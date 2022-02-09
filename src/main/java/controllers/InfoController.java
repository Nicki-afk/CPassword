package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import managers.WinManager;

public class InfoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button enterButton;

    @FXML
    private CheckBox checkBoxRead;

    @FXML
    void initialize() {


        try {

            checkBoxRead.setOnAction(event -> {


                if (checkBoxRead.isSelected()) {
                    enterButton.setDisable(false);
                }


            });


            enterButton.setOnAction(event -> {


                enterButton.getScene().getWindow().hide();
                WinManager.loadWindow("/fxml/reg.fxml");


            });
        }catch (Exception e){


            System.out.println("ERROR IN THE APP WINDOW (" + e + "):(" + this.getClass() + ")");

        }



    }
}
