package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtil {

    public static String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getTimeStamp() {
        return new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new Date());

    }



}
