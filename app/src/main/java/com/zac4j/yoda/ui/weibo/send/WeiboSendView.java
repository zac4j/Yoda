package com.zac4j.yoda.ui.weibo.send;

import com.zac4j.yoda.ui.base.MvpView;

/**
 * Send Weibo View
 * Created by zac on 3/27/2017.
 */

public interface WeiboSendView extends MvpView {
  void showMessage(String message);
}
