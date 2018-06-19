package com.zac4j.yoda;

import android.content.Context;
import com.zac4j.yoda.data.EmotionRepository;
import com.zac4j.yoda.data.local.EmotionDatabase;
import com.zac4j.yoda.data.remote.EmotionNetworkDataSource;
import com.zac4j.yoda.ui.main.MainViewModelFactory;

/**
 * Created by zac on 2018/6/19.
 * Description:Provides static methods to inject the various classes needed for Weibo.
 */
public class InjectorHelper {

    public static EmotionRepository provideRepository(Context context) {
        EmotionDatabase database = EmotionDatabase.getInstance(context);
        AppExecutors executors = AppExecutors.getInstance();
        EmotionNetworkDataSource dataSource =
            EmotionNetworkDataSource.getInstance(context.getApplicationContext(), executors);
        return EmotionRepository.getInstance(database.emotionDao(), dataSource, executors);
    }

    public static MainViewModelFactory provideMainActivityViewModelFactory(Context context) {
        EmotionRepository repository = provideRepository(context.getApplicationContext());
        return new MainViewModelFactory(repository);
    }
}
