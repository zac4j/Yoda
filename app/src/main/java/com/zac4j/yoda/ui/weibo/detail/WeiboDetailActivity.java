package com.zac4j.yoda.ui.weibo.detail;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.bumptech.glide.Glide;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.zac4j.yoda.R;
import com.zac4j.yoda.data.model.User;
import com.zac4j.yoda.data.model.Weibo;
import com.zac4j.yoda.ui.base.BaseActivity;
import com.zac4j.yoda.util.TimeUtils;
import javax.inject.Inject;

/**
 * Activity for Weibo Detail
 * Created by zac on 3/30/2017.
 */

public class WeiboDetailActivity extends BaseActivity implements WeiboDetailView {

  public static final String WEIBO_ID_EXTRA = "weibo_id";

  @Inject WeiboDetailPresenter mPresenter;

  @BindView(R.id.weibo_detail_main_view) View mMainView;
  @BindView(R.id.toolbar) Toolbar mToolbar;
  @BindView(R.id.progress_bar) ProgressBar mProgressBar;
  @BindView(R.id.error_view) View mErrorView;
  @BindView(R.id.error_view_txt) TextView mErrorTextView;
  @BindView(R.id.weibo_detail_iv_avatar) ImageView mAvatarView;
  @BindView(R.id.weibo_detail_tv_nickname) TextView mNicknameView;
  @BindView(R.id.weibo_detail_tv_username) TextView mUsernameView;
  @BindView(R.id.weibo_detail_tv_post_time) TextView mPostTimeView;
  @BindView(R.id.weibo_detail_tv_post_from) TextView mPostFromView;
  @BindView(R.id.weibo_detail_tv_content) TextView mWeiboContentView;
  @BindView(R.id.weibo_detail_iv_media_view) ImageView mMediaView;
  @BindView(R.id.weibo_detail_rv_comment_list) RecyclerView mCommentListView;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_weibo_detail);

    getActivityComponent().inject(this);
    mPresenter.attach(this);
    ButterKnife.bind(this);

    if (mToolbar != null) {
      setSupportActionBar(mToolbar);
    }

    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
      actionBar.setTitle(R.string.weibo_detail);
    }

    final long weiboId = getIntent().getLongExtra(WEIBO_ID_EXTRA, 0L);
    if (weiboId != 0) {
      String token = AccessTokenKeeper.readAccessToken(this).getToken();
      mPresenter.getWeibo(token, weiboId);
    }
  }

  @OnClick({ R.id.weibo_detail_tv_repost, R.id.weibo_detail_tv_reply, R.id.weibo_detail_tv_like })
  public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.weibo_detail_tv_repost:
        break;
      case R.id.weibo_detail_tv_reply:
        break;
      case R.id.weibo_detail_tv_like:
        break;
    }
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        break;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override public void showProgress(boolean show) {
    if (mProgressBar != null) {
      mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
    }
  }

  @Override public void showEmptyView(boolean show) {

  }

  @Override public void showMessage(String message) {
    mErrorView.setVisibility(View.VISIBLE);
    if (TextUtils.isEmpty(message)) {
      return;
    }
    mErrorTextView.setText(message);
  }

  @Override public void showWeiboInfo(Weibo weibo) {

    if (weibo == null) {
      return;
    }

    final User user = weibo.getUser();

    if (user == null) {
      return;
    }

    // set user avatar
    String avatarUrl = user.getProfileImageUrl();
    if (!TextUtils.isEmpty(avatarUrl)) {
      Glide.with(this).load(avatarUrl).into(mAvatarView);
    }

    // set username
    String username = user.getDomain();
    if (!TextUtils.isEmpty(username)) {
      mUsernameView.setText(user.getDomain());
    }

    // set nickname
    String name = user.getName();
    if (!TextUtils.isEmpty(name)) {
      mNicknameView.setText(name);
    }

    // set post time
    setupPostTime(weibo.getCreatedAt());

    // set post from
    setupPostFrom(weibo.getSource());

    String content = weibo.getText();
    if (!TextUtils.isEmpty(content)) {
      mWeiboContentView.setText(content);
    }

    String mediaUrl = weibo.getBmiddlePic();
    if (!TextUtils.isEmpty(mediaUrl)) {
      Glide.with(this).load(mediaUrl).into(mMediaView);
    }
    mMediaView.setVisibility(View.VISIBLE);
  }

  private void setupPostTime(String postTime) {
    if (TextUtils.isEmpty(postTime)) {
      return;
    }
    String pattern = "E MMM dd HH:mm:ss Z yyyy";
    String dateStr = TimeUtils.getDateAndTime(postTime, pattern);
    mPostTimeView.setText(dateStr);
  }

  private void setupPostFrom(String postFrom) {
    if (TextUtils.isEmpty(postFrom)) {
      return;
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      mPostFromView.setText(Html.fromHtml(postFrom, Html.FROM_HTML_MODE_LEGACY));
    } else {
      mPostFromView.setText(Html.fromHtml(postFrom));
    }
  }
}
