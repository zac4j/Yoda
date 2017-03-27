package com.zac4j.yoda.ui.main;

import com.zac4j.yoda.ui.base.MvpView;

/**
 * Main View
 * Created by zac on 16-8-27.
 */
public interface MainView extends MvpView {

  void showAvatar(String avatarUrl);

  void showUserDescription(String description);
}
