package com.xxx.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {

    public static Long calcBetweenDays(String a, String b) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar_a = Calendar.getInstance();
        Calendar calendar_b = Calendar.getInstance();
        try {
            Date date_a = simpleDateFormat.parse(a);
            Date date_b = simpleDateFormat.parse(b);
            calendar_a.setTime(date_a);
            calendar_b.setTime(date_b);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long time_a = calendar_a.getTimeInMillis();
        long time_b = calendar_b.getTimeInMillis();
        return (time_b - time_a) / (1000 * 3600 * 24);
    }


    public static String getCurrentTime(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }



    public static long getTime(String oldTime,String newTime) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long NTime =df.parse(newTime).getTime();

        long OTime = df.parse(oldTime).getTime();
        long diff=(NTime-OTime);
        return diff;
    }


    public static void main(String[] args) throws ParseException {


        Long aLong = TimeUtil.calcBetweenDays("2022-10-12 09:59:51", "2022-10-15 10:59:51");
        System.out.println(aLong);
    }

    public static boolean compareTime(String time1,String time2) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = df.parse(df.format(new Date()));
        Date begin = df.parse(time1);
        Date end = df.parse(time2);
        Calendar nowTime = Calendar.getInstance();
        nowTime.setTime(now);
        Calendar beginTime = Calendar.getInstance();
        beginTime.setTime(begin);
        Calendar endTime = Calendar.getInstance();
        endTime.setTime(end);
        return nowTime.before(endTime) && nowTime.after(beginTime);
    }
}
