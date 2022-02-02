package logic;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PSystem{


    private static final String version = "version-0.0.1.2-28012022";
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd:MM:yyyy");
    public static String DATE = dateFormat.format(new Date());


    public static String getVersion() {
        return version;
    }








}
