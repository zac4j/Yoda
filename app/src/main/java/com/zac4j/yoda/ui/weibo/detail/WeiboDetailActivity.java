package com.zac4j.yoda.ui.weibo.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.zac4j.yoda.R;
import com.zac4j.yoda.data.model.Comment;
import com.zac4j.yoda.data.model.User;
import com.zac4j.yoda.data.model.Weibo;
import com.zac4j.yoda.ui.base.BaseActivity;
import com.zac4j.yoda.util.WeiboReader;
import java.util.List;
import javax.inject.Inject;

/**
 * Activity for Weibo Detail
 * Created by zac on 3/30/2017.
 */

public class WeiboDetailActivity extends BaseActivity implements WeiboDetailView {

  public static final String EXTRA_WEIBO = "extra_weibo";

  @Inject WeiboDetailPresenter mPresenter;

  @BindView(R.id.weibo_detail_main_view) View mMainView;
  @BindView(R.id.toolbar) Toolbar mToolbar;
  @BindView(R.id.progress_bar) ProgressBar mProgressBar;
  @BindView(R.id.weibo_detail_iv_avatar) ImageView mAvatarView;
  @BindView(R.id.weibo_detail_tv_nickname) TextView mNicknameView;
  @BindView(R.id.weibo_detail_tv_username) TextView mUsernameView;
  @BindView(R.id.weibo_detail_tv_post_time) TextView mPostTimeView;
  @BindView(R.id.weibo_detail_tv_post_from) TextView mPostFromView;
  @BindView(R.id.weibo_detail_tv_content) TextView mWeiboContentView;
  @BindView(R.id.weibo_detail_tv_repost) TextView mRepostView;
  @BindView(R.id.weibo_detail_tv_reply) TextView mReplyView;
  @BindView(R.id.weibo_detail_tv_like) TextView mLikeView;
  @BindView(R.id.weibo_detail_rv_comment_list) RecyclerView mCommentListView;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_weibo_detail);

    getActivityComponent().inject(this);
    mPresenter.attach(this);
    ButterKnife.bind(this);

    // Set up ActionBar
    if (mToolbar != null) {
      setSupportActionBar(mToolbar);
    }
    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
      actionBar.setTitle(R.string.weibo_detail);
    }

    // Set up CommentView
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    mCommentListView.setLayoutManager(layoutManager);
    mCommentListView.addItemDecoration(
        new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));

    Weibo weibo = (Weibo) getIntent().getSerializableExtra(EXTRA_WEIBO);
    if (weibo != null) {
      showWeiboInfo(weibo);

      String token = AccessTokenKeeper.readAccessToken(this).getToken();
      //mPresenter.getWeiboComments(token, weibo.getId());
    } else {
      showEmptyView(true);
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
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
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
    WeiboReader.readAvatar(this, mAvatarView, user.getProfileImageUrl());

    // set username
    WeiboReader.readUsername(mUsernameView, user.getDomain());

    // set nickname
    WeiboReader.readNickname(mNicknameView, user.getName());

    // set post time
    WeiboReader.readPostTime(mPostTimeView, weibo.getCreatedAt());

    // set post from
    WeiboReader.readPostSource(mPostFromView, weibo.getSource());

    // set weibo content
    WeiboReader.readContent(mWeiboContentView, weibo.getText());

    // set weibo repost number
    WeiboReader.readRepostNumber(mRepostView, weibo.getRepostsCount());

    // set weibo comment number
    WeiboReader.readCommentsNumber(mReplyView, weibo.getCommentsCount());

    // set weibo like number
    WeiboReader.readLikeNumber(mLikeView, weibo.getAttitudesCount());

    // 设置自己是否赞过
    WeiboReader.readLikeState(mLikeView, weibo.getFavorited());
  }

  @Override public void showWeiboComments(List<Comment> commentList) {

  }
}
