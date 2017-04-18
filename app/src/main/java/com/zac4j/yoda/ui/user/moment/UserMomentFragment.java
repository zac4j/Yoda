package com.zac4j.yoda.ui.user.moment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.zac4j.yoda.R;
import com.zac4j.yoda.data.model.Weibo;
import com.zac4j.yoda.ui.adapter.TimelineAdapter;
import com.zac4j.yoda.ui.base.BaseFragment;
import com.zac4j.yoda.ui.listener.EndlessRecyclerViewScrollListener;
import com.zac4j.yoda.ui.user.UserActivity;
import java.util.List;
import javax.inject.Inject;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Weibo list page
 * Created by zac on 3/17/2017.
 */

public class UserMomentFragment extends BaseFragment implements UserMomentView {

  // Server default weibo count is 20 as well.
  public static final int DEFAULT_WEIBO_COUNT = 6;
  private int mRequestCount = DEFAULT_WEIBO_COUNT;
  private int mRequestPage = 1;
  private EndlessRecyclerViewScrollListener mScrollListener;

  @Inject UserMomentPresenter mPresenter;
  @Inject TimelineAdapter mTimelineAdapter;

  @BindView(R.id.swipe_weibo_list_container) SwipeRefreshLayout mSwipeContainer;
  @BindView(R.id.recycler_weibo_list) RecyclerView mWeiboListView;
  @BindView(R.id.progress_bar) ProgressBar mProgressBar;
  @BindView(R.id.error_view) View mErrorView;
  @BindView(R.id.error_view_img) AppCompatImageView mErrorViewImg;
  @BindView(R.id.error_view_txt) TextView mErrorViewText;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    getFragmentComponent().inject(this);

    View view = inflater.inflate(R.layout.fragment_weibo_list, container, false);
    ButterKnife.bind(this, view);
    mPresenter.attach(this);

    final String token = AccessTokenKeeper.readAccessToken(getContext()).getToken();

    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
    mWeiboListView.setLayoutManager(layoutManager);
    mWeiboListView.addItemDecoration(
        new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    mWeiboListView.setAdapter(mTimelineAdapter);

    mScrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
      @Override public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
        mRequestPage = page;
        mPresenter.getTimeline(token, mRequestCount, mRequestPage);
      }
    };
    mWeiboListView.addOnScrollListener(mScrollListener);

    mSwipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override public void onRefresh() {
        mPresenter.getTimeline(token, mRequestCount, mRequestPage);
      }
    });

    mSwipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
        android.R.color.holo_green_light, android.R.color.holo_orange_light,
        android.R.color.holo_red_light);

    mPresenter.getTimeline(token, mRequestCount, mRequestPage);

    return view;
  }

  @Override public void showError(String message) {
    Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    mErrorView.setVisibility(VISIBLE);
  }

  @Override public boolean isProcessing() {
    return mProgressBar != null && mProgressBar.isShown();
  }

  @Override public void onTokenInvalid() {
    ((UserActivity) getActivity()).onTokenInvalid();
  }

  @Override public void showMainView(boolean show) {
    if (mSwipeContainer != null) {
      mSwipeContainer.setVisibility(show ? VISIBLE : GONE);
    }
  }

  @Override public void showProgress(boolean show) {
    if (mProgressBar != null) {
      mProgressBar.setVisibility(show ? VISIBLE : GONE);
    }
  }

  @Override public void showRefresh(boolean refresh) {
    mSwipeContainer.setRefreshing(refresh);
  }

  @Override public boolean isRefreshing() {
    return mSwipeContainer.isRefreshing();
  }

  @Override public void showEmpty(boolean show) {
    if (show) {
      mErrorView.setVisibility(VISIBLE);
      mErrorViewImg.setImageResource(R.drawable.ic_weibo);
      mErrorViewText.setText(R.string.error_no_weibo);
    } else {
      mErrorView.setVisibility(GONE);
    }
  }

  @Override public void showTimeline(List<Weibo> weiboList) {
    if (mRequestPage == 1) { // while refresh the list.
      if (mScrollListener != null) {
        mScrollListener.resetState();
      }
      mTimelineAdapter.clear();
    }

    if (weiboList == null || weiboList.isEmpty()) {
      showEmpty(mTimelineAdapter.isEmpty());
      return;
    }

    mTimelineAdapter.addWeiboList(weiboList);

    mSwipeContainer.setRefreshing(false);
    mWeiboListView.setVisibility(VISIBLE);
  }
}
