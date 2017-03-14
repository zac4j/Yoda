package com.zac4j.yoda.utils;

import android.util.Log;

/**
 * Logger
 * Created by zac on 3/14/2017.
 */

public class Logger {

  private static final boolean BUILD_STATE = true;

  public static void d(String tag, String msg) {
    if (BUILD_STATE) {
      Log.d(tag, msg);
    }
  }

  public static void e(String tag, String methodName, Throwable throwable) {
    if (BUILD_STATE) {
      Log.e(tag, "method name: " + methodName, throwable);
    }
  }
}
