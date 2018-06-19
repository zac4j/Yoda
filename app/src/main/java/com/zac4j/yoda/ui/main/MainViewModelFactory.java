package com.zac4j.yoda.ui.main;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import com.zac4j.yoda.data.EmotionRepository;

/**
 * Created by zac on 2018/6/19.
 * Description:Factory method that allows us to create a ViewModel with a constructor that takes a
 * {@link MainViewModelFactory}
 */
public class MainViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final EmotionRepository mRepository;

    public MainViewModelFactory(EmotionRepository repository) {
        mRepository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainActivityViewModel(mRepository);
    }
}
