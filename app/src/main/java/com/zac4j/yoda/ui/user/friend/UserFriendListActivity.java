package com.zac4j.yoda.ui.user.friend;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

  public static final int REQUEST_FRIENDS_COUNT = 10;

  @BindView(R.id.user_friend_list_view) RecyclerView mFriendListView;
  @BindView(R.id.user_friend_list_swipe_container) SwipeRefreshLayout mFriendListSwipeContainer;
  @BindView(R.id.progress_bar) ProgressBar mProgressBar;
  @BindView(R.id.error_view_img) AppCompatImageView mErrorImgView;
  @BindView(R.id.error_view_txt) TextView mErrorTextView;

  private int mCount = REQUEST_FRIENDS_COUNT;
  private int mCursor;

  @Inject FriendListAdapter mAdapter;
  @Inject UserFriendListPresenter mPresenter;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user_friend_list);

    getActivityComponent().inject(this);
    ButterKnife.bind(this);
    mPresenter.attach(this);

    final Oauth2AccessToken accessToken = AccessTokenKeeper.readAccessToken(this);

    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    mFriendListView.setLayoutManager(layoutManager);
    mFriendListView.addItemDecoration(
        new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
    mFriendListView.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
      @Override public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
        mPresenter.getUserFriends(accessToken.getToken(), accessToken.getUid(), mCount, mCursor);
      }
    });

    mPresenter.getUserFriends(accessToken.getToken(), accessToken.getUid(), mCount, mCursor);
  }

  @Override public void showMainView(boolean show) {
    if (mFriendListView != null) {
      mFriendListView.setVisibility(show ? View.VISIBLE : View.GONE);
    }
  }

  @Override public void showProgress(boolean show) {
    if (mProgressBar != null) {
      mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }
  }

  @Override public void showError(String message) {
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
    return mFriendListSwipeContainer != null && mFriendListSwipeContainer.isRefreshing();
  }

  @Override public void showFriendList(Friend friend) {
    if (friend == null) {
      return;
    }

    mCursor = friend.getNextCursor();

    List<User> userList = friend.getUsers();

    if (userList == null || userList.isEmpty() || mAdapter == null) {
      return;
    }

    mAdapter.addFriendList(userList);
  }
}
