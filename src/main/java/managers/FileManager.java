package managers;

import logic.PSystem;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {



    private String []data;


    public FileManager(String[]data){

        this.data = data;

    }

    public FileManager(){}



    public boolean createUser(){

        try {

            File file = new File("user");

            if (!file.exists()) {


                file.mkdir();
                file = new File("user/user.txt");
                file.createNewFile();
                file = new File("user/word.txt");
                file.createNewFile();
            }else{
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }



        try(
                BufferedWriter writer = new BufferedWriter(new FileWriter("user/user.txt"))

                ){

            writer.write("NAME      : " + data[0] + "\n");
            writer.write("FIRSTNAME : " + data[1] + "\n");
            writer.write("E-MAIL    : " + data[2] + "\n");
            writer.write("DATE      : " + PSystem.DATE + "\n");
            writer.flush();
            return true;

        }catch (Exception e){

            e.printStackTrace();

        }

        return false;

    }


    private String format(String s){

        return s.substring(s.indexOf("{")+1 , s.indexOf("}")).trim();

    }


    public String read(){

        try(
                BufferedReader reader = new BufferedReader(new FileReader("user/word.txt"))

                ){


            String x = reader.readLine();
            return format(x);


        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public boolean write(byte[]arr){


        try(

                BufferedWriter writer = new BufferedWriter(new FileWriter("user/word.txt"))


                ){

            writer.write("CODE_WORD : { ");

            for(byte i : arr){

                writer.write(i + "/");

            }

            writer.write(" }");
            writer.flush();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;

    }


    public boolean filesExists(){


        if(!new File("user/user.txt").exists() &&! new File("user/word.txt").exists()){
            return false;

        }else {

            return true;
        }

    }

    public boolean writePassword(String[]data){


        File file = new File("user/passwords.txt");
        ArrayList<String>arr = new ArrayList<>();


        try{


            if(!file.exists()){
                file.createNewFile();
            }else{

                try(
                        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                        BufferedReader reader = new BufferedReader(new FileReader(file))

                        ){








                }catch (Exception e){

                    e.printStackTrace();
                }






            }







        }catch (Exception e){
            e.printStackTrace();
        }



        return false;
    }




}
