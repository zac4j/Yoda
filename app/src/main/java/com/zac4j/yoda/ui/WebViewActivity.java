package com.zac4j.yoda.ui;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.zac4j.yoda.R;

/**
 * WebView Activity
 * Created by zac on 4/5/2017.
 */

public class WebViewActivity extends AppCompatActivity {

  public static final String WEIBO_DOMAIN = "api.weibo.com";
  public static final String EXTRA_WEIBO_ID = "extra_weibo_id";
  public static final String EXTRA_UID = "extra_uid";

  @BindView(R.id.toolbar) Toolbar mToolbar;
  @BindView(R.id.progress_bar) ProgressBar mProgressBar;
  @BindView(R.id.web_view) WebView mWebView;

  @SuppressLint("SetJavaScriptEnabled") @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_webview);
    ButterKnife.bind(this);

    if (mToolbar != null) {
      setSupportActionBar(mToolbar);
    }

    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
      actionBar.setTitle(getString(R.string.weibo_detail));
    }

    String token = AccessTokenKeeper.readAccessToken(this).getToken();
    String weiboId = getIntent().getStringExtra(EXTRA_WEIBO_ID);
    String uid = getIntent().getStringExtra(EXTRA_UID);

    Uri.Builder builder = new Uri.Builder();
    builder.scheme("https")
        .authority(WEIBO_DOMAIN)
        .appendPath("2")
        .appendPath("statuses")
        .appendPath("go")
        .appendQueryParameter("access_token", token)
        .appendQueryParameter("id", weiboId)
        .appendQueryParameter("uid", uid);

    String url = builder.build().toString();

    if (mWebView == null) {
      return;
    }

    if (mProgressBar != null) {
      mProgressBar.setMax(100);
    }

    mWebView.getSettings().setJavaScriptEnabled(true);

    mWebView.setWebViewClient(new WebViewClient() {
      @Override public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
      }

      @TargetApi(Build.VERSION_CODES.LOLLIPOP) @Override
      public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        view.loadUrl(request.getUrl().toString());
        return true;
      }
    });

    mWebView.setWebChromeClient(new WebChromeClient() {
      @Override public void onProgressChanged(WebView view, int newProgress) {
        mProgressBar.setProgress(newProgress);
      }
    });
    mWebView.loadUrl(url);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        break;
    }
    return super.onOptionsItemSelected(item);
  }
}
