package com.zac4j.yoda.di.component;

import com.zac4j.yoda.di.PerConfig;
import com.zac4j.yoda.di.module.ActivityModule;
import com.zac4j.yoda.di.module.DialogFragmentModule;
import com.zac4j.yoda.di.module.FragmentModule;
import com.zac4j.yoda.ui.base.BaseActivity;
import dagger.Component;

/**
 * A dagger component that live during the lifecycle of an Activity/Fragment but it won't be destroy
 * during configuration changes.Check {@link BaseActivity} to see how this components
 * survives configuration changes.
 * Use the {@link PerConfig} scope to annotate dependencies that need to survive
 * configuration changes (for example ViewModels).
 *
 * Created by zac on 16-7-17.
 */

@PerConfig @Component(dependencies = ApplicationComponent.class)
public interface PerConfigComponent {
  ActivityComponent activityComponent(ActivityModule activityModule);

  FragmentComponent fragmentComponent(FragmentModule fragmentModule);

  DialogFragmentComponent dialogFragmentComponent(DialogFragmentModule dialogFragmentModule);
}
