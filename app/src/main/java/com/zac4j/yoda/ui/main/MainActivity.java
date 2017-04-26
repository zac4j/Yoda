package com.zac4j.yoda.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.zac4j.yoda.R;
import com.zac4j.yoda.ui.adapter.MainPagerAdapter;
import com.zac4j.yoda.ui.base.BaseActivity;
import com.zac4j.yoda.ui.home.HomeNotificationFragment;
import com.zac4j.yoda.ui.home.HomeTimelineFragment;
import com.zac4j.yoda.ui.login.LoginActivity;
import com.zac4j.yoda.ui.user.UserActivity;
import com.zac4j.yoda.ui.user.friend.UserFriendListActivity;
import com.zac4j.yoda.ui.weibo.send.WeiboSendActivity;
import com.zac4j.yoda.util.image.CircleTransformation;
import javax.inject.Inject;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends BaseActivity {

  @BindView(R.id.main_vp_container) ViewPager mViewPager;
  @BindView(R.id.main_fab_write) FloatingActionButton mWriteBtn;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    getActivityComponent().inject(this);
    ButterKnife.bind(this);

    if (mViewPager != null) {
      setupViewPager(mViewPager);
    }

    if (mWriteBtn != null) {
      mWriteBtn.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          startActivity(new Intent(MainActivity.this, WeiboSendActivity.class));
        }
      });
    }
  }

  private void setupViewPager(ViewPager viewPager) {
    MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager());
    viewPager.setAdapter(adapter);
    viewPager.setOffscreenPageLimit(0);
  }

  public void onTokenInvalid() {
    AccessTokenKeeper.clear(this);
    startActivity(new Intent(this, LoginActivity.class));
  }
}
