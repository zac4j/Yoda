package com.zac4j.yoda.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.zac4j.yoda.R;
import com.zac4j.yoda.ui.base.BaseActivity;
import com.zac4j.yoda.ui.login.LoginActivity;

/**
 * UI for User
 * Created by Zac on 2017/3/24.
 */

public class UserActivity extends BaseActivity {

  @BindView(R.id.user_toolbar) Toolbar mToolbar;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user);
    ButterKnife.bind(this);

    if (mToolbar != null) {
      setSupportActionBar(mToolbar);
    }

    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
      actionBar.setTitle(R.string.nav_drawer_my_moments);
    }

    FragmentManager fragmentMgr = getSupportFragmentManager();
    Fragment fragment = fragmentMgr.findFragmentById(R.id.user_fragment_container);
    if (fragment == null) {
      fragment = new UserTimelineFragment();
      fragmentMgr.beginTransaction().add(R.id.user_fragment_container, fragment).commit();
    }
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        UserActivity.this.finish();
    }
    return true;
  }

  public void onTokenInvalid() {
    AccessTokenKeeper.clear(this);
    startActivity(new Intent(this, LoginActivity.class));
  }
}
