package logic;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import managers.AnimManager;


public class Password extends CPassword {


    /**

     @date : 09:02:2022
     @author : Niki-afk
     @info  :
     This class is a descendant of the abstract class Cpassword . The class
     uses the data of the parent class for password generation (may be rewritten)





     */








    private String pass;
    private Scene scene;
    private TextField filed;
    private int percentage;
    private Text text;
    private AnimManager animManager;



    public Password(){}





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
