package com.zac4j.yoda.ui.home;

import com.zac4j.yoda.data.model.Timeline;
import com.zac4j.yoda.data.model.Weibo;
import com.zac4j.yoda.ui.base.MvpView;
import java.util.List;

/**
 * Timeline View
 * Created by zac on 3/17/2017.
 */

public interface TimelineView extends MvpView {

  void showTimeline(List<Weibo> weiboList);
}
