package controllers;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
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
import logic.PSystem;
import logic.Pass;
import logic.Password;
import managers.CryptoManager;
import managers.WinManager;

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


    Pass local = new Pass();

    @FXML
    void initialize() {


        try {


            CryptoManager manager = CryptoManager.getInstance();
            ObservableList<Pass> passwords = manager.getPasswordsList();


            numberColumn.setCellValueFactory(new PropertyValueFactory<Pass, Integer>("N"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<Pass, String>("NAME"));
            linkColumn.setCellValueFactory(new PropertyValueFactory<Pass, String>("LINK"));
            dateColumn.setCellValueFactory(new PropertyValueFactory<Pass, String>("DATE"));
            passwordColumn.setCellValueFactory(new PropertyValueFactory<Pass, String>("PASSWORD"));

            mainTable.setItems(passwords);

            saveDateFiled.setText(PSystem.DATE);
            recomendedFiled.setText(new Password().generatePassword());


            saveButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    if (
                            saveNameFiled.getText().equals("") ||
                                    saveLinkFiled.getText().equals("") ||
                                    saveDateFiled.getText().equals("") ||
                                    savePasswordFiled.getText().equals("")
                    ) {


                        saveNameFiled.setStyle("-fx-border-color : red; -fx-border-radius:4;");
                        saveLinkFiled.setStyle("-fx-border-color : red; -fx-border-radius:4;");
                        saveDateFiled.setStyle("-fx-border-color : red; -fx-border-radius:4;");
                        savePasswordFiled.setStyle("-fx-border-color : red; -fx-border-radius:4;");


                    } else {


                        saveNameFiled.setStyle("-fx-border-color : black; -fx-border-radius:4;");
                        saveLinkFiled.setStyle("-fx-border-color : black; -fx-border-radius:4;");
                        saveDateFiled.setStyle("-fx-border-color : black; -fx-border-radius:4;");
                        savePasswordFiled.setStyle("-fx-border-color : black; -fx-border-radius:4;");


                        Pass pass = new Pass();

                        pass
                                .setNumber(manager.passwordSize())
                                .setNamePassword(saveNameFiled.getText())
                                .setLinkP(saveLinkFiled.getText())
                                .setDate(saveDateFiled.getText())
                                .setPass(savePasswordFiled.getText());

                        manager.writeCryptoPass(pass);
                        passwords.add(pass);


                    }


                }
            });


            mainTable.setOnMouseClicked(event -> {

                try {

                    local = mainTable.getSelectionModel().getSelectedItem();
                    selectedNameFiled.setText(mainTable.getSelectionModel().getSelectedItem().getNAME());
                    selectedLinkFiled.setText(mainTable.getSelectionModel().getSelectedItem().getLINK());
                    selectedDateFiled.setText(mainTable.getSelectionModel().getSelectedItem().getDATE());
                    selectedPasswordFiled.setText(mainTable.getSelectionModel().getSelectedItem().getPASSWORD());
                }catch (Exception e){

                    System.out.println("ERROR IN THE APP WINDOW (" + e + "):(" + this.getClass() + ")");
                }

            });




            copyButton.setOnAction(event -> {

                try {
                    local.copy();
                }catch (Exception e){

                    System.err.println("COPY ERROR (" + e + "):(" + copyButton.getClass() + ")");
                }

            });

            reloadButton.setOnAction(event -> {

                recomendedFiled.setText(new Password().generatePassword());
                recomendedFiled.setStyle("-fx-border-radius:4; -fx-border-color:black;");

            });

            copyRecPasswordFiled.setOnAction(event -> {


                recomendedFiled.setStyle("-fx-border-radius:4; -fx-border-color:#50FF18;");

                StringSelection stringSelection = new StringSelection(recomendedFiled.getText());
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);

            });

            clearButton.setOnAction(event -> {


                saveNameFiled.setText("");
                saveLinkFiled.setText("");
                savePasswordFiled.setText("");


            });




            settingsPasswordButton.setOnAction(event -> {

                WinManager.loadWindow();



            });

        }catch (Exception e){


            System.out.println("ERROR IN THE APP WINDOW (" + e + "):(" + this.getClass() + ")");

        }



    }
}
