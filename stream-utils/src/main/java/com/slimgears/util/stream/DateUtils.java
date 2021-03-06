package com.slimgears.util.stream;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Objects;

@SuppressWarnings("WeakerAccess")
public class DateUtils {
    private final static ThreadLocal<DateFormat> LONG_DATE_TIME_FORMAT = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"));
    private final static ThreadLocal<DateFormat> COMPACT_DATE_FORMAT = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyyMMdd"));

    public static Date round(Date time, Duration period) {
        long rounded = (time.getTime() / period.toMillis()) * period.toMillis();
        return new Date(rounded);
    }

    public static boolean equalsRounded(Date first, Date second, Duration duration) {
        return Objects.equals(round(first, duration), round(second, duration));
    }

    public static Date add(Date date, Duration duration) {
        return new Date(date.getTime() + duration.toMillis());
    }

    public static Date sub(Date date, Duration duration) {
        return new Date(date.getTime() - duration.toMillis());
    }

    public static Duration times(Duration duration, double times) {
        return Duration.ofMillis(Math.round((double)duration.toMillis() * times));
    }

    public static Date parseCompactDate(String dateString) {
        return parseDate(COMPACT_DATE_FORMAT, dateString);
    }

    public static Date parseLongDateTime(String dateString) {
        return parseDate(LONG_DATE_TIME_FORMAT, dateString);
    }

    private static Date parseDate(ThreadLocal<DateFormat> format, String dateString) {
        try {
            return format.get().parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }
}
