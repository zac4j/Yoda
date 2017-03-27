package com.zac4j.yoda.ui.weibo;

import com.zac4j.yoda.ui.base.MvpView;

/**
 * Send Weibo View
 * Created by zac on 3/27/2017.
 */

public interface SendWeiboView extends MvpView {
  void showMessage(String message);
}
