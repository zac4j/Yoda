package com.zac4j.yoda.data.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import com.zac4j.yoda.data.model.EmotionEntry;

/**
 * Created by zac on 2018/5/21.
 * Description:{@link Dao} which provides an api for all data operations with the {@link EmotionDatabase}.
 */
@Dao
public interface EmotionDao {

    @Query("SELECT * FROM emotion WHERE phrase = :phrase")
    LiveData<EmotionEntry> getEmotionByPhrase(String phrase);

}
