package com.zac4j.yoda.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.zac4j.yoda.R;
import com.zac4j.yoda.data.model.Weibo;
import com.zac4j.yoda.ui.adapter.HomeTimelineAdapter;
import com.zac4j.yoda.ui.base.BaseFragment;
import com.zac4j.yoda.ui.main.MainActivity;
import java.util.List;
import javax.inject.Inject;

/**
 * Weibo list page
 * Created by zac on 3/17/2017.
 */

public class TimelineFragment extends BaseFragment implements TimelineView {

  // Server default weibo count is 20 as well.
  public static final int DEFAULT_WEIBO_COUNT = 20;
  private int requestCount = DEFAULT_WEIBO_COUNT;
  private int requestPage = 1;

  @Inject TimelinePresenter mPresenter;
  @Inject HomeTimelineAdapter mTimelineAdapter;

  @BindView(R.id.home_swipe_weibo_list_container) SwipeRefreshLayout mSwipeContainer;
  @BindView(R.id.home_rv_weibo_list) RecyclerView mWeiboListView;
  @BindView(R.id.progress_bar) ProgressBar mProgressBar;
  @BindView(R.id.empty_view) ImageView mEmptyView;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    getFragmentComponent().inject(this);

    View view = inflater.inflate(R.layout.fragment_home_weibo_list, container, false);
    ButterKnife.bind(this, view);
    mPresenter.attach(this);

    final String token = AccessTokenKeeper.readAccessToken(getContext()).getToken();

    mSwipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override public void onRefresh() {
        mPresenter.getTimeline(token, requestCount, requestPage);
      }
    });

    mSwipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
        android.R.color.holo_green_light, android.R.color.holo_orange_light,
        android.R.color.holo_red_light);

    mWeiboListView.setLayoutManager(new LinearLayoutManager(getActivity()));
    mWeiboListView.addItemDecoration(
        new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    mWeiboListView.setAdapter(mTimelineAdapter);

    mPresenter.getTimeline(token, requestCount, requestPage);

    return view;
  }

  @Override public void showError(String message) {
    Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
  }

  @Override public void onTokenInvalid() {
    ((MainActivity) getActivity()).onTokenInvalid();
  }

  @Override public void showProgress(boolean show) {
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

  @Override public void showTimeline(List<Weibo> weiboList) {
    mTimelineAdapter.clear();
    mTimelineAdapter.setWeiboList(weiboList);

    mSwipeContainer.setRefreshing(false);
    mWeiboListView.setVisibility(View.VISIBLE);
  }
}
