package logic;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import managers.AnimManager;


public class Password extends CPassword {


    private String pass;
    private Scene scene;
    private TextField filed;
    private int percentage;
    private Text text;
    private AnimManager animManager;

    public Password(Scene scene , TextField field   , Text text){
        super();
        this.scene = scene;
        this.text = text;
        this.filed = field;

    }

    public Password(){}


    public void listen(){

        this.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                if(event.getCode() == KeyCode.ENTER){
                    setPass(filed.getText());
                    percentage = howStrongIsThePassword();
                    animManager = new AnimManager(text , percentage);
                    animManager.start();

                }


            }
        });
    }



    @Override
    public String generatePassword() {
        return gen.generatePassword(10, splCharRule, lowerCaseRule,
                upperCaseRule, digitRule);
    }

    @Override
    public int howStrongIsThePassword() {
        return super.howStrongIsThePassword();
    }


    public int getPercentage() {
        return percentage;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
        setPassword(pass);
    }
}
