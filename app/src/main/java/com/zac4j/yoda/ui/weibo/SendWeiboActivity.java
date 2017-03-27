package com.zac4j.yoda.ui.weibo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.zac4j.yoda.R;
import com.zac4j.yoda.ui.base.BaseActivity;
import com.zac4j.yoda.ui.login.LoginActivity;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Send Weibo
 * Created by zac on 3/27/2017.
 */

public class SendWeiboActivity extends BaseActivity implements SendWeiboView {

  @BindView(R.id.toolbar) Toolbar mToolbar;
  @BindView(R.id.root_layout) View mRootView;
  @BindView(R.id.send_weibo_et_content) EditText mContentInput;
  @BindView(R.id.send_weibo_progressbar) ProgressBar mProgressBar;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_send_weibo);
    ButterKnife.bind(this);

    if (mToolbar != null) {
      setSupportActionBar(mToolbar);
    }

    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
    }
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        break;
    }
    return super.onOptionsItemSelected(item);
  }

  @OnClick({
      R.id.send_weibo_iv_action_camera, R.id.send_weibo_iv_action_gallery,
      R.id.send_weibo_iv_action_location, R.id.send_weibo_iv_action_visibility,
      R.id.send_weibo_tv_action_send
  }) public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.send_weibo_iv_action_camera:
        break;
      case R.id.send_weibo_iv_action_gallery:
        break;
      case R.id.send_weibo_iv_action_location:
        break;
      case R.id.send_weibo_iv_action_visibility:
        break;
      case R.id.send_weibo_tv_action_send:
        break;
    }
  }

  @Override public void showProgress(boolean show) {
    mProgressBar.setVisibility(show ? VISIBLE : GONE);
  }

  @Override public void showError(String message) {
    final Snackbar snackbar = Snackbar.make(mRootView, message, Snackbar.LENGTH_INDEFINITE);
    snackbar.setAction(R.string.ok, new View.OnClickListener() {
      @Override public void onClick(View v) {
        snackbar.dismiss();
      }
    });
  }

  @Override public void onTokenInvalid() {
    AccessTokenKeeper.clear(this);
    startActivity(new Intent(this, LoginActivity.class));
  }
}
