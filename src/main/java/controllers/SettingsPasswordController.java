package controllers;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import logic.Password;

public class SettingsPasswordController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Tab generateTab;

    @FXML
    private TextField genTextFiled;

    @FXML
    private CheckBox spCheckBox;

    @FXML
    private CheckBox numCheckBox;

    @FXML
    private Slider slider;

    @FXML
    private TextField percentageFiled;

    @FXML
    private Button copyButton;

    @FXML
    private Button reloadButton;

    @FXML
    private Tab passwordTab;

    @FXML
    private TextField passTextFiled;

    @FXML
    private TextField recPassFiled;

    @FXML
    private Button pReloadButton;

    @FXML
    private Button pCopyButton;

    @FXML
    private CheckBox pSpCheckBox;

    @FXML
    private CheckBox pNumCheckBox;

    @FXML
    void initialize() {


        Password password = new Password();



       slider.setOnMouseDragged(event -> {

           percentageFiled.setText("" + Math.round(slider.getValue()));



       });


       genTextFiled.setText(password.generatePassword(spCheckBox.isSelected(), numCheckBox.isSelected(), Integer.parseInt(percentageFiled.getText())));





       reloadButton.setOnAction(event -> {


           genTextFiled.setText(password.generatePassword(spCheckBox.isSelected(), numCheckBox.isSelected(), Integer.parseInt(percentageFiled.getText())));



       });


       copyButton.setOnAction(event -> {

           StringSelection stringSelection = new StringSelection(genTextFiled.getText());
           Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
           clipboard.setContents(stringSelection, null);

       });


       pReloadButton.setOnAction(event -> {


           if(passTextFiled.getText().equals("") || passTextFiled.getText() == null){

               passTextFiled.setStyle("-fx-border-color:red; -fx-border-radius:2; -fx-border-width:0.6;");
               recPassFiled.setStyle("-fx-border-color:red; -fx-border-radius:2; -fx-border-width:0.6;");

           }else{

               passTextFiled.setStyle("");
               recPassFiled.setStyle("");

               recPassFiled.setText(password.generatePassword(passTextFiled.getText() , pSpCheckBox.isSelected(), pNumCheckBox.isSelected()));



           }



       });



       pCopyButton.setOnAction(event -> {


           StringSelection stringSelection = new StringSelection(recPassFiled.getText());
           Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
           clipboard.setContents(stringSelection, null);



           recPassFiled.setStyle("-fx-border-color:green; -fx-border-radius:2; -fx-border-width:0.6;");



       });


    }
}
