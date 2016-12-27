package com.example.simplerestaurantfinder.utils;

import org.joda.time.DateTime;

import java.sql.Time;
import java.util.Date;

/**
 * Created by BERM-PC on 27/12/2559.
 */
public class DateTimeUtil {

    public static Time convertDateTimeToSqlTime(DateTime dateTime){
        Time time = null;
        if(dateTime != null){
            Date date = dateTime.toDate();
            time = new Time(date.getTime());
        }
        return  time;
    }
}
