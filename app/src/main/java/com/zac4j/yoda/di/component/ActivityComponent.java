package com.zac4j.yoda.di.component;

import android.content.Context;
import com.zac4j.yoda.di.ActivityContext;
import com.zac4j.yoda.di.PerActivity;
import com.zac4j.yoda.di.module.ActivityModule;
import com.zac4j.yoda.ui.base.BaseActivity;
import com.zac4j.yoda.ui.main.MainActivity;
import com.zac4j.yoda.ui.user.friend.UserFriendListActivity;
import com.zac4j.yoda.ui.weibo.WeiboImageActivity;
import com.zac4j.yoda.ui.weibo.detail.WeiboDetailActivity;
import com.zac4j.yoda.ui.weibo.send.WeiboSendActivity;
import dagger.Subcomponent;

/**
 * Activity Component
 * Created by zac on 16-7-3.
 */

@PerActivity @Subcomponent(modules = ActivityModule.class) public interface ActivityComponent {
  @ActivityContext Context context();

  void inject(BaseActivity baseActivity);

  void inject(WeiboSendActivity sendWeiboActivity);

  void inject(WeiboDetailActivity weiboDetailActivity);

  void inject(WeiboImageActivity weiboImageActivity);

  void inject(UserFriendListActivity friendListActivity);
}
