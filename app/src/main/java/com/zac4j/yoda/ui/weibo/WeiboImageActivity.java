package com.zac4j.yoda.ui.weibo;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.zac4j.yoda.R;

/**
 * Image Viewer Activity
 * Created by zac on 3/29/2017.
 */

public class WeiboImageActivity extends AppCompatActivity {

  public static final String EXTRA_IMAGE_URI = "img_uri";

  @BindView(R.id.toolbar) Toolbar mToolbar;
  @BindView(R.id.weibo_image_iv_container) ImageView mImageContainer;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_weibo_image);
    ButterKnife.bind(this);

    if (mToolbar != null) {
      setSupportActionBar(mToolbar);
    }

    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
    }

    Uri imageUri = getIntent().getParcelableExtra(EXTRA_IMAGE_URI);
    if (imageUri != null) {
      mImageContainer.setImageURI(imageUri);
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
