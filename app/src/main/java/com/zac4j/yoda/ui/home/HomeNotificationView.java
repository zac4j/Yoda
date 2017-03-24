package com.zac4j.yoda.ui.home;

import com.zac4j.yoda.ui.base.MvpView;

/**
 * View for Notification
 * Created by zac on 3/22/2017.
 */

public interface HomeNotificationView extends MvpView {
  void showProgress(boolean show);

  void showRefresh(boolean refresh);

  boolean isRefreshing();

  boolean isProgressing();

  void showEmpty();
}
