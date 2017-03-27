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
import com.zac4j.yoda.ui.weibo.SendWeiboActivity;
import com.zac4j.yoda.util.img.CircleTransformation;
import javax.inject.Inject;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends BaseActivity implements MainView {

  @Inject MainPresenter mMainPresenter;

  @BindView(R.id.main_toolbar) Toolbar mToolbar;
  @BindView(R.id.main_drawer_layout) DrawerLayout mDrawerLayout;
  private ImageView mAvatarView;
  private TextView mUserDescView;
  private ProgressBar mProgressBar;

  @BindView(R.id.main_nav_view) NavigationView mNavigationView;
  @BindView(R.id.main_viewpager) ViewPager mViewPager;
  @BindView(R.id.main_fab) FloatingActionButton mFABtn;
  @BindView(R.id.main_tabs) TabLayout mTableLayout;

  private DrawerLayout.DrawerListener mDrawerToggle;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    getActivityComponent().inject(this);
    ButterKnife.bind(this);
    mMainPresenter.attach(this);

    if (mToolbar != null) {
      setSupportActionBar(mToolbar);
    }

    final ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
      actionBar.setDisplayHomeAsUpEnabled(true);
    }

    if (mNavigationView != null) {
      setupDrawer(mNavigationView);
      // Get navigation view header layout
      View HeaderLayout = mNavigationView.getHeaderView(0);
      mAvatarView = (ImageView) HeaderLayout.findViewById(R.id.main_drawer_header_avatar);
      mUserDescView = (TextView) HeaderLayout.findViewById(R.id.main_drawer_header_desc);
      mProgressBar = (ProgressBar) HeaderLayout.findViewById(R.id.main_drawer_header_progress_bar);
    }

    if (mViewPager != null) {
      setupViewPager(mViewPager);
    }

    if (mTableLayout != null) {
      mTableLayout.setupWithViewPager(mViewPager);
    }

    if (mFABtn != null) {
      mFABtn.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          startActivity(new Intent(MainActivity.this, SendWeiboActivity.class));
        }
      });
    }
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        mDrawerLayout.openDrawer(GravityCompat.START);

        // Get user profile
        Oauth2AccessToken accessToken = AccessTokenKeeper.readAccessToken(MainActivity.this);
        mMainPresenter.getUserProfile(accessToken.getToken(), accessToken.getUid());
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override protected void onDestroy() {
    mDrawerLayout.removeDrawerListener(mDrawerToggle);
    super.onDestroy();
  }

  private void setupDrawer(NavigationView navigationView) {
    mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open,
        R.string.drawer_close) {

      /** Called when a drawer has settled in a completely closed state. */
      public void onDrawerClosed(View view) {
        super.onDrawerClosed(view);
        showProgress(false);
      }

      /** Called when a drawer has settled in a completely open state. */
      public void onDrawerOpened(View drawerView) {
        super.onDrawerOpened(drawerView);
        Oauth2AccessToken accessToken = AccessTokenKeeper.readAccessToken(MainActivity.this);
        mMainPresenter.getUserProfile(accessToken.getToken(), accessToken.getUid());
      }
    };
    // Set the drawer toggle as the DrawerListener
    mDrawerLayout.addDrawerListener(mDrawerToggle);

    navigationView.setNavigationItemSelectedListener(
        new NavigationView.OnNavigationItemSelectedListener() {
          @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            item.setChecked(true);
            mDrawerLayout.closeDrawers();
            switch (item.getItemId()) {
              case R.id.nav_drawer_my_moments:
                startActivity(new Intent(MainActivity.this, UserActivity.class));
                break;
            }
            return true;
          }
        });
  }

  private void setupViewPager(ViewPager viewPager) {
    MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager());
    adapter.addFragment(new HomeTimelineFragment(), "Timeline");
    adapter.addFragment(new HomeNotificationFragment(), "Notification");
    viewPager.setAdapter(adapter);
    viewPager.setOffscreenPageLimit(1);
  }

  @Override public void showError(String message) {
    showToast(message);
  }

  @Override public void onTokenInvalid() {
    AccessTokenKeeper.clear(this);
    startActivity(new Intent(this, LoginActivity.class));
  }

  @Override public boolean isProcessing() {
    return mProgressBar != null && mProgressBar.isShown();
  }

  @Override public void showProgress(boolean show) {
    if (mProgressBar != null) {
      mProgressBar.setVisibility(show ? VISIBLE : GONE);
    }
  }

  @Override public void showAvatar(String avatarUrl) {
    if (TextUtils.isEmpty(avatarUrl)) {
      return;
    }
    Glide.with(this).load(avatarUrl).transform(new CircleTransformation(this)).into(mAvatarView);
  }

  @Override public void showUserDescription(String description) {
    if (TextUtils.isEmpty(description)) {
      return;
    }
    mUserDescView.setText(description);
  }
}
