package com.zac4j.yoda.data.remote;

import android.content.Context;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * HttpClient
 * Created by zac on 4/5/2017.
 */

public class HttpClient {

  private static final int CACHE_SIZE_BYTES = 1024 * 1024 * 2;

  private Context mContext;
  private OkHttpClient mHttpClient;

  public HttpClient(Context context) {
    mContext = context;
  }

  public OkHttpClient getHttpClient() {
    if (mHttpClient == null) {
      OkHttpClient.Builder builder = new OkHttpClient.Builder();
      builder.cache(new Cache(mContext.getCacheDir(), CACHE_SIZE_BYTES));
      builder.addNetworkInterceptor(new StethoInterceptor());
      mHttpClient = builder.build();
    }
    return mHttpClient;
  }
}
