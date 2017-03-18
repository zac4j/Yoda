package com.zac4j.yoda.data.remote;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.zac4j.yoda.data.model.Timeline;
import io.reactivex.Single;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Web Service
 * Created by zac on 16-7-3.
 */

public interface ApiServer {

  String BASE_URL = "https://api.weibo.com/2/";

  @GET("statuses/{scope}_timeline.json") Single<Timeline> getTimeline(
      @Path("scope") String scope, @Query("access_token") String token,
      @Query("count") int count, @Query("page") int page);

  class Factory {
    public static ApiServer create() {
      OkHttpClient.Builder builder = new OkHttpClient.Builder();
      builder.addNetworkInterceptor(new StethoInterceptor());

      Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
          .client(builder.build())
          .addConverterFactory(GsonConverterFactory.create())
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .build();

      return retrofit.create(ApiServer.class);
    }
  }
}
