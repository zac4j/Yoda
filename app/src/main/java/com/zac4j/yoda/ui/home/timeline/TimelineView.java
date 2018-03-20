package com.zac4j.yoda.ui.home.timeline;

import com.zac4j.yoda.data.model.Weibo;
import com.zac4j.yoda.ui.base.MvpView;
import java.util.List;

/**
 * Timeline View
 * Created by zac on 3/17/2017.
 */

interface TimelineView extends MvpView {

    void showProgress(boolean show);

    void showEmptyView(boolean show);

    void showTimeline(List<Weibo> weiboList);

    void showRefresh(boolean show);

    boolean isRefreshing();
}
