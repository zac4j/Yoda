package com.zac4j.yoda.ui.home.notif;

import com.zac4j.yoda.data.model.Comment;
import com.zac4j.yoda.data.model.User;
import com.zac4j.yoda.data.model.db.Profile;
import com.zac4j.yoda.ui.base.MvpView;
import java.util.List;

/**
 * View for Notification
 * Created by zac on 3/22/2017.
 */

public interface NotificationView extends MvpView {

  void showEmptyComment();

  void showComments(List<Comment> comments);

  void showEmptyFollower();

  void showLatestFollowers(List<User> users);

  void showProfileDialog(Profile profile);
}
