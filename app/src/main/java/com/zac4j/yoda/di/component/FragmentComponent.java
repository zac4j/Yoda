package com.zac4j.yoda.di.component;

import com.zac4j.yoda.di.PerFragment;
import com.zac4j.yoda.di.module.FragmentModule;
import com.zac4j.yoda.ui.base.BaseFragment;
import com.zac4j.yoda.ui.home.HomeNotificationFragment;
import com.zac4j.yoda.ui.home.HomeTimelineFragment;
import dagger.Subcomponent;

/**
 * Fragment Component
 * Created by zac on 3/17/2017.
 */
@PerFragment @Subcomponent(modules = FragmentModule.class) public interface FragmentComponent {

  void inject(BaseFragment baseFragment);

  void inject(HomeTimelineFragment homeTimelineFragment);

  void inject(HomeNotificationFragment homeNotificationFragment);
}
