package com.zac4j.yoda.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.zac4j.yoda.R;
import com.zac4j.yoda.data.model.User;
import com.zac4j.yoda.di.ActivityContext;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Adapter for User Friend List
 * Created by zac on 4/14/2017.
 */

public class FriendListAdapter
    extends RecyclerView.Adapter<FriendListAdapter.FriendListViewHolder> {

  private Context mContext;
  private List<User> mFriendList;

  @Inject public FriendListAdapter(@ActivityContext Context context) {
    mContext = context;
    mFriendList = new ArrayList<>();
  }

  public void addFriendList(List<User> friendList) {
    if (friendList == null || friendList.isEmpty()) {
      return;
    }
    mFriendList.addAll(friendList);
    notifyDataSetChanged();
  }

  public void clear() {
    if (mFriendList != null) {
      mFriendList.clear();
    }
    notifyDataSetChanged();
  }

  @Override public FriendListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(mContext);
    View view = inflater.inflate(R.layout.list_item_user_friend, parent, false);
    return new FriendListViewHolder(view);
  }

  @Override public void onBindViewHolder(FriendListViewHolder holder, int position) {
    if (mFriendList == null || mFriendList.isEmpty()) {
      return;
    }

    final User user = mFriendList.get(position);

    if (user == null) {
      return;
    }

    // Set user avatar
    String avatarUrl = user.getProfileImageUrl();
    holder.setAvatar(mContext, avatarUrl);

    // Set user nickname
    String nickname = user.getScreenName();
    holder.setNickname(nickname);

    // set user Description
    String description = user.getDescription();
    holder.setDescription(description);
  }

  @Override public int getItemCount() {
    if (mFriendList == null || mFriendList.isEmpty()) {
      return 0;
    }
    return mFriendList.size();
  }

  static class FriendListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.user_friend_list_iv_avatar) ImageView mAvatarView;
    @BindView(R.id.user_friends_list_tv_nickname) TextView mNicknameView;
    @BindView(R.id.user_friends_list_tv_description) TextView mDescriptionView;

    public FriendListViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    void setAvatar(Context context, String avatarUrl) {
      if (TextUtils.isEmpty(avatarUrl)) {
        Glide.clear(mAvatarView);
      } else {
        Glide.with(context).load(avatarUrl).fitCenter().into(mAvatarView);
      }
    }

    void setNickname(String nickname) {
      if (TextUtils.isEmpty(nickname)) {
        mNicknameView.setText("");
      } else {
        mNicknameView.setText(nickname);
      }
    }

    void setDescription(String description) {
      if (TextUtils.isEmpty(description)) {
        mDescriptionView.setText("");
      } else {
        mDescriptionView.setText(description);
      }
    }
  }
}
