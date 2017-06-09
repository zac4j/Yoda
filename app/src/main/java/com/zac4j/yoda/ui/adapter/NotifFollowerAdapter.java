package com.zac4j.yoda.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import com.zac4j.yoda.data.model.User;
import com.zac4j.yoda.data.model.db.Profile;
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
  private List<User> mUserList;

  @Inject public NotifFollowerAdapter(@ActivityContext Context context) {
    mContext = context;
    mUserList = new ArrayList<>();
  }

  public void addAvatarList(List<User> userList) {
    if (userList == null || userList.isEmpty()) {
      return;
    }

    mUserList.addAll(userList);
    notifyDataSetChanged();
  }

  public boolean isEmpty() {
    return mUserList == null || mUserList.isEmpty();
  }

  @Override public int getCount() {
    if (mUserList == null || mUserList.isEmpty()) {
      return 0;
    }
    return mUserList.size();
  }

  @Override public User getItem(int position) {
    return mUserList.get(position);
  }

  @Override public long getItemId(int position) {
    return mUserList.get(position).getId();
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

    User user = getItem(position);

    WeiboReader.readAvatar(mContext, imageView, user.getProfileImageUrl());

    return imageView;
  }
}
