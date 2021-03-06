package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import logic.PSystem;
import managers.CryptoManager;
import managers.WinManager;

public class LoadWindow {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox mainPane;


    @FXML
    private Text versionText;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Text loadText;

    @FXML
    private Button enterButton;

    @FXML
    private ImageView loadImg;


    @FXML
    void initialize() {


        versionText.setText(PSystem.getVersion());



        mainPane.setOnMouseEntered(event -> {



            KeyLoger keyLoger = new KeyLoger(mainPane.getScene() , progressBar.getProgress());
            keyLoger.listen();



        });


        Loader loader = new Loader(progressBar);
        loader.setActions(enterButton , loadText , loadImg);
        loader.start();


        enterButton.setOnAction(event -> {


            enterButton.getScene().getWindow().hide();
            Platform.runLater(() ->{


                WinManager.loadWindow("/fxml/simpleDisign.fxml");

            });


        });







    }
}class Loader extends Thread{



    private ProgressBar bar;

    private Button button;
    private Text text;
    private ImageView view;



    public Loader(ProgressBar bar){


        this.bar = bar;

    }

    public void load(){

        try{



            double x = 0.0;


            while (x < 1){

                if(CryptoManager.getInstance().transform()){

                    x+= 0.50;

                }

                x+=0.50;
                bar.setProgress(x);

            }

            this.button.setDisable(false);
            this.view.setVisible(false);
            this.text.setText("PRESS ENTER TO CONTINUE");
            this.text.setLayoutX(209);



        }catch (Exception e){

            System.out.println("ERROR IN THE APP WINDOW (" + e + "):(" + this.getClass() + ")");

        }


    }

    public void setActions(Button button , Text text , ImageView view){
        this.button = button;
        this.text = text;
        this.view = view;


    }


    @Override
    public void run() {
        load();
    }


}class KeyLoger{


    private Scene scene;
    private double progress;


    public KeyLoger(Scene scene , double progress){

        this.scene = scene;
        this.progress = progress;

    }


    public void listen(){

        this.scene.setOnKeyPressed(event -> {

            if(event.getCode() == KeyCode.ENTER && this.progress == 1){


                this.scene.getWindow().hide();

                Platform.runLater(() ->{


                    WinManager.loadWindow("/fxml/simpleDisign.fxml");

                });


            }


        });

    }



}
