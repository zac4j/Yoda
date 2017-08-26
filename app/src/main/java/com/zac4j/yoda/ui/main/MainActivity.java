package com.zac4j.yoda.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.zac4j.yoda.R;
import com.zac4j.yoda.ui.adapter.MainPagerAdapter;
import com.zac4j.yoda.ui.base.BaseActivity;
import com.zac4j.yoda.ui.login.LoginActivity;
import com.zac4j.yoda.ui.weibo.send.WeiboSendActivity;

public class MainActivity extends BaseActivity {

  @BindView(R.id.toolbar) Toolbar mToolbar;
  @BindView(R.id.main_fab_write) FloatingActionButton mWriteBtn;
  @BindView(R.id.main_fragment_container) ViewPager mMainContainer;
  @BindView(R.id.main_bottom_navigation) BottomNavigationView mBottomNavigationView;

  private int mPreviousPosition;

  private ActionBar mActionBar;
  private int[] mPagerTitles = {
      R.string.main_nav_home, R.string.main_nav_hot, R.string.main_nav_notification,
      R.string.main_nav_message, R.string.main_nav_user
  };
  private int[] mBottomNavIds = {
      R.id.main_nav_home, R.id.main_nav_hot, R.id.main_nav_notification, R.id.main_nav_message,
      R.id.main_nav_user
  };

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    getActivityComponent().inject(this);
    ButterKnife.bind(this);

    setSupportActionBar(mToolbar);

    mActionBar = getSupportActionBar();

    if (mWriteBtn != null) {
      mWriteBtn.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          startActivity(new Intent(MainActivity.this, WeiboSendActivity.class));
        }
      });
    }

    mMainContainer.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));

    mBottomNavigationView.setOnNavigationItemSelectedListener(
        new BottomNavigationView.OnNavigationItemSelectedListener() {
          @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int position = 0;
            switch (item.getItemId()) {
              case R.id.main_nav_home:
                break;
              case R.id.main_nav_hot:
                position = 1;
                break;
              case R.id.main_nav_notification:
                position = 2;
                break;
              case R.id.main_nav_message:
                position = 3;
                break;
              case R.id.main_nav_user:
                position = 4;
                break;
            }

            if (mPreviousPosition == position) {
              return true;
            }

            updatePagerTitle(position);

            mMainContainer.setCurrentItem(position);

            mPreviousPosition = position;
            return true;
          }
        });

    mMainContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
      }

      @Override public void onPageSelected(int position) {
        mPreviousPosition = position;
        mBottomNavigationView.setSelectedItemId(mBottomNavIds[position]);
      }

      @Override public void onPageScrollStateChanged(int state) {
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
