package mng.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author oac
 */
public class DateUtil {
    private final static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final static DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss SSS");

    public static String formatTime(LocalDateTime date, String fmt){
        return DateTimeFormatter.ofPattern(fmt).format(date);
    }

    public static String formatDate(LocalDate date, String fmt){
        return DateTimeFormatter.ofPattern(fmt).format(date);
    }

    public static void main(String[] args) {
        LocalDateTime localDate = LocalDateTime.of(2019,1,1,0,0);
        System.out.println(formatTime(localDate,"yyyy-MM-dd hh:mm:ss SSS"));
    }
}
