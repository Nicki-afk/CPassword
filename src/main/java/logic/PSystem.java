package logic;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PSystem {


    private static final String version = "version-0.0.0.1-16012022";
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd:mm:yy");
    public static String DATE = dateFormat.format(new Date());

    public static String getVersion() {
        return version;
    }


}
