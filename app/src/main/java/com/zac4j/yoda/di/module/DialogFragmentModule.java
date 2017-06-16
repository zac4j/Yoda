package com.zac4j.yoda.di.module;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.DialogFragment;
import com.zac4j.yoda.di.ActivityContext;
import dagger.Module;
import dagger.Provides;

/**
 * Module for dialog fragment
 * Created by Zheng on 6/16/2017.
 */

@Module public class DialogFragmentModule {

  private DialogFragment mDialogFragment;

  public DialogFragmentModule(DialogFragment dialogFragment) {
    mDialogFragment = dialogFragment;
  }

  @Provides DialogFragment provideDialogFragment() {
    return mDialogFragment;
  }

  @Provides Activity provideActivity() {
    return mDialogFragment.getActivity();
  }

  @Provides @ActivityContext Context provideContext() {
    return mDialogFragment.getActivity();
  }
}
