package com.zac4j.yoda.ui.user.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.zac4j.yoda.R;
import com.zac4j.yoda.ui.base.BaseActivity;

/**
 * Activity for User Profile Detail
 * Created by zac on 4/18/2017.
 */

public class UserDetailActivity extends BaseActivity {

  public static final String EXTRA_UID = "extra_uid";

  @BindView(R.id.user_detail_iv_background) ImageView mUserDetailBgView;
  @BindView(R.id.toolbar) Toolbar mToolbar;
  @BindView(R.id.collapsing_toolbar) CollapsingToolbarLayout mCollapsingToolbar;
  @BindView(R.id.user_detail_fab_avatar) FloatingActionButton mAvatarBtn;
  @BindView(R.id.user_detail_tv_nickname) TextView mNicknameView;
  @BindView(R.id.user_detail_tv_username) TextView mUsernameView;
  @BindView(R.id.user_detail_tv_description) TextView mDescriptionView;
  @BindView(R.id.user_detail_tv_location) TextView mLocationView;
  @BindView(R.id.user_detail_tv_link) TextView mLinkView;
  @BindView(R.id.user_detail_vp_media_container) ViewPager mMediaContainer;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user_detail);
    ButterKnife.bind(this);

    setSupportActionBar(mToolbar);

    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
    }

    // mCollapsingToolbar.setTitle();

    long uid = getIntent().getLongExtra(EXTRA_UID, 0L);

  }
}
