package com.zac4j.yoda.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.zac4j.yoda.R;
import com.zac4j.yoda.ui.base.BaseActivity;
import com.zac4j.yoda.ui.home.hot.HotTagFragment;
import com.zac4j.yoda.ui.home.message.MessengerFragment;
import com.zac4j.yoda.ui.home.notif.NotificationFragment;
import com.zac4j.yoda.ui.home.timeline.TimelineFragment;
import com.zac4j.yoda.ui.home.user.UserFragment;
import com.zac4j.yoda.ui.login.LoginActivity;
import com.zac4j.yoda.ui.weibo.send.WeiboSendActivity;

public class MainActivity extends BaseActivity {

  @BindView(R.id.toolbar) Toolbar mToolbar;
  @BindView(R.id.main_fab_write) FloatingActionButton mWriteBtn;
  @BindView(R.id.main_bottom_navigation) BottomNavigationView mBottomNavigationView;

  private int mPreviousPosition;

  private ActionBar mActionBar;
  private SparseArray<Fragment> mHomePagers = new SparseArray<>(5);
  private int[] mPagerTitles = {
      R.string.main_nav_home, R.string.main_nav_hot, R.string.main_nav_message,
      R.string.main_nav_notification, R.string.main_nav_user
  };

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
      mHomePagers.put(0, fragment);

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
            int position = 0;
            switch (item.getItemId()) {
              case R.id.main_nav_home:
                if (mHomePagers.get(position) == null) {
                  mHomePagers.put(position, TimelineFragment.newInstance());
                }
                break;
              case R.id.main_nav_hot:
                position = 1;
                if (mHomePagers.get(position) == null) {
                  mHomePagers.put(position, HotTagFragment.newInstance());
                }
                break;
              case R.id.main_nav_message:
                position = 2;
                if (mHomePagers.get(position) == null) {
                  mHomePagers.put(position, MessengerFragment.newInstance());
                }
                break;
              case R.id.main_nav_notification:
                position = 3;
                if (mHomePagers.get(position) == null) {
                  mHomePagers.put(position, NotificationFragment.newInstance());
                }
                break;
              case R.id.main_nav_user:
                position = 4;
                if (mHomePagers.get(position) == null) {
                  mHomePagers.put(position, UserFragment.newInstance());
                }
                break;
            }

            if (mPreviousPosition == position) {
              return true;
            }

            updatePagerTitle(position);

            getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_fragment_container, mHomePagers.get(position))
                .commit();

            // Why not work ?
            //getSupportFragmentManager().beginTransaction()
            //    .hide(mHomePagers.get(mPreviousPosition))
            //    .show(mHomePagers.get(position))
            //    .commit();

            mPreviousPosition = position;
            return true;
          }
        });
  }

  /**
   * Update pager title
   *
   * @param position position for current pager
   */
  private void updatePagerTitle(int position) {
    mActionBar.setTitle(mPagerTitles[position]);
  }

  public void onTokenInvalid() {
    AccessTokenKeeper.clear(this);
    startActivity(new Intent(this, LoginActivity.class));
  }
}
