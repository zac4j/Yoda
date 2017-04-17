package com.zac4j.yoda.ui.user.friend;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.zac4j.yoda.R;
import com.zac4j.yoda.data.model.Friend;
import com.zac4j.yoda.data.model.User;
import com.zac4j.yoda.data.model.response.Error;
import com.zac4j.yoda.ui.adapter.FriendListAdapter;
import com.zac4j.yoda.ui.base.BaseActivity;
import com.zac4j.yoda.ui.listener.EndlessRecyclerViewScrollListener;
import com.zac4j.yoda.ui.login.LoginActivity;
import dagger.multibindings.ElementsIntoSet;
import java.util.List;
import javax.inject.Inject;

/**
 * Activity for User Friends
 * Created by zac on 4/13/2017.
 */

public class UserFriendListActivity extends BaseActivity implements UserFriendListView {

  public static final int REQUEST_FRIENDS_COUNT = 20;

  @BindView(R.id.toolbar) Toolbar mToolbar;
  @BindView(R.id.user_friend_list_view) RecyclerView mFriendListView;
  @BindView(R.id.user_friend_list_swipe_container) SwipeRefreshLayout mSwipeContainer;
  @BindView(R.id.progress_bar) ProgressBar mProgressBar;
  @BindView(R.id.error_view) View mErrorView;
  @BindView(R.id.error_view_img) AppCompatImageView mErrorImgView;
  @BindView(R.id.error_view_txt) TextView mErrorTextView;

  private int mCount = REQUEST_FRIENDS_COUNT;
  private int mCursor;
  private boolean mIsProcessing;
  private EndlessRecyclerViewScrollListener mScrollListener;

  @Inject FriendListAdapter mAdapter;
  @Inject UserFriendListPresenter mPresenter;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user_friend_list);

    getActivityComponent().inject(this);
    ButterKnife.bind(this);
    mPresenter.attach(this);

    if (mToolbar != null) {
      setSupportActionBar(mToolbar);
    }

    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
      actionBar.setTitle(R.string.nav_drawer_my_friends);
    }

    final Oauth2AccessToken accessToken = AccessTokenKeeper.readAccessToken(this);

    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    mFriendListView.setLayoutManager(layoutManager);
    mFriendListView.addItemDecoration(
        new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    mFriendListView.setAdapter(mAdapter);

    mScrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
      @Override public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
        mPresenter.getUserFriends(accessToken.getToken(), accessToken.getUid(), mCount, mCursor);
      }
    };
    mFriendListView.addOnScrollListener(mScrollListener);

    mSwipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override public void onRefresh() {
        mPresenter.getUserFriends(accessToken.getToken(), accessToken.getUid(),
            REQUEST_FRIENDS_COUNT, 0);
      }
    });

    mSwipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
        android.R.color.holo_green_light, android.R.color.holo_orange_light,
        android.R.color.holo_red_light);

    mPresenter.getUserFriends(accessToken.getToken(), accessToken.getUid(), mCount, mCursor);
  }

  @Override public void showMainView(boolean show) {
    if (mSwipeContainer != null) {
      mSwipeContainer.setVisibility(show ? View.VISIBLE : View.GONE);
    }
  }

  @Override public void showProgress(boolean show) {
    if (mProgressBar != null) {
      mIsProcessing = show;
      mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }
  }

  @Override public void showError(String message) {
    mErrorView.setVisibility(View.VISIBLE);
    mErrorTextView.setText(message);
  }

  @Override public boolean isProcessing() {
    return mProgressBar != null && mProgressBar.isShown();
  }

  @Override public void onTokenInvalid() {
    AccessTokenKeeper.clear(this);
    startActivity(new Intent(this, LoginActivity.class));
  }

  @Override public boolean isRefreshing() {
    return mSwipeContainer != null && mSwipeContainer.isRefreshing();
  }

  @Override public void showRefresh(boolean show) {
    if (mSwipeContainer != null) {
      mSwipeContainer.setRefreshing(show);
    }
  }

  @Override public void showEmpty(boolean show) {
    if (show) {
      mErrorView.setVisibility(View.VISIBLE);
      mErrorTextView.setText(getString(R.string.error_no_friends));
    } else {
      mErrorView.setVisibility(View.GONE);
    }
  }

  @Override public void showFriendList(Friend friend) {

    if (mCursor == 0) {
      if (mScrollListener != null) {
        mScrollListener.resetState();
      }
      mAdapter.clear();
    }

    mCursor = friend.getNextCursor();

    List<User> userList = friend.getUsers();

    if (userList == null || userList.isEmpty() || mAdapter == null) {
      showEmpty(true);
    }

    mAdapter.addFriendList(userList);
    mSwipeContainer.setRefreshing(false);
    mFriendListView.setVisibility(View.VISIBLE);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        break;
    }
    return true;
  }
}
