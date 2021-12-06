package managers;

import logic.PSystem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class FileManager {



    private String []data;


    public FileManager(String[]data){

        this.data = data;

    }



    public boolean createUser(){

        try {

            File file = new File("user");

            if (!file.exists()) {


                file.mkdir();
                file = new File("user/user.txt");
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






}
