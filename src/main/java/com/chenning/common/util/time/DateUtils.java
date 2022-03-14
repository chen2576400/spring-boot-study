package com.chenning.common.util.time;
import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author nchen
 * @version 1.0
 * @date 2021/2/22 15:20
 */
public class DateUtils {
    /**
     * 英文简写（默认）如：2010-12-01
     */
    public static String FORMAT_SHORT = "yyyy-MM-dd";
    /**
     * 英文简写（默认）如：2010-12-01
     */
    public static String FORMAT_DIY = "yyyy/MM/dd";

    /**
     * 英文全称  如：2010-12-01 23:15:06
     */
    public static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";

    /**
     * 精确到毫秒的完整时间    如：yyyy-MM-dd HH:mm:ss.S
     */
    public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";

    /**
     * 中文简写  如：2010年12月01日
     */
    public static String FORMAT_SHORT_CN = "yyyy年MM月dd";

    /**
     * 中文全称  如：2010年12月01日  23时15分06秒
     */
    public static String FORMAT_LONG_CN = "yyyy年MM月dd日  HH时mm分ss秒";

    /**
     * 精确到毫秒的完整中文时间
     */
    public static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";


    /**
     * 获取当前日期
     *
     * @param pattern 格式，默认格式yyyyMMdd
     * @return 20190101
     */
    public static String getCurrentDay(String pattern) {
        LocalDateTime localDateTime = LocalDateTime.now();
        if (StringUtils.isEmpty(pattern)) {
            pattern = "yyyyMMdd";
        }
        return format(localDateTime2Date(localDateTime), pattern);
    }


    /**
     * 解析字符串日期为Date
     *
     * @param dateStr 日期字符串
     * @param pattern 格式
     * @return Date
     */
    public static Date parse(String dateStr, String pattern) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }


    /**
     * 格式化日期为字符串
     *
     * @param date    date
     * @param pattern 格式
     * @return 日期字符串
     */
    public static String format(Date date, String pattern) {

        Instant instant = date.toInstant();

        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());

        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }


    /**
     * LocalDate类型转为Date
     *
     * @param localDate LocalDate object
     * @return Date object
     */
    public static Date localDate2Date(LocalDate localDate) {

        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());

        return Date.from(zonedDateTime.toInstant());
    }


    /**
     * LocalDateTime类型转为Date
     *
     * @param localDateTime LocalDateTime object
     * @return Date object
     */
    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }


    /**
     * @return 返回当天的起始时间
     */
    public static Date getStartTime() {

        LocalDateTime now = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        return localDateTime2Date(now);
    }


    /**
     * @return 返回当天的结束时间
     */
    public static Date getEndTime() {
        LocalDateTime now = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59).withNano(999);
        return localDateTime2Date(now);
    }


    /**
     * @param startTime
     * @param endTime
     * @return 以小时为单位。如：2018-08-08 和 2018-08-07 相差24h
     */
    public static double getDistanceTime(Date startTime, Date endTime) {
        double hour = 0;
        long time1 = startTime.getTime();
        long time2 = endTime.getTime();

        long diff;
        if (time1 < time2) {
            diff = time2 - time1;
        } else {
            diff = time1 - time2;
        }
        hour = (diff / (60 * 60 * 1000));
        return hour;
    }


    /**
     * 2021-04-12
     * 只包含年月日时间字符串转Timestamp
     */
    public static Timestamp getChangTime(String time) {
        Date date = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = formatter.parse(time);
            Timestamp timestamp = new Timestamp(date.getTime());
            return timestamp;
        } catch (ParseException e) {
            return null;
        }

    }


    /**
     * 将字符串时间转换为时间戳
     */
    public static long dateToStamp(String s, String pattern) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = simpleDateFormat.parse(s);
        return date.getTime();
    }


    /**
     * 将时间戳转成字符串 默认yyyy-MM-dd HH:mm:ss
     */
    public static String getDateToString(long time, String pattern) {
        if (StringUtils.isEmpty(pattern)) {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat(pattern);
        return sf.format(d);
    }


    /**
     * +推迟   -提前
     *
     * 日期加减，提供当前日期以及指定的年月日时分秒进行日期加减；
     *
     * @param now    当前数字格式日期
     * @param year   年
     * @param month  月
     * @param day    日
     * @param hour   时
     * @param minute 分
     * @param second 秒
     * @return 经过日期加减处理过的数字日期
     */
    public static long getDate(long now, int year, int month, int day,
                               int hour, int minute, int second) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(now);
        if (second != 0)
            cal.add(Calendar.SECOND, second);
        if (minute != 0)
            cal.add(Calendar.MINUTE, minute);
        if (hour != 0)
            cal.add(Calendar.HOUR, hour);
        if (day != 0)
            cal.add(Calendar.DATE, day);
        if (month != 0)
            cal.add(Calendar.MONTH, month);
        if (year != 0)
            cal.add(Calendar.YEAR, year);

        return cal.getTime().getTime();
    }


    /**
     * @param inputDate 要解析的字符串
     * @return 解析出来的时间戳，如果没有匹配的返回null
     */
    public static Long parseDate(String inputDate) {
        String[] patterns = {//可能出现的时间格式
                "yyyy-MM-dd HH:mm:ss",

                "yyyy-MM-dd HH:mm",

                "yyyy/MM/dd HH:mm:ss",

                "yyyy/MM/dd HH:mm",

                "yyyy年MM月dd日",

                "yyyy-MM-dd",

                "yyyy/MM/dd",

                "yyyyMMdd"

        };

        SimpleDateFormat df = new SimpleDateFormat();

        for (String pattern : patterns) {
            df.applyPattern(pattern);

            df.setLenient(false);//设置解析日期格式是否严格解析日期

            ParsePosition pos = new ParsePosition(0);

            Date date = df.parse(inputDate, pos);
            if (date != null) {
                return date.getTime();
            }
        }
        return null;

    }


}
