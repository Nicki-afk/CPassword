package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class SPController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane mainPane;

    @FXML
    private Text versionText;

    @FXML
    private TextField namePasswordFiled;

    @FXML
    private Button sinUpButton;

    @FXML
    private Button exitButton;

    @FXML
    private PasswordField passwordFiled;

    @FXML
    private TableView<?> table;

    @FXML
    void initialize() {

    }
}
