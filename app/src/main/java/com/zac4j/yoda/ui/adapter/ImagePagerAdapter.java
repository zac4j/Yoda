package com.zac4j.yoda.ui.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.zac4j.yoda.R;
import com.zac4j.yoda.data.model.ThumbUrl;
import com.zac4j.yoda.di.ActivityContext;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Image View Pager Adapter
 * Created by zac on 4/12/2017.
 */

public class ImagePagerAdapter extends PagerAdapter {

  private Context mContext;
  private List<ThumbUrl> mImageUrlList;

  @Inject public ImagePagerAdapter(@ActivityContext Context context) {
    mContext = context;
    mImageUrlList = new ArrayList<>();
  }

  public void addImageUrlList(List<ThumbUrl> imageUrlList) {
    if (imageUrlList == null || imageUrlList.isEmpty()) {
      return;
    }
    mImageUrlList.addAll(imageUrlList);
    notifyDataSetChanged();
  }

  @Override public int getCount() {
    if (mImageUrlList == null || mImageUrlList.isEmpty()) {
      return 0;
    }
    return mImageUrlList.size();
  }

  @Override public boolean isViewFromObject(View view, Object object) {
    return view == object;
  }

  @Override public Object instantiateItem(ViewGroup container, int position) {

    PhotoView photoView = new PhotoView(mContext);

    final String imgUrl =
        mImageUrlList.get(position).getThumbnailPic().replaceAll("thumbnail", "large");

    Glide.with(mContext)
        .load(imgUrl)
        .placeholder(android.R.drawable.progress_indeterminate_horizontal)
        .into(photoView);

    photoView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.black));

    container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT);
    return photoView;
  }

  @Override public void destroyItem(ViewGroup container, int position, Object object) {
    container.removeView((View) object);
  }
}
