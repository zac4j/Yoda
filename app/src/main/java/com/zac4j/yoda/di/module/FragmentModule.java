package com.zac4j.yoda.di.module;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import com.zac4j.yoda.di.ActivityContext;
import dagger.Module;
import dagger.Provides;

/**
 * Fragment Module
 * Created by zac on 3/17/2017.
 */
@Module public class FragmentModule {

  private Fragment mFragment;

  public FragmentModule(Fragment fragment) {
    mFragment = fragment;
  }

  @Provides Fragment provideFragment() {
    return mFragment;
  }

  @Provides Activity provideActivity() {
    return mFragment.getActivity();
  }

  @Provides @ActivityContext Context provideContext() {
    return mFragment.getActivity();
  }
}
