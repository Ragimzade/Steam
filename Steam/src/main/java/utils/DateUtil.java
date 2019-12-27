package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class for working with date and time
 */
public class DateUtil {

    /**
     *
     * @return current date
     */
    public static String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    /**
     *
     * @return time stamp
     */
    public static String getTimeStamp() {
        return new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new Date());
    }

}
