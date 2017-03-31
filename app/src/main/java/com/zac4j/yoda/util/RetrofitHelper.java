package com.zac4j.yoda.util;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Retrofit Helper
 * Created by zac on 3/30/2017.
 */

public class RetrofitHelper {

  public static RequestBody createPartFromString(String text) {
    return RequestBody.create(MediaType.parse("text/plain"), text);
  }
}
