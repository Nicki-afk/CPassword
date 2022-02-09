package logic;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PSystem{


    /**

     @date : 09:02:2022
     @author : Niki-afk
     @info :

     This class is a system class of the program. This class
     provides data such as the date and version of the program. It uses
     mostly static methods to make it easier to use the class in
     different parts of the program.



     */


    private static final String version = "version-0.0.1.5-09022022";
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd:MM:yyyy");
    public static String DATE = dateFormat.format(new Date());


    public static String getVersion() {
        return version;
    }








}
