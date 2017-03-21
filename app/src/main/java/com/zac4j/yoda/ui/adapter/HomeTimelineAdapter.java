package com.zac4j.yoda.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.zac4j.yoda.R;
import com.zac4j.yoda.data.model.Weibo;
import com.zac4j.yoda.di.ActivityContext;
import com.zac4j.yoda.util.img.RoundTransformation;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;

/**
 * Home Weibo List Adapter
 * Created by zac on 3/17/2017.
 */

public class HomeTimelineAdapter extends RecyclerView.Adapter<HomeTimelineAdapter.ViewHolder> {

  private Context mContext;
  private List<Weibo> mWeiboList;

  @Inject public HomeTimelineAdapter(@ActivityContext Context context) {
    mContext = context;
    mWeiboList = Collections.emptyList();
  }

  public void setWeiboList(List<Weibo> weiboList) {
    if (weiboList == null || weiboList.isEmpty()) {
      return;
    }
    mWeiboList.addAll(weiboList);
    notifyDataSetChanged();
  }

  public void clear() {
    mWeiboList.clear();
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.item_home_weibo_list, parent, false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    if (mWeiboList == null || mWeiboList.isEmpty()) {
      return;
    }
    Weibo weibo = mWeiboList.get(position);
    String content = weibo.getText();
    holder.setContent(content);
  }

  @Override public int getItemCount() {
    if (mWeiboList == null || mWeiboList.isEmpty()) {
      return 0;
    }
    return mWeiboList.size();
  }

  static class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.weibo_list_item_iv_avatar) ImageView mAvatarView;
    @BindView(R.id.weibo_list_item_tv_nickname) TextView mNicknameView;
    @BindView(R.id.weibo_list_item_tv_username) TextView mUsernameView;
    @BindView(R.id.weibo_list_item_tv_post_time) TextView mPostTimeView;
    @BindView(R.id.weibo_list_item_tv_post_from) TextView mPostSourceView;
    @BindView(R.id.weibo_list_item_tv_content) TextView mContentView;
    @BindView(R.id.weibo_list_item_media_container) ViewStub mMediaContainer;
    @BindView(R.id.weibo_list_item_tv_repost) TextView mRepostBtn;
    @BindView(R.id.weibo_list_item_tv_reply) TextView mReplyBtn;
    @BindView(R.id.weibo_list_item_tv_like) TextView mLikeBtn;

    ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(ViewHolder.this, itemView);
    }

    public void setAvatar(Context context, String imageUrl) {
      if (TextUtils.isEmpty(imageUrl)) {
        return;
      }
      Glide.with(context)
          .load(imageUrl)
          .transform(new RoundTransformation(context))
          .into(mAvatarView);
    }

    public void setNickname(String nickname) {
      if (TextUtils.isEmpty(nickname)) {
        return;
      }
      mNicknameView.setText(nickname);
    }

    public void setUsernameView(String username) {
      if (TextUtils.isEmpty(username)) {
        return;
      }
      mUsernameView.setText(username);
    }

    public void setPostTime(String postTime) {
      if (TextUtils.isEmpty(postTime)) {
        return;
      }
      mPostTimeView.setText(postTime);
    }

    public void setPostSource(String postSource) {
      if (TextUtils.isEmpty(postSource)) {
        return;
      }
      mPostSourceView.setText(postSource);
    }

    public void setContent(String content) {
      if (TextUtils.isEmpty(content)) {
        return;
      }
      mContentView.setText(content);
    }

    public void setMediaContainer(ViewStub mediaContainer) {
      mMediaContainer = mediaContainer;
    }

    public void setRepostNumber(int repostNumber) {
      mRepostBtn.setText(String.format(Locale.getDefault(), "%d", repostNumber));
    }

    public void setReplyNumber(int replyNumber) {
      mReplyBtn.setText(String.format(Locale.getDefault(), "%d", replyNumber));
    }

    public void setLikeNumber(int likeNumber) {
      mLikeBtn.setText(String.format(Locale.getDefault(), "%d", likeNumber));
    }
  }
}
