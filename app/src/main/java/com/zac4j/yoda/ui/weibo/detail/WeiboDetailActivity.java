package com.zac4j.yoda.ui.weibo.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.zac4j.yoda.R;
import com.zac4j.yoda.data.model.Weibo;
import com.zac4j.yoda.ui.base.BaseActivity;
import javax.inject.Inject;

/**
 * Activity for Weibo Detail
 * Created by zac on 3/30/2017.
 */

public class WeiboDetailActivity extends BaseActivity {

  public static final String WEIBO_EXTRA = "weibo";

  @Inject WeiboDetailPresenter mPresenter;

  @BindView(R.id.weibo_detail_iv_avatar) ImageView mAvatarView;
  @BindView(R.id.weibo_detail_tv_nickname) TextView mNicknameView;
  @BindView(R.id.weibo_detail_tv_username) TextView mUsernameView;
  @BindView(R.id.weibo_detail_tv_post_time) TextView mPostTimeView;
  @BindView(R.id.weibo_detail_tv_post_from) TextView mPostFromView;
  @BindView(R.id.weibo_detail_tv_content) TextView mWeiboContentView;
  @BindView(R.id.weibo_detail_fl_media_container) FrameLayout mMediaContainer;
  @BindView(R.id.weibo_detail_rv_comment_list) RecyclerView mCommentListView;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_weibo_detail);
    ButterKnife.bind(this);
    getActivityComponent().inject(this);

    Weibo weibo = getIntent().getParcelableExtra(WEIBO_EXTRA);

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
}
