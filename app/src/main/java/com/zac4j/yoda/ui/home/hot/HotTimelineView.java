package com.zac4j.yoda.ui.home.hot;

import com.zac4j.yoda.data.model.Weibo;
import com.zac4j.yoda.ui.base.MvpView;
import java.util.List;

/**
 * View for Hot Timeline
 * Created by zac on 5/2/17.
 */

public interface HotTimelineView extends MvpView {

  void showTimeline(List<Weibo> weiboList);

  void showRefresh(boolean show);

  boolean isRefreshing();

}
