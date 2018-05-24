package com.zac4j.yoda.data;

import android.arch.lifecycle.LiveData;
import android.util.Log;
import com.zac4j.yoda.AppExecutors;
import com.zac4j.yoda.data.local.EmotionDao;
import com.zac4j.yoda.data.model.EmotionEntry;
import com.zac4j.yoda.data.remote.EmotionNetworkDataSource;
import java.util.List;

/**
 * Created by zac on 2018/5/22.
 * Description:Emotion repository
 */
public class EmotionRepository {
    private static final String TAG = EmotionRepository.class.getSimpleName();

    private static final Object LOCK = new Object();
    private static EmotionRepository sInstance;
    private final EmotionDao mEmotionDao;
    private final EmotionNetworkDataSource mDataSource;
    private final AppExecutors mAppExecutors;

    private boolean mInitialized;

    private EmotionRepository(EmotionDao emotionDao, EmotionNetworkDataSource dataSource,
        AppExecutors executors) {
        mEmotionDao = emotionDao;
        mDataSource = dataSource;
        mAppExecutors = executors;

        LiveData<List<EmotionEntry>> emotionList = mDataSource.getEmotionList();
        emotionList.observeForever(emotionEntries -> mAppExecutors.diskIO().execute(() -> {
            // delete old data
            mEmotionDao.bulkInsert(emotionEntries);
        }));
    }

    public synchronized static EmotionRepository getInstance(EmotionDao emotionDao,
        EmotionNetworkDataSource dataSource, AppExecutors executors) {
        Log.d(TAG, "Getting the repository");
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = new EmotionRepository(emotionDao, dataSource, executors);
                Log.d(TAG, "Made new repository");
            }
        }
        return sInstance;
    }

    public LiveData<List<EmotionEntry>> getEmotionList() {
        initializeData();
        return mEmotionDao.getEmotions();
    }

    public LiveData<EmotionEntry> getEmotionByPhrase(String phrase) {
        initializeData();
        return mEmotionDao.getEmotionByPhrase(phrase);
    }

    private void initializeData() {
        if (mInitialized) return;
        mInitialized = true;

        mAppExecutors.diskIO().execute(mDataSource::fetchEmotions);
    }
}
