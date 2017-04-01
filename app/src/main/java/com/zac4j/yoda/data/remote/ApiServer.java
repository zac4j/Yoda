package com.zac4j.yoda.data.remote;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import io.reactivex.Single;
import java.util.Map;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Web Service
 * Created by zac on 16-7-3.
 */

public interface ApiServer {

  String BASE_URL = "https://api.weibo.com/2/";

  /**
   * Get time line
   *
   * @param scope home: home timeline, user: user timeline
   * @param token user access token
   * @param count pagination count
   * @param page pagination page
   * @return time line
   */
  @GET("statuses/{scope}_timeline.json") Single<Response<Object>> getTimeline(
      @Path("scope") String scope, @Query("access_token") String token, @Query("count") int count,
      @Query("page") int page);

  /**
   * Get user profile
   *
   * @param token user access token
   * @param uid user id
   * @return user profile
   */
  @GET("users/show.json") Single<Response<Object>> getUserProfile(
      @Query("access_token") String token, @Query("uid") String uid);

  /**
   * Get single weibo info
   *
   * @param token user access token
   * @param id weibo id
   * @return single weibo info
   */
  @GET("statuses/show.json") Single<Response<Object>> getWeiboInfo(
      @Query("access_token") String token, @Query("id") long id);

  /**
   * Send Text Weibo
   *
   * @param weibo weibo form contents
   * @return weibo
   */
  @FormUrlEncoded @POST("statuses/update.json") Single<Response<Object>> sendTextWeibo(
      @FieldMap Map<String, String> weibo);

  /**
   * Send Picture Weibo
   * @param weiboMap weibo form data
   * @param image image for weibo
   * @return weibo
   */
  @Multipart @POST("statuses/upload.json") Single<Response<Object>> sendPictureWeibo(
      @PartMap Map<String, RequestBody> weiboMap, @Part MultipartBody.Part image);

  class Factory {
    public static ApiServer create() {
      OkHttpClient.Builder builder = new OkHttpClient.Builder();
      builder.addNetworkInterceptor(new StethoInterceptor());

      Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
          .client(builder.build())
          .addConverterFactory(JacksonConverterFactory.create())
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .build();

      return retrofit.create(ApiServer.class);
    }
  }
}
