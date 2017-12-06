package com.zac4j.yoda.ui.weibo;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.github.chrisbanes.photoview.PhotoView;
import com.zac4j.yoda.R;
import com.zac4j.yoda.data.model.ThumbUrl;
import com.zac4j.yoda.ui.adapter.ImagePagerAdapter;
import com.zac4j.yoda.ui.base.BaseActivity;
import java.util.ArrayList;
import javax.inject.Inject;

/**
 * Image Viewer Activity
 * Created by zac on 3/29/2017.
 */

public class WeiboImageActivity extends BaseActivity {

  public static final String EXTRA_IMAGE_LIST = "img_list_uri";
  public static final String EXTRA_IMAGE_URI = "img_uri";

  @Inject ImagePagerAdapter mImagePagerAdapter;

  @BindView(R.id.toolbar) Toolbar mToolbar;
  @BindView(R.id.weibo_image_vp_container) ViewPager mImageContainer;
  @BindView(R.id.progress_bar) ProgressBar mProgressBar;
  @BindView(R.id.photo_view) PhotoView mPhotoView;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_weibo_image);

    getActivityComponent().inject(this);
    ButterKnife.bind(this);

    if (mToolbar != null) {
      setSupportActionBar(mToolbar);
    }

    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      actionBar.setDisplayHomeAsUpEnabled(true);
    }

    // Single image to display.
    Uri imgUri = getIntent().getParcelableExtra(EXTRA_IMAGE_URI);
    if (imgUri != null) {
      mProgressBar.setVisibility(View.VISIBLE);
      Glide.with(this).load(imgUri).listener(new RequestListener<Drawable>() {
        @Override public boolean onLoadFailed(@Nullable GlideException e, Object model,
            Target<Drawable> target, boolean isFirstResource) {
          mProgressBar.setVisibility(View.GONE);
          return false;
        }

        @Override
        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target,
            DataSource dataSource, boolean isFirstResource) {
          mProgressBar.setVisibility(View.GONE);
          return false;
        }
      }).into(mPhotoView);
      mImageContainer.setVisibility(View.GONE);
      mPhotoView.setVisibility(View.VISIBLE);
      mPhotoView.setBackgroundColor(ContextCompat.getColor(WeiboImageActivity.this, R.color.black));
      return;
    } else {
      mPhotoView.setVisibility(View.GONE);
      mImageContainer.setVisibility(View.VISIBLE);
    }

    // An images array to display.
    ArrayList<ThumbUrl> imgUrlList =
        (ArrayList<ThumbUrl>) getIntent().getSerializableExtra(EXTRA_IMAGE_LIST);
    if (imgUrlList == null || imgUrlList.isEmpty()) {
      return;
    }
    mImageContainer.setAdapter(mImagePagerAdapter);
    mImagePagerAdapter.addImageUrlList(imgUrlList);
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
