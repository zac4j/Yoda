package com.zac4j.yoda.ui.user.friend;

import com.zac4j.yoda.data.model.Friend;
import com.zac4j.yoda.data.model.User;
import com.zac4j.yoda.ui.base.MvpView;
import java.util.List;

/**
 * View for User Friends
 * Created by zac on 4/13/2017.
 */

public interface UserFriendListView extends MvpView {

  boolean isRefreshing();

  void showRefresh(boolean show);

  void showEmpty(boolean show);

  void showFriendList(Friend friend);
}
