package com.zac4j.yoda.ui.home.hot;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
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

/**
 * UI for Hot Timeline
 * Created by Zac on 2017/5/1.
 */

public class HotTimelineFragment extends BaseFragment implements HotTimelineView{

  public static final int DEFAULT_REQUEST_WEIBO_COUNT = 50;

  private int mRequestCount = DEFAULT_REQUEST_WEIBO_COUNT;
  private int mRequestPage = 1;

  private String mToken; // user token
  private EndlessRecyclerViewScrollListener mScrollListener;

  @Inject HotTimelinePresenter mPresenter;
  @Inject TimelineAdapter mTimelineAdapter;

  @BindView(R.id.swipe_weibo_list_container) SwipeRefreshLayout mSwipeContainer;
  @BindView(R.id.recycler_weibo_list) RecyclerView mWeiboListView;
  @BindView(R.id.progress_bar) ProgressBar mProgressBar;

  public static HotTimelineFragment newInstance() {
    return new HotTimelineFragment();
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

    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
    mWeiboListView.setLayoutManager(layoutManager);
    mWeiboListView.setAdapter(mTimelineAdapter);

    mToken = AccessTokenKeeper.readAccessToken(getContext()).getToken();
    mScrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
      @Override public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
        mRequestPage = page;
      }
    };
    mWeiboListView.addOnScrollListener(mScrollListener);

    mSwipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override public void onRefresh() {
        mPresenter.getHotTimeline(mToken, mRequestCount, mRequestPage);
      }
    });

    mSwipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
        android.R.color.holo_green_light, android.R.color.holo_orange_light,
        android.R.color.holo_red_light);
  }

  @Override public void onResume() {
    super.onResume();
    mPresenter.getHotTimeline(mToken, mRequestCount, mRequestPage);
  }

  @Override public void onDestroy() {
    super.onDestroy();
    mWeiboListView.removeOnScrollListener(mScrollListener);
  }

  @Override public void showMainView(boolean show) {
    mWeiboListView.setVisibility(show ? View.VISIBLE : View.GONE);
  }

  @Override public void showProgress(boolean show) {
    mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
  }

  @Override public void showError(String message) {

  }

  @Override public boolean isProcessing() {
    return mSwipeContainer.isRefreshing() || mProgressBar.isShown();
  }

  @Override public void onTokenInvalid() {
    ((MainActivity)getActivity()).onTokenInvalid();
  }

  @Override public void showTimeline(List<Weibo> weiboList) {
    mTimelineAdapter.addWeiboList(weiboList);
  }

  @Override public void showRefresh(boolean show) {
    mSwipeContainer.setRefreshing(show);
  }

  @Override public boolean isRefreshing() {
    return mSwipeContainer.isRefreshing();
  }
}
