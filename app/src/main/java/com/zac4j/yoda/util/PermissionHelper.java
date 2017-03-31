package com.zac4j.yoda.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Permission Helper Utilities.
 * Created by zac on 3/29/2017.
 */

public class PermissionHelper {

  public static boolean hasStoragePermission(Context context) {
    return ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        == PackageManager.PERMISSION_GRANTED;
  }

  public static void requestStoragePermission(Activity activity, int requestCode) {
    ActivityCompat.requestPermissions(activity,
        new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE }, requestCode);
  }
}
