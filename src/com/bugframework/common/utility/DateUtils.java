package com.bugframework.common.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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

    public static String getNowDay() {
        return formatYYYYMMdd.format(new Date());
    }

    // 获得当前日期与本周日相差的天数
    public static int getMondayPlus() {
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
            } else if ("yyyy".equals(format)) {
                return formatyyyy.format(timestap);
            } else if ("all".equals(format)) {
                return formatYYYYMMddHHmmss.format(timestap);
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

    /**
     * 当天开始时间戳
     *
     * @return 时间戳
     */
    public static Long getStartTime() {
       /* Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR, -1);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 1);
        return todayStart.getTime().getTime();
*/
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        //时分秒（毫秒数）
        long millisecond = hour*60*60*1000 + minute*60*1000 + second*1000;
        //凌晨00:00:00
        cal.setTimeInMillis(cal.getTimeInMillis()-millisecond);
        return cal.getTime().getTime();


    }

    /**
     * 当天结束时间
     *
     * @return 时间戳
     */
    public static Long getEndTime() {
        /*Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime().getTime();*/
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        //时分秒（毫秒数）
        long millisecond = hour*60*60*1000 + minute*60*1000 + second*1000;
        //凌晨00:00:00
        cal.setTimeInMillis(cal.getTimeInMillis()-millisecond);
        cal.setTimeInMillis(cal.getTimeInMillis()+23*60*60*1000 + 59*60*1000 + 59*1000);
        return cal.getTime().getTime();

    }

    public static void main(String[] args) {
       /* String[] days = getWeekDays(-2);
        for (int i = 0; i < 7; i++) {
            System.out.println(days[i]);
        }*/

        // System.out.println(timestapTostr(new Date().getTime()));
        //    System.out.println(strTotimstrap("2017-3-16 12:00:00"));
/*        System.out.println(getMondayPlus());
        System.out.println(timestapTostr(1490968800000L,"all")+"    "+timestapTostr(1490974200000L,"all")+"  "+timestapTostr(getEndTime(),"all"));*/
        System.out.println("start = "+DateUtils.getStartTime()+"    end = "+ DateUtils.getEndTime());
    }
}
