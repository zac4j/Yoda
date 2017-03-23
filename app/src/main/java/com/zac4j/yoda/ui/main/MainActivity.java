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
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.zac4j.yoda.R;
import com.zac4j.yoda.ui.adapter.MainPagerAdapter;
import com.zac4j.yoda.ui.base.BaseActivity;
import com.zac4j.yoda.ui.home.NotificationFragment;
import com.zac4j.yoda.ui.home.TimelineFragment;
import com.zac4j.yoda.ui.login.LoginActivity;
import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainView {

  @Inject MainPresenter mMainPresenter;

  @BindView(R.id.main_toolbar) Toolbar mToolbar;
  @BindView(R.id.main_drawer_layout) DrawerLayout mDrawerLayout;
  @BindView(R.id.main_nav_view) NavigationView mNavigationView;
  @BindView(R.id.main_viewpager) ViewPager mViewPager;
  @BindView(R.id.main_fab) FloatingActionButton mFABtn;
  @BindView(R.id.main_tabs) TabLayout mTableLayout;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);

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
    }

    if (mViewPager != null) {
      setupViewPager(mViewPager);
    }

    if (mTableLayout != null) {
      mTableLayout.setupWithViewPager(mViewPager);
    }
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        mDrawerLayout.openDrawer(GravityCompat.START);
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  private void setupDrawer(NavigationView navigationView) {
    navigationView.setNavigationItemSelectedListener(
        new NavigationView.OnNavigationItemSelectedListener() {
          @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            item.setChecked(true);
            mDrawerLayout.closeDrawers();
            return true;
          }
        });
  }

  private void setupViewPager(ViewPager viewPager) {
    MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager());
    adapter.addFragment(new TimelineFragment(), "Timeline");
    adapter.addFragment(new NotificationFragment(), "Notification");
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
}
