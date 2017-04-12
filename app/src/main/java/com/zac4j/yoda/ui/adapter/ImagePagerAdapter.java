package com.zac4j.yoda.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Image View Pager Adapter
 * Created by zac on 4/12/2017.
 */

public class ImagePagerAdapter extends PagerAdapter {

  private Context mContext;
  private List<String> mImageUrlList;

  @Inject public ImagePagerAdapter(Context context) {
    mContext = context;
    mImageUrlList = new ArrayList<>();
  }

  public void addImageUrlList(List<String> imageUrlList) {
    if (imageUrlList == null || imageUrlList.isEmpty()) {
      return;
    }
    mImageUrlList.addAll(imageUrlList);
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

    Glide.with(mContext)
        .load(mImageUrlList.get(position))
        .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
        .into(photoView);

    container.addView(photoView, ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT);
    return photoView;
  }

  @Override public void destroyItem(ViewGroup container, int position, Object object) {
    container.removeView((View) object);
  }
}
