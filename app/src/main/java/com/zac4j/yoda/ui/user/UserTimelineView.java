package com.zac4j.yoda.ui.user;

import com.zac4j.yoda.data.model.Weibo;
import com.zac4j.yoda.ui.base.MvpView;
import java.util.List;

/**
 * Timeline View
 * Created by zac on 3/17/2017.
 */

public interface UserTimelineView extends MvpView {

  void showProgress(boolean show);

  void showRefresh(boolean refresh);

  boolean isRefreshing();

  boolean isProgressing();

  void showEmpty(boolean show);

  void showTimeline(List<Weibo> weiboList);
}
