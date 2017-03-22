package com.bugframework.common.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;
/**
 * 日期控件
 * Created by admin on 2017/3/16.
 */
public class DateUtils {
    public static SimpleDateFormat formatd = new SimpleDateFormat("d");
    public static SimpleDateFormat formatM = new SimpleDateFormat("M");
    public static SimpleDateFormat formatyyyy = new SimpleDateFormat("yyyy");
    public static SimpleDateFormat formatYYYYMMdd = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat formatYYYYMMddHHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // 获得当前日期与本周日相差的天数
    private static int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1;         //因为按中国礼拜一作为第一天所以这里减1
        if (dayOfWeek == 1) {
            return 0;
        } else {
            return 1 - dayOfWeek;
        }
    }

    /**
     * 获得一周的全部日期
     *
     * @param weeks:代表那一周，0--当周 -1--上一周  1--下一周
     * @return
     */
    public static String[] getWeekDays(int weeks) {
        int mondayPlus = getMondayPlus();
        String[] days = new String[7];
        for (int i = 0; i < 7; i++) {
            GregorianCalendar currentDate = new GregorianCalendar();
            currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks + i);
            days[i] = formatYYYYMMdd.format(currentDate.getTime());
        }
        return days;
    }

    public static String timestapTostr(Long timestap) {
        if (timestap == null)
            return null;
        try {
            return formatYYYYMMdd.format(timestap);
        } catch (Exception e) {
        }
        return null;
    }

    public static String timestapTostr(Long timestap, String format) {
        if (timestap == null)
            return null;
        try {
            if ("d".equals(format)) {
                return formatd.format(timestap);
            } else if ("M".equals(format)) {
                return formatM.format(timestap);
            } else if ("yyyy".equals(timestap)) {
                return formatyyyy.format(timestap);
            } else {
                return formatYYYYMMdd.format(timestap);
            }
        } catch (Exception e) {
        }
        return null;
    }

    public static Long strTotimstrap(String str) {
        try {
            return formatYYYYMMddHHmmss.parse(str).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
       /* String[] days = getWeekDays(-2);
        for (int i = 0; i < 7; i++) {
            System.out.println(days[i]);
        }*/

        // System.out.println(timestapTostr(new Date().getTime()));
    //    System.out.println(strTotimstrap("2017-3-16 12:00:00"));
        System.out.println(formatM.format(new Date()));
    }
}
