package top.ks.common.base;


import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.text.SimpleDateFormat;

public class WhatTime {

    /**
     * Dates  those have not EXACTLY 24 hours ?
     **/
    public static void testDayTime(TimeZone timeZone) {

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println("Time Zone is " + timeZone.getDisplayName() + " " + timeZone.getID());

        Calendar start = Calendar.getInstance(timeZone);
        start.setTime(new Date(0));//UTC 1970-01-01

        System.out.println("start=" + fmt.format(start.getTime()));

        long end = Calendar.getInstance(timeZone).getTimeInMillis();//current time

        boolean find = false;
        for (long i = start.getTimeInMillis(); i < end; i = start.getTimeInMillis()) {
            start.add(Calendar.DATE, 1); //add one day

            if ((start.getTimeInMillis() - i) % (24 * 3600 * 1000L) != 0) {
                find = true;
                System.out.println("from " + fmt.format(new Date(i)) +
                        "to " + fmt.format(start.getTime()) +
                        " has " + (start.getTimeInMillis() - i) + "ms" +
                        "[" + (start.getTimeInMillis() - i) / (3600 * 1000L) + "hours]");
            }
        }
        if (!find) {
            System.out.println("Every day is ok.");
        }
    }

    public static void main(String argv[]) throws Exception {

        TimeZone timeZone = TimeZone.getDefault();
        WhatTime.testDayTime(timeZone);

        System.out.println("----------------------------------------------------------------");

        timeZone = TimeZone.getTimeZone("US");
        WhatTime.testDayTime(timeZone);
        System.out.println(isDST(ZonedDateTime.of(1987,5,3,7,59,0,0, ZoneId.systemDefault())));
    }
    public static boolean isDST(ZonedDateTime t) {
        return t.getZone().getRules().isDaylightSavings(t.toInstant());
    }

}
