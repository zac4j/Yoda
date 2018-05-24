package com.zac4j.yoda.data.remote;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.zac4j.yoda.AppExecutors;
import com.zac4j.yoda.data.model.EmotionEntry;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zac on 2018/5/22.
 * Description:
 */
public class EmotionNetworkDataSource {
    private static final String TAG = EmotionNetworkDataSource.class.getSimpleName();
    private static final Object LOCK = new Object();
    private Context mContext;
    private AppExecutors mAppExecutors;
    private static EmotionNetworkDataSource sInstance;
    private MutableLiveData<List<EmotionEntry>> mEmotionList;

    private EmotionNetworkDataSource(Context context, AppExecutors executors) {
        mContext = context;
        mAppExecutors = executors;
        mEmotionList = new MutableLiveData<>();
    }

    public static EmotionNetworkDataSource getInstance(Context context, AppExecutors executors) {
        Log.d(TAG, "getInstance: ");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new EmotionNetworkDataSource(context.getApplicationContext(), executors);
            }
        }
        return sInstance;
    }

    public LiveData<List<EmotionEntry>> getEmotionList() {
        return mEmotionList;
    }

    public void fetchEmotions() {
        mAppExecutors.networkIO().execute(() -> {
            String token = AccessTokenKeeper.readAccessToken(mContext).getToken();
            String url = "https://api.weibo.com/2/emotions.json?access_token=" + token;
            Request request = new Request.Builder()
                .get()
                .url(url)
                .build();

            OkHttpClient client = new OkHttpClient();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String json = Objects.requireNonNull(response.body()).string();
                        ObjectMapper mapper = new ObjectMapper();
                        List<EmotionEntry> emotionEntryList = mapper.readValue(json, new TypeReference<List<EmotionEntry>>(){});
                        mEmotionList.postValue(emotionEntryList);
                    }
                }
            });
        });
    }
}
