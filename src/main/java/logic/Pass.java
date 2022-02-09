package logic;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Pass {


    /**

     @date : 09:02:2022
     @author : Niki-afk
     @info :

     This class was created to store data about the password, namely
     the password number, password name, link, and the password itself.
     This class is a POJO class for working with tables in JavaFx




     */



    // POJO OBJECT CLASS


    private int number;
    private String namePassword;
    private String linkP;
    private String date = new SimpleDateFormat("dd:MM:yyyy").format(new Date());
    private String pass;



    public Pass(){}

    public Pass(int number , String namePassword , String linkP , String date ,  String pass){



        this.number = number;
        this.namePassword = namePassword;
        this.linkP = linkP;
        this.date = date;
        this.pass = pass;


    }

    public Pass(String h){

        this.number = Integer.parseInt(h.substring(h.indexOf("<")+1 , h.indexOf(">")));
        this.namePassword = h.substring(h.indexOf("[")+1 , h.indexOf("]")).trim();
        this.linkP = h.substring(h.lastIndexOf("[")+1 , h.lastIndexOf("]")).trim();
        this.date = h.substring(h.indexOf("{")+1 , h.indexOf("}")).trim();
        this.pass = h.substring(h.lastIndexOf("{")+1 , h.lastIndexOf("}")).trim();


    }





    public String format(){

        // This method accepts a formatted data string containing password information.
        // FORMAT : <>[][]{}{}


        return  "<" + this.number +">[" + this.namePassword + "][" + this.linkP + "]{" + this.date + "}{" + this.pass + "}";

    }


    public int getN() {
        return number;
    }

    public Pass setNumber(Integer number) {
        this.number = number;
        return this;
    }

    public String getNAME() {
        return namePassword;
    }

    public Pass setNamePassword(String namePassword) {
        this.namePassword = namePassword;
        return this;
    }

    public String getLINK() {
        return linkP;
    }

    public Pass setLinkP(String linkP) {
        this.linkP = linkP;
        return this;
    }

    public String getDATE() {
        return date;
    }

    public Pass setDate(String date){

        this.date = date;
        return this;

    }

    public String getPASSWORD() {

        // In order not to display the password in the table, this method returns it in a hidden form

        String i = "";
        for (int x = 0; x < this.pass.length(); x++){

            i += "●";
        }


        return i;
    }


    public void copy(){

        // This method copies the password in its original form


        StringSelection stringSelection = new StringSelection(this.pass);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);


    }

    public Pass setPass(String pass) {
        this.pass = pass;
        return this;
    }
}
