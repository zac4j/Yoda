package com.zac4j.yoda.ui;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import com.zac4j.yoda.ui.base.BaseActivity;

public class BrowserActivity extends BaseActivity {

    public static final String EXTRA_LINK = "link";
    private WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(prepareContentView());

        // Check whether we're recreating a previously destroyed instance
        if (savedInstanceState != null) {
            // Restore the previous URL and history stack
            mWebView.restoreState(savedInstanceState);
        }

        setUpWebViewDefaults();
    }

    /**
     * Convenience method to set some generic defaults for a
     * given WebView
     */
    @SuppressLint("SetJavaScriptEnabled")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setUpWebViewDefaults() {
        WebSettings settings = mWebView.getSettings();

        // Enable Javascript
        settings.setJavaScriptEnabled(true);

        // Use WideViewport and Zoom out if there is no viewport defined
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);

        // Enable pinch to zoom without the zoom buttons
        settings.setBuiltInZoomControls(true);

        // Hide the zoom controls for HONEYCOMB+
        settings.setDisplayZoomControls(false);

        // Enable remote debugging via chrome://inspect
        WebView.setWebContentsDebuggingEnabled(true);

        // We set the WebViewClient to ensure links are consumed by the WebView rather
        // than passed to a browser if it can
        mWebView.setWebViewClient(new WebViewClient());
    }

    private View prepareContentView() {
        FrameLayout layout = new FrameLayout(this);
        mWebView = new WebView(getApplicationContext());
        FrameLayout.LayoutParams layoutParams =
            new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        layout.addView(mWebView, layoutParams);
        return layout;
    }

    private void destroyWebView() {
        if (mWebView != null) {
            mWebView.clearHistory();

            // NOTE: clears RAM cache, if you pass true, it will also clear the disk cache.
            // Probably not a great idea to pass true if you have other WebViews still alive.
            mWebView.clearCache(true);

            // Loading a blank page is optional, but will ensure that the WebView isn't doing anything when you destroy it.
            mWebView.loadUrl("about:blank");

            mWebView.onPause();
            mWebView.removeAllViews();
            mWebView.destroyDrawingCache();

            // NOTE: This pauses JavaScript execution for ALL WebViews,
            // do not use if you have other WebViews still alive.
            // If you create another WebView after calling this,
            // make sure to call mWebView.resumeTimers().
            mWebView.pauseTimers();

            // NOTE: This can occasionally cause a segfault below API 17 (4.2)
            mWebView.destroy();

            // Null out the reference so that you don't end up re-using it.
            mWebView = null;
        }
    }

}
