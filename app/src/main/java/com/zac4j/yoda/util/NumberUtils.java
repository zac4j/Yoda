package com.zac4j.yoda.util;

/**
 * Utilities for number display
 * Created by zac on 17-5-11.
 */

public class NumberUtils {

  private static final long MILLION = 1000000;
  private static final long THOUSAND = 1000;

  public static String getRelativeNumberSpanString(long number) {
    if (number > MILLION) {
      return "100 w+";
    } else if (number > MILLION / 10) {
      return "100 k+";
    } else if (number > THOUSAND * 10) {
      return "10 k+";
    } else {
      return String.valueOf(number);
    }
  }
}
