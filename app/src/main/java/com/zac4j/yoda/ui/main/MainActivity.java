package com.zac4j.yoda.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.zac4j.yoda.R;
import com.zac4j.yoda.ui.base.BaseActivity;
import com.zac4j.yoda.ui.home.message.MessengerFragment;
import com.zac4j.yoda.ui.home.notification.NotificationFragment;
import com.zac4j.yoda.ui.home.hot.HotFragment;
import com.zac4j.yoda.ui.home.timeline.TimelineFragment;
import com.zac4j.yoda.ui.home.user.UserFragment;
import com.zac4j.yoda.ui.login.LoginActivity;
import com.zac4j.yoda.ui.weibo.send.WeiboSendActivity;

public class MainActivity extends BaseActivity {

  @BindView(R.id.toolbar) Toolbar mToolbar;
  @BindView(R.id.main_fab_write) FloatingActionButton mWriteBtn;
  @BindView(R.id.main_bottom_navigation) BottomNavigationView mBottomNavigationView;

  private ActionBar mActionBar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    getActivityComponent().inject(this);
    ButterKnife.bind(this);

    setSupportActionBar(mToolbar);

    mActionBar = getSupportActionBar();

    Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.main_fragment_container);
    if (fragment == null) {
      fragment = TimelineFragment.newInstance();
      getSupportFragmentManager().beginTransaction()
          .add(R.id.main_fragment_container, fragment)
          .commit();
    }

    if (mWriteBtn != null) {
      mWriteBtn.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          startActivity(new Intent(MainActivity.this, WeiboSendActivity.class));
        }
      });
    }

    mBottomNavigationView.setOnNavigationItemSelectedListener(
        new BottomNavigationView.OnNavigationItemSelectedListener() {
          @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment navigationFragment = TimelineFragment.newInstance();
            switch (item.getItemId()) {
              case R.id.main_nav_home:
                break;
              case R.id.main_nav_hot:
                mActionBar.setTitle(R.string.main_nav_hot);
                navigationFragment = HotFragment.newInstance();
                break;
              case R.id.main_nav_message:
                mActionBar.setTitle(R.string.main_nav_message);
                navigationFragment = MessengerFragment.newInstance();
                break;
              case R.id.main_nav_notification:
                mActionBar.setTitle(R.string.main_nav_notification);
                navigationFragment = NotificationFragment.newInstance();
                break;
              case R.id.main_nav_user:
                mActionBar.setTitle(R.string.main_nav_user);
                navigationFragment = UserFragment.newInstance();
                break;
            }

            Fragment currentFragment =
                getSupportFragmentManager().findFragmentById(R.id.main_fragment_container);
            if (currentFragment == navigationFragment) {
              return true;
            }

            getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_fragment_container, navigationFragment)
                .commit();
            return true;
          }
        });
  }

  public void onTokenInvalid() {
    AccessTokenKeeper.clear(this);
    startActivity(new Intent(this, LoginActivity.class));
  }
}
