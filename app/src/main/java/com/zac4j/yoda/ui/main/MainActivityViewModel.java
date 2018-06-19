package com.zac4j.yoda.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import com.zac4j.yoda.data.EmotionRepository;
import com.zac4j.yoda.data.model.EmotionEntry;
import java.util.List;

/**
 * Created by zac on 2018/6/19.
 * Description:{@link ViewModel} for {@link MainActivity}
 */
public class MainActivityViewModel extends ViewModel {

    private final EmotionRepository mRepository;
    private final LiveData<List<EmotionEntry>> mEmotions;

    public MainActivityViewModel(EmotionRepository repository) {
        mRepository = repository;
        mEmotions = mRepository.getEmotionList();
    }

    public LiveData<List<EmotionEntry>> getEmotions() {
        return mEmotions;
    }
}
