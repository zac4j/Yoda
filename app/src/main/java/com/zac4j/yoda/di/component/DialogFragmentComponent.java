package com.zac4j.yoda.di.component;

import com.zac4j.yoda.di.PerFragment;
import com.zac4j.yoda.di.module.DialogFragmentModule;
import com.zac4j.yoda.ui.base.BaseDialogFragment;
import com.zac4j.yoda.ui.weibo.repost.WeiboRepostDialogFragment;
import dagger.Subcomponent;

/**
 * Dialog fragment component
 * Created by Zheng on 6/16/2017.
 */

@PerFragment @Subcomponent(modules = DialogFragmentModule.class)
public interface DialogFragmentComponent {

  void inject(BaseDialogFragment baseDialog);

  void inject(WeiboRepostDialogFragment repostDialog);
}
