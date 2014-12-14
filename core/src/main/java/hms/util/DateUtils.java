package hms.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by sadupa on 12/13/14.
 */
public class DateUtils {
    public static Date stringToDateTime(String date){
        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        Date formattedDate = null;
        try {
            formattedDate = format.parse(date);
        } catch (ParseException e) {
            return null;
        }
        return formattedDate;
    }

    public static Date reduceMinutesFromDate(Date date, int minutes){
        date.setTime(date.getTime() - (minutes * 60 * 1000));

        return date;
    }
}
