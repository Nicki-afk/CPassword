package logic;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Pass {


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


    public String format(){


        return "[" + this.namePassword + "][" + this.linkP + "]{" + this.date + "}{" + this.pass + "}";

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
        return pass;
    }

    public Pass setPass(String pass) {
        this.pass = pass;
        return this;
    }
}
