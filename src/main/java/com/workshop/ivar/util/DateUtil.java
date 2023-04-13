package com.workshop.ivar.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author cuongnd9
 * @date 31/03/2023
 * @project ivar
 * @package com.workshop.ivar.util
 */
public class DateUtil {

    private DateUtil() {

    }

    private static LocalDateTime convertDateToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    private static Date convertLocalDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date getCurrentDateTime() {
        return convertLocalDateTimeToDate(LocalDateTime.now());
    }

    public static Date addTimeMillis(Date date, Long time) {
        LocalDateTime dateTime = convertDateToLocalDateTime(date);
        return convertLocalDateTimeToDate(dateTime.plus(time, ChronoUnit.MILLIS));
    }

}
