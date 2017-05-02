package com.zac4j.yoda.ui.home.timeline;

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
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.zac4j.yoda.R;
import com.zac4j.yoda.data.model.Weibo;
import com.zac4j.yoda.ui.adapter.TimelineAdapter;
import com.zac4j.yoda.ui.base.BaseFragment;
import com.zac4j.yoda.ui.listener.EndlessRecyclerViewScrollListener;
import com.zac4j.yoda.ui.main.MainActivity;
import java.util.List;
import javax.inject.Inject;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Weibo list page
 * Created by zac on 3/17/2017.
 */

public class TimelineFragment extends BaseFragment implements TimelineView {

  // WebService default weibo count is 20 as well.
  public static final int DEFAULT_WEIBO_COUNT = 6;
  private int mRequestCount = DEFAULT_WEIBO_COUNT;
  private int mRequestPage = 1;

  private String mToken; // user token

  private EndlessRecyclerViewScrollListener mScrollListener;
  @Inject TimelinePresenter mPresenter;
  @Inject TimelineAdapter mTimelineAdapter;

  @BindView(R.id.swipe_weibo_list_container) SwipeRefreshLayout mSwipeContainer;
  @BindView(R.id.recycler_weibo_list) RecyclerView mWeiboListView;
  @BindView(R.id.progress_bar) ProgressBar mProgressBar;
  @BindView(R.id.error_view) View mErrorView;

  public static TimelineFragment newInstance() {
    return new TimelineFragment();
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_weibo_list, container, false);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    getFragmentComponent().inject(this);
    ButterKnife.bind(this, view);
    mPresenter.attach(this);

    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
    mWeiboListView.setLayoutManager(layoutManager);
    mWeiboListView.setAdapter(mTimelineAdapter);

    mToken = AccessTokenKeeper.readAccessToken(getContext()).getToken();
    System.out.println("Token: " + mToken);
    mScrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
      @Override public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
        mRequestPage = page;
        mPresenter.getTimeline(mToken, mRequestCount, mRequestPage);
      }
    };
    mWeiboListView.addOnScrollListener(mScrollListener);

    mSwipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override public void onRefresh() {
        mPresenter.getTimeline(mToken, mRequestCount, mRequestPage);
      }
    });

    mSwipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
        android.R.color.holo_green_light, android.R.color.holo_orange_light,
        android.R.color.holo_red_light);
  }

  @Override public void onResume() {
    super.onResume();
    mPresenter.getTimeline(mToken, mRequestCount, mRequestPage);
  }

  @Override public void onDestroy() {
    super.onDestroy();
    mWeiboListView.removeOnScrollListener(mScrollListener);
  }

  @Override public void showError(String message) {
    Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
  }

  @Override public boolean isProcessing() {
    return mProgressBar.isShown();
  }

  @Override public void onTokenInvalid() {
    ((MainActivity) getActivity()).onTokenInvalid();
  }

  @Override public void showMainView(boolean show) {
    mSwipeContainer.setVisibility(show ? VISIBLE : GONE);
  }

  @Override public void showProgress(boolean show) {
    mProgressBar.setVisibility(show ? VISIBLE : GONE);
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

  @Override public void showEmpty(boolean show) {
    mErrorView.setVisibility(show ? VISIBLE : GONE);
  }

  @Override public void showTimeline(List<Weibo> weiboList) {
    if (mRequestPage == 1) { // while refresh the list.
      if (mScrollListener != null) {
        mScrollListener.resetState();
      }
      mTimelineAdapter.clear();
    }
    mTimelineAdapter.addWeiboList(weiboList);

    mSwipeContainer.setRefreshing(false);
    mWeiboListView.setVisibility(VISIBLE);
  }
}
