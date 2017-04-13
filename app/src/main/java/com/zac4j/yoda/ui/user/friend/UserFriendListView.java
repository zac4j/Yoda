package com.zac4j.yoda.ui.user.friend;

import com.zac4j.yoda.data.model.Friend;
import com.zac4j.yoda.ui.base.MvpView;

/**
 * View for User Friends
 * Created by zac on 4/13/2017.
 */

public interface UserFriendListView extends MvpView {

  boolean isFreshing();

  void showFriendList(Friend friend);
}
