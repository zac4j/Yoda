package com.zac4j.yoda.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import com.zac4j.yoda.di.ActivityContext;
import com.zac4j.yoda.util.WeiboReader;
import com.zac4j.yoda.util.image.PhotoUtils;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Follower avatar grid adapter
 * Created by zaccc on 6/8/2017.
 */

public class NotifFollowerAdapter extends BaseAdapter {

  private Context mContext;
  private List<String> mAvatarUrlList;

  @Inject public NotifFollowerAdapter(@ActivityContext Context context) {
    mContext = context;
    mAvatarUrlList = new ArrayList<>();
  }

  public void addAvatarList(List<String> avatarUrlList) {
    if (avatarUrlList == null || avatarUrlList.isEmpty()) {
      return;
    }

    mAvatarUrlList.addAll(avatarUrlList);
    notifyDataSetChanged();
  }

  public boolean isEmpty() {
    return mAvatarUrlList == null || mAvatarUrlList.isEmpty();
  }

  @Override public int getCount() {
    if (mAvatarUrlList == null || mAvatarUrlList.isEmpty()) {
      return 0;
    }
    return mAvatarUrlList.size();
  }

  @Override public String getItem(int position) {
    return mAvatarUrlList.get(position);
  }

  @Override public long getItemId(int position) {
    return position;
  }

  @Override public View getView(int position, View convertView, ViewGroup parent) {
    ImageView imageView;
    if (convertView == null) {
      imageView = new ImageView(mContext);
      imageView.setLayoutParams(new GridView.LayoutParams((int) PhotoUtils.dpToPixel(48),
          (int) PhotoUtils.dpToPixel(48)));
    } else {
      imageView = (ImageView) convertView;
    }
    WeiboReader.readAvatar(mContext, imageView, getItem(position));
    return imageView;
  }
}
