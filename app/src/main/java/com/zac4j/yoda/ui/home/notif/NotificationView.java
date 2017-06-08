package com.zac4j.yoda.ui.home.notif;

import com.zac4j.yoda.data.model.Comment;
import com.zac4j.yoda.ui.base.MvpView;

/**
 * View for Notification
 * Created by zac on 3/22/2017.
 */

public interface NotificationView extends MvpView {
  void showComment(Comment comment);

  void showEmptyComment(boolean show);
}
