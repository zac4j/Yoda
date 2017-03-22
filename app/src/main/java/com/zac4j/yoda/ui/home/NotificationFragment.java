package com.zac4j.yoda.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.zac4j.yoda.R;
import com.zac4j.yoda.ui.base.BaseFragment;

/**
 * UI for Notification
 * Created by zac on 3/22/2017.
 */

public class NotificationFragment extends BaseFragment {

  @BindView(R.id.home_rv_notification_list) RecyclerView mNotificationListView;
  @BindView(R.id.home_swipe_notifications_container) SwipeRefreshLayout
      mSwipeNotificationsContainer;
  @BindView(R.id.notification_progress_bar) ProgressBar mNotificationProgressBar;
  @BindView(R.id.notification_empty_view) ImageView mNotificationEmptyView;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_home_notification_list, container, false);
    ButterKnife.bind(this, view);
    return view;
  }
}
