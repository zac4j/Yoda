package com.zac4j.yoda.di.component;

import android.content.Context;
import com.zac4j.yoda.di.ActivityContext;
import com.zac4j.yoda.di.PerActivity;
import com.zac4j.yoda.di.module.ActivityModule;
import dagger.Subcomponent;

/**
 * Activity Component
 * Created by zac on 16-7-3.
 */

@PerActivity @Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {
  @ActivityContext Context context();
}
