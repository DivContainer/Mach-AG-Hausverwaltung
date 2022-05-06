package de.machag.HouseService;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Util {

    public static Date addHoursToDate(Date date, int hours) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.HOUR_OF_DAY, hours);
        return c.getTime();
    }

}
