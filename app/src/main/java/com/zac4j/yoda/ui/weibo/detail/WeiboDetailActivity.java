package com.zac4j.yoda.ui.weibo.detail;

import android.content.Context;
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
import com.zac4j.yoda.ui.adapter.TimelineAdapter;
import com.zac4j.yoda.ui.adapter.WeiboCommentAdapter;
import com.zac4j.yoda.ui.base.BaseActivity;
import com.zac4j.yoda.ui.listener.EndlessRecyclerViewScrollListener;
import com.zac4j.yoda.ui.weibo.repost.WeiboRepostDialogFragment;
import com.zac4j.yoda.util.WeiboReader;
import java.util.List;
import javax.inject.Inject;

/**
 * Activity for Weibo Detail
 * Created by zac on 3/30/2017.
 */

public class WeiboDetailActivity extends BaseActivity implements WeiboDetailView {

  public static final String EXTRA_WEIBO = "extra_weibo";
  private static final int DEFAULT_COMMENT_PAGE = 1;
  private static final int DEFAULT_COMMENT_COUNT = 10;
  private int mCommentPage = DEFAULT_COMMENT_PAGE;
  private long mWeiboId;
  private String mToken;

  @Inject WeiboDetailPresenter mPresenter;
  @Inject WeiboCommentAdapter mAdapter;

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

    mToken = AccessTokenKeeper.readAccessToken(this).getToken();

    // Set up CommentView
    LinearLayoutManager layoutManager = new LinearLayoutManager(this);
    mCommentListView.setLayoutManager(layoutManager);
    mCommentListView.addItemDecoration(
        new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
    mCommentListView.setAdapter(mAdapter);
    mCommentListView.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
      @Override public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
        mCommentPage = ++page;
        mPresenter.getWeiboComments(mToken, mWeiboId, mCommentPage, DEFAULT_COMMENT_COUNT);
      }
    });

    Weibo weibo = (Weibo) getIntent().getSerializableExtra(EXTRA_WEIBO);
    if (weibo != null) {
      showWeiboInfo(weibo);
      mWeiboId = weibo.getId();
    } else {
      showEmptyView(true);
    }
  }

  @Override protected void onStart() {
    super.onStart();

    if (mWeiboId != 0L) {
      mPresenter.getWeiboComments(mToken, mWeiboId, DEFAULT_COMMENT_PAGE, DEFAULT_COMMENT_COUNT);
    }
  }

  @OnClick({ R.id.weibo_detail_tv_repost, R.id.weibo_detail_tv_reply, R.id.weibo_detail_tv_like })
  public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.weibo_detail_tv_repost:
        setRepostClickEvent(String.valueOf(mWeiboId));
        break;
      case R.id.weibo_detail_tv_reply:
        break;
      case R.id.weibo_detail_tv_like:
        break;
    }
  }

  /**
   * 转发按钮点击事件
   */
  private void setRepostClickEvent(String weiboId) {
    WeiboRepostDialogFragment dialogFragment = WeiboRepostDialogFragment.newInstance(weiboId);
    dialogFragment.show(getSupportFragmentManager());
    dialogFragment.setOnRepostListener(new WeiboRepostDialogFragment.OnRepostListener() {
      @Override public void onSuccess(Weibo weibo1) {
        WeiboDetailActivity.super.onStart();
      }

      @Override public void onFailure() {
        // Try to do something fun.
      }
    });
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

    WeiboReader.readAvatar(this, mAvatarView, user.getProfileImageUrl());
    WeiboReader.readUsername(mUsernameView, user.getDomain());
    WeiboReader.readNickname(mNicknameView, user.getName());
    WeiboReader.readPostTime(mPostTimeView, weibo.getCreatedAt());
    WeiboReader.readPostSource(mPostFromView, weibo.getSource());
    WeiboReader.readContent(mWeiboContentView, weibo.getText());
    WeiboReader.readRepostNumber(mRepostView, weibo.getRepostsCount());
    WeiboReader.readCommentsNumber(mReplyView, weibo.getCommentsCount());
    WeiboReader.readLikeNumber(mLikeView, weibo.getAttitudesCount());

    // 设置自己是否赞过
    WeiboReader.readLikeState(mLikeView, weibo.getFavorited());
  }

  @Override public void showWeiboComments(List<Comment> commentList) {
    mAdapter.addCommentList(commentList);
  }

  @Override public void showEmptyCommentView(boolean show) {

  }
}
