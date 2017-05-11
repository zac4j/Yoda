package com.zac4j.yoda.ui.home.notification;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.zac4j.yoda.R;
import com.zac4j.yoda.ui.adapter.NotificationAdapter;
import com.zac4j.yoda.ui.base.BaseFragment;
import com.zac4j.yoda.ui.main.MainActivity;
import javax.inject.Inject;

/**
 * UI for Notification
 * Created by zac on 3/22/2017.
 */

public class NotificationFragment extends BaseFragment implements NotificationView {

  @Inject NotificationPresenter mPresenter;
  @Inject NotificationAdapter mAdapter;

  @BindView(R.id.home_rv_notification_list) RecyclerView mListView;
  @BindView(R.id.home_swipe_notifications_container) SwipeRefreshLayout mSwipeContainer;
  @BindView(R.id.notification_progress_bar) ProgressBar mProgressBar;
  @BindView(R.id.notification_empty_view) View mEmptyView;
  private Unbinder unbinder;

  public static NotificationFragment newInstance() {
    return new NotificationFragment();
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_home_notification, container, false);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    getFragmentComponent().inject(this);
    unbinder = ButterKnife.bind(this, view);
    mPresenter.attach(this);

    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
    mListView.setLayoutManager(layoutManager);
    mListView.setAdapter(mAdapter);
  }

  @Override public void onResume() {
    super.onResume();
  }

  @Override public void onDestroy() {
    super.onDestroy();
    unbinder.unbind();
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
    if (mSwipeContainer == null) {
      return;
    }
    mSwipeContainer.setVisibility(show ? View.VISIBLE : View.GONE);
  }

  @Override public void showProgress(boolean show) {
    if (mProgressBar == null) {
      return;
    }
    mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
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
