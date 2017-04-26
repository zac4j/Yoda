package com.zac4j.yoda.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.zac4j.yoda.R;
import com.zac4j.yoda.ui.base.BaseFragment;
import com.zac4j.yoda.ui.main.MainActivity;
import javax.inject.Inject;

/**
 * UI for Notification
 * Created by zac on 3/22/2017.
 */

public class HomeNotificationFragment extends BaseFragment implements HomeNotificationView {

  @Inject HomeNotificationPresenter mPresenter;

  @BindView(R.id.home_rv_notification_list) RecyclerView mNotificationListView;
  @BindView(R.id.home_swipe_notifications_container) SwipeRefreshLayout mSwipeContainer;
  @BindView(R.id.notification_progress_bar) ProgressBar mProgressBar;
  @BindView(R.id.notification_empty_view) View mEmptyView;

  public static HomeNotificationFragment newInstance() {
    return new HomeNotificationFragment();
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    getFragmentComponent().inject(this);

    View view = inflater.inflate(R.layout.fragment_home_notification_list, container, false);

    ButterKnife.bind(this, view);
    mPresenter.attach(this);

    return view;
  }

  @Override public void showError(String message) {
    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
  }

  @Override public boolean isProcessing() {
    return mProgressBar != null && mProgressBar.isShown();
  }

  @Override public void onTokenInvalid() {
    ((MainActivity) getActivity()).onTokenInvalid();
  }

  @Override public void showMainView(boolean show) {
    if (mSwipeContainer != null) {
      mSwipeContainer.setVisibility(show ? View.VISIBLE : View.GONE);
    }
  }

  @Override public void showProgress(boolean show) {
    if (mProgressBar != null) {
      mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }
  }

  @Override public void showRefresh(boolean refresh) {
    mSwipeContainer.setRefreshing(refresh);
  }

  @Override public boolean isRefreshing() {
    return mSwipeContainer.isRefreshing();
  }

  @Override public boolean isProgressing() {
    return mProgressBar.isShown();
  }

  @Override public void showEmpty() {
    mEmptyView.setVisibility(View.VISIBLE);
  }
}
