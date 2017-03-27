package com.zac4j.yoda.ui.weibo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.text.TextUtilsCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.zac4j.yoda.R;
import com.zac4j.yoda.data.model.post.TextWeibo;
import com.zac4j.yoda.ui.base.BaseActivity;
import com.zac4j.yoda.ui.login.LoginActivity;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Send Weibo
 * Created by zac on 3/27/2017.
 */

public class SendWeiboActivity extends BaseActivity implements SendWeiboView {

  @Inject SendWeiboPresenter mPresenter;

  @BindView(R.id.toolbar) Toolbar mToolbar;
  @BindView(R.id.root_layout) View mRootView;
  @BindView(R.id.send_weibo_et_content) EditText mContentInput;
  @BindView(R.id.send_weibo_progressbar) ProgressBar mProgressBar;

  private final Map<String, String> mTextWeiboMap = new HashMap<>();

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_send_weibo);

    getActivityComponent().inject(this);
    ButterKnife.bind(this);
    mPresenter.attach(this);

    if (mToolbar != null) {
      setSupportActionBar(mToolbar);
    }

    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
    }

    setupToken();
  }

  private void setupToken() {
    String token = AccessTokenKeeper.readAccessToken(SendWeiboActivity.this).getToken();
    if (TextUtils.isEmpty(token)) {
      return;
    }
    mTextWeiboMap.put(getString(R.string.token), token);
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
        String weiboContent = mContentInput.getText().toString();
        if (TextUtils.isEmpty(weiboContent)) {
          showError("Not allowed empty weibo content !");
          return;
        }
        mTextWeiboMap.put(getString(R.string.text_weibo), weiboContent);
        mPresenter.sendTextWeibo(mTextWeiboMap);
        break;
    }
  }

  @Override public void showProgress(boolean show) {
    if (mProgressBar != null) {
      mProgressBar.setVisibility(show ? VISIBLE : GONE);
    }
  }

  @Override public void showError(String message) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
  }

  @Override public boolean isProcessing() {
    return mProgressBar != null && mProgressBar.isShown();
  }

  @Override public void onTokenInvalid() {
    AccessTokenKeeper.clear(this);
    startActivity(new Intent(this, LoginActivity.class));
  }

  @Override public void showMessage(String message) {
    Snackbar.make(mRootView, message, Snackbar.LENGTH_SHORT).show();
  }
}
