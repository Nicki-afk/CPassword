package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import logic.Pass;
import managers.CryptoManager;

public class SaveController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Pass> mainTable;

    @FXML
    private TableColumn<Pass, Integer> numberColumn;

    @FXML
    private TableColumn<Pass, String> nameColumn;

    @FXML
    private TableColumn<Pass, String> linkColumn;

    @FXML
    private TableColumn<Pass, String> dateColumn;

    @FXML
    private TableColumn<Pass, String> passwordColumn;

    @FXML
    private TextField selectedNameFiled;

    @FXML
    private TextField selectedLinkFiled;

    @FXML
    private TextField selectedDateFiled;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField saveNameFiled;

    @FXML
    private TextField saveLinkFiled;

    @FXML
    private TextField saveDateFiled;

    @FXML
    private TextField savePasswordFiled;

    @FXML
    private Button copyButton;

    @FXML
    private Button searchButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button clearButton;

    @FXML
    private TextField recomendedFiled;

    @FXML
    private Button reloadButton;

    @FXML
    private Button copyRecPasswordFiled;

    @FXML
    private Button settingsPasswordButton;

    @FXML
    private PasswordField selectedPasswordFiled;

    @FXML
    void initialize() {


        CryptoManager manager = CryptoManager.getInstance();
        ObservableList<Pass>passwords = manager.getPasswordsList();


        numberColumn.setCellValueFactory(new PropertyValueFactory<Pass, Integer>("N"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Pass , String>("NAME"));
        linkColumn.setCellValueFactory(new PropertyValueFactory<Pass , String>("LINK"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Pass , String>("DATE"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<Pass , String>("PASSWORD"));

        mainTable.setItems(passwords);


        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if(
                        saveNameFiled.getText().equals("") ||
                        saveLinkFiled.getText().equals("") ||
                        saveDateFiled.getText().equals("") ||
                        savePasswordFiled.getText().equals("")
                ){


                            saveNameFiled.setStyle("-fx-border-color : red; -fx-border-radius:4;");
                            saveLinkFiled.setStyle("-fx-border-color : red; -fx-border-radius:4;");
                            saveDateFiled.setStyle("-fx-border-color : red; -fx-border-radius:4;");
                            savePasswordFiled.setStyle("-fx-border-color : red; -fx-border-radius:4;");



                }else{


                    saveNameFiled.setStyle("-fx-border-color : black; -fx-border-radius:4;");
                    saveLinkFiled.setStyle("-fx-border-color : black; -fx-border-radius:4;");
                    saveDateFiled.setStyle("-fx-border-color : black; -fx-border-radius:4;");
                    savePasswordFiled.setStyle("-fx-border-color : black; -fx-border-radius:4;");

                }




            }
        });










    }
}
