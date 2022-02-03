package controllers;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
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
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import logic.PSystem;
import logic.Pass;
import logic.Password;
import managers.CryptoManager;


public class SPController {

    private ObservableList<Pass>list = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane mainPane;

    @FXML
    private Text versionText;

    @FXML
    private TableView<Pass> mainTable;

    @FXML
    private TableColumn<Pass, Integer> numberPass;

    @FXML
    private TableColumn<Pass, String> namePass;

    @FXML
    private TableColumn<Pass, String> linkPass;

    @FXML
    private TableColumn<Pass, String> datePass;

    @FXML
    private TableColumn<Pass , String> password;

    @FXML
    private TextField saveNamePasswordFiled;

    @FXML
    private TextField saveLinkPasswordFiled;

    @FXML
    private Button saveButton;

    @FXML
    private Button clearButton;

    @FXML
    private TextField selectedLinkPassFiled;

    @FXML
    private TextField selectedNamePassFiled;

    @FXML
    private PasswordField selectedPassFiled;

    @FXML
    private Button exitButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button copyButton;

    @FXML
    private TextField selectedDatePassFiled;

    @FXML
    private TextField searchNamePassFiled;

    @FXML
    private Button searchButton;

    @FXML
    private TextField genPassFiled;

    @FXML
    private Button copyRecPassButton;

    @FXML
    private Button reloadButton;

    @FXML
    private TextField savePasswordFiled;







    @FXML
    void initialize() {



        numberPass.setCellValueFactory(new PropertyValueFactory<Pass, Integer>("N"));
        namePass.setCellValueFactory(new PropertyValueFactory<Pass, String>("NAME"));
        linkPass.setCellValueFactory(new PropertyValueFactory<Pass, String>("LINK"));
        datePass.setCellValueFactory(new PropertyValueFactory<Pass, String>("DATE"));
        password.setCellValueFactory(new PropertyValueFactory<Pass, String>("PASSWORD"));


        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Pass pass = new Pass();

                pass
                        .setNumber(0)
                        .setNamePassword(saveNamePasswordFiled.getText())
                        .setLinkP(saveLinkPasswordFiled.getText())
                        .setDate(PSystem.DATE)
                        .setPass(savePasswordFiled.getText());

                list.add(pass);
                mainTable.setItems(list);
                CryptoManager.getInstance().writeCryptoPass(pass);

            }
        });





    }


}
