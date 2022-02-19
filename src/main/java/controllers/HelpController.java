package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import logic.Password;

public class HelpController {



    private static class KeyLogger{


        private Scene scene;
        private TextField filed;
        private TextArea area;

        private boolean sp , num;

        public KeyLogger(Scene scene , TextField field , TextArea area){

            this.filed = field;
            this.scene = scene;
            this.area = area;


        }

        public void listen(){


            this.scene.setOnKeyReleased(event -> {


                if(event.getCode() == KeyCode.ENTER){

                    if (filed.getText().equals("") || filed.getText() == null){

                        filed.setStyle("-fx-border-color:red; -fx-border-radius:2; -fx-border-width:0.6;");
                    }else{

                        filed.setStyle("");

                        Password password = new Password();

                        String[]rec = new String[20];

                        for(int x = 0; x < 20; x++){

                           rec[x] = password.generatePassword(this.filed.getText() , sp , num) + "\n";

                        }

                        area.setText("");

                        for(String i : rec){

                            area.setText(area.getText() + i);
                        }


                    }

                }


            });

        }

        public void setSettings(boolean sp , boolean num){
            this.sp = sp;
            this.num = num;

        }


    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField simpleFiled;

    @FXML
    private TextArea variantFiled;

    @FXML
    private CheckBox spCheckBox;

    @FXML
    private CheckBox numCheckBox;

    @FXML
    AnchorPane mainPane;

    KeyLogger keyLogger ;

    @FXML
    void initialize() {



        mainPane.setOnMouseEntered(event -> {


            keyLogger = new KeyLogger(mainPane.getScene(),  simpleFiled , variantFiled);
            keyLogger.setSettings(spCheckBox.isSelected(), numCheckBox.isSelected());
            keyLogger.listen();


        });

        spCheckBox.setOnAction(event -> {

            keyLogger.setSettings(spCheckBox.isSelected(), numCheckBox.isSelected());


        });


        numCheckBox.setOnAction(event -> {

            keyLogger.setSettings(spCheckBox.isSelected(), numCheckBox.isSelected());


        });






    }


}
