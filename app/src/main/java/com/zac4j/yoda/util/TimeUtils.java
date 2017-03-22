package com.zac4j.yoda.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Time Utilities
 * Created by zac on 3/22/2017.
 */

public class TimeUtils {

  /**
   * Get 2017/08/08 style string.
   * @param dateStr date string for format.
   * @param pattern date format.
   * @return formatted date string.
   */
  public static String getYmd(String dateStr, String pattern) {
    DateFormat formatter = new SimpleDateFormat(pattern, Locale.ENGLISH);
    Date date = null;
    try {
      date = formatter.parse(dateStr);
    } catch (ParseException e) {
      e.printStackTrace();
    }

    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    if (date == null) {
      return "";
    }
    return cal.get(Calendar.YEAR) + "/" + cal.get(Calendar.MONTH) + "/" + cal.get(Calendar.DATE);
  }
}
