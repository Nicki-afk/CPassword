package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import logic.PSystem;
import managers.CryptoManager;
import managers.WinManager;
import sun.dc.pr.PRError;

public class LoadWindow {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private Text versionText;


    @FXML
    void initialize() {


        versionText.setText(PSystem.getVersion());













    }




}class Loader extends Thread{


    private ProgressBar bar;
    private ImageView view;


    public Loader(ProgressBar bar , ImageView view){

        this.bar = bar;
        this.view = view;


    }





    public void load(){








    }


    @Override
    public void run() {
       load();
    }
}
