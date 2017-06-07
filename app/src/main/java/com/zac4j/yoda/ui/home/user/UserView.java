package com.zac4j.yoda.ui.home.user;

import com.zac4j.yoda.data.model.User;
import com.zac4j.yoda.ui.base.MvpView;

/**
 * View for User Page
 * Created by zac on 17-5-10.
 */

public interface UserView extends MvpView {

  void showProfile(User user);
}
