package com.zac4j.yoda.data.remote;

import android.content.Context;
import io.reactivex.Single;
import java.util.Map;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.Field;
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
   * 转发微博
   *
   * @param token access token
   * @param id weibo id
   * @param status status content
   * @param isComment 转发并评论 0：否、1：评论给当前微博、2：评论给原微博、3：都评论，默认为0 。
   * @return weibo content
   */
  @FormUrlEncoded @POST("statuses/repost.json") Single<Response<Object>> repostWeibo(
      @Field("access_token") String token, @Field("id") String id, @Field("status") String status,
      @Field("is_comment") int isComment);

  /**
   * 评论微博
   *
   * @param token access token
   * @param id weibo id
   * @param comment comment content
   * @return weibo content
   */
  @FormUrlEncoded @POST("comments/create.json") Single<Response<Object>> commentWeibo(
      @Field("access_token") String token, @Field("id") String id, @Field("comment") String comment);

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
   *
   * @param weiboMap weibo form data
   * @param image image for weibo
   * @return weibo
   */
  @Multipart @POST("statuses/upload.json") Single<Response<Object>> sendPictureWeibo(
      @PartMap Map<String, RequestBody> weiboMap, @Part MultipartBody.Part image);

  /**
   * Get User Friends
   *
   * @param token user access token
   * @param uid user id
   * @param count get friends count each request
   * @param cursor get friends number cursor
   * @return friends
   */
  @GET("friendships/friends.json") Single<Response<Object>> getFriends(
      @Query("access_token") String token, @Query("uid") long uid, @Query("count") int count,
      @Query("cursor") int cursor);

  /**
   * Unfollow friends
   *
   * @param token access token
   * @param uid friend id
   * @return user info
   */
  @GET("friendships/destroy.json") Single<Response<Object>> unfollowFriend(
      @Query("access_token") String token, @Query("uid") long uid);

  /**
   * Search proper topic weibo
   *
   * @param token user access token
   * @param keywords keyword for search
   * @param count weibo result count for each page
   * @param page weibo result page
   * @return weibo search result
   */
  @GET("search/topics.json") Single<Response<Object>> search(@Query("access_token") String token,
      @Query("q") String keywords, @Query("count") int count, @Query("page") int page);

  /**
   * Get server suggest tags
   *
   * @param token use access token
   * @return suggest tags
   */
  @GET("tags/suggestions.json") Single<Response<Object>> getHotTags(
      @Query("access_token") String token);

  /**
   * Get @user comments.
   *
   * @param token user access token
   * @param count pagination count
   * @param page pagination page
   * @return @user comments
   */
  @GET("comments/mentions.json") Single<Response<Object>> getComments(
      @Query("access_token") String token, @Query("count") int count, @Query("page") int page);

  /**
   * Get lastest @user comment.
   *
   * @param token user access token
   * @return @user lastest comment
   */
  @GET("comments/to_me.json") Single<Response<Object>> getLatestComments(
      @Query("access_token") String token);

  /**
   * Get user followers
   *
   * @param token user access token
   * @param uid user id
   * @return user followers
   */
  @GET("friendships/followers.json") Single<Response<Object>> getFollowers(
      @Query("access_token") String token, @Query("uid") String uid);

  class Factory {
    public static ApiServer create(Context context) {

      Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
          .client(new HttpClient(context).getHttpClient())
          .addConverterFactory(JacksonConverterFactory.create())
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .build();

      return retrofit.create(ApiServer.class);
    }
  }
}
