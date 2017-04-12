package com.zac4j.yoda.ui.weibo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.zac4j.yoda.R;
import com.zac4j.yoda.di.PerConfig;
import com.zac4j.yoda.ui.adapter.ImagePagerAdapter;
import com.zac4j.yoda.ui.base.BaseActivity;
import java.util.ArrayList;
import javax.inject.Inject;

/**
 * Image Viewer Activity
 * Created by zac on 3/29/2017.
 */

@PerConfig public class WeiboImageActivity extends BaseActivity {

  public static final String EXTRA_IMAGE_URI = "img_uri";
  public static final String EXTRA_IMAGE_LIST_URI = "img_list_uri";

  @Inject ImagePagerAdapter mImagePagerAdapter;

  @BindView(R.id.toolbar) Toolbar mToolbar;
  @BindView(R.id.weibo_image_vp_container) ViewPager mImageContainer;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_weibo_image);

    ButterKnife.bind(this);
    getActivityComponent().inject(this);

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
}
