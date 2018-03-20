package com.zac4j.yoda.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zac4j.yoda.data.local.PreferencesHelper;
import com.zac4j.yoda.data.remote.ApiServer;
import io.reactivex.Observable;
import io.reactivex.Single;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

/**
 * Data access manager
 * Created by zac on 16-7-21.
 */

@Singleton public class DataManager {

    private ApiServer mApiServer;
    private PreferencesHelper mPrefsHelper;
    private ObjectMapper mObjectMapper;

    @Inject
    public DataManager(ApiServer apiServer, PreferencesHelper prefsHelper, ObjectMapper mapper) {
        mApiServer = apiServer;
        mPrefsHelper = prefsHelper;
        mObjectMapper = mapper;
    }

    public PreferencesHelper getPrefsHelper() {
        return mPrefsHelper;
    }

    public ApiServer getApiServer() {
        return mApiServer;
    }

    public ObjectMapper getObjectMapper() {
        return mObjectMapper;
    }

    public Observable<Response<Object>> getHomeTimeline(String token, int count, int page) {
        return mApiServer.getTimeline("friends", token, count, page);
    }

    public Single<Response<Object>> getWeiboInfo(String token, long id) {
        return mApiServer.getWeiboInfo(token, id);
    }

    public Single<Response<Object>> getWeiboComments(String token, long id, int page, int count) {
        return mApiServer.getWeiboComments(token, id, page, count);
    }

    public Single<Response<Object>> sendTextWeibo(Map<String, String> weibo) {
        return mApiServer.sendTextWeibo(weibo);
    }

    public Single<Response<Object>> sendPictureWeibo(Map<String, RequestBody> weibo,
        MultipartBody.Part image) {
        return mApiServer.sendPictureWeibo(weibo, image);
    }

    public Single<Response<Object>> repostWeibo(String token, String id, String status,
        int asComment) {
        return mApiServer.repostWeibo(token, id, status, asComment);
    }

    public Single<Response<Object>> commentWeibo(String token, String id, String comment) {
        return mApiServer.commentWeibo(token, id, comment);
    }

    public Single<Response<Object>> unfollowFriend(String token, long uid) {
        return mApiServer.unfollowFriend(token, uid);
    }

    public Single<Response<Object>> search(String token, String keywords, int count, int page) {
        return mApiServer.search(token, keywords, count, page);
    }

    public Single<Response<Object>> getHotTags(String token) {
        return mApiServer.getHotTags(token);
    }

    public Single<Response<Object>> getUserFriends(String token, long uid, int count, int cursor) {
        return mApiServer.getFriends(token, uid, count, cursor);
    }

    public Observable<Response<Object>> getUserTimeline(String token, int count, int page) {
        return mApiServer.getTimeline("user", token, count, page);
    }

    public Single<Response<Object>> getUserProfile(String token, String uid) {
        return mApiServer.getUserProfile(token, uid);
    }

    public Single<Response<Object>> getFollowers(String token, String uid) {
        return mApiServer.getFollowers(token, uid);
    }

    public Single<Response<Object>> getUserComments(String token, int count, int page) {
        return mApiServer.getUserComments(token, count, page);
    }

    public Single<Response<Object>> getLatestComments(String token) {
        return mApiServer.getLatestComments(token);
    }
}
