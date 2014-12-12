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
    public static Date StringToDateTime(String date){
        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        Date formattedDate = null;
        try {
            formattedDate = format.parse(date);
        } catch (ParseException e) {
            return null;
        }
        return formattedDate;
    }
}
