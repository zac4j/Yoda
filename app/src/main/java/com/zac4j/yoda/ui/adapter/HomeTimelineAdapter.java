package com.zac4j.yoda.ui.adapter;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
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
import com.zac4j.yoda.data.model.User;
import com.zac4j.yoda.data.model.Weibo;
import com.zac4j.yoda.di.ActivityContext;
import com.zac4j.yoda.util.TimeUtils;
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
    mWeiboList = weiboList;
    notifyDataSetChanged();
  }

  public void addWeiboList(List<Weibo> weiboList) {
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

  @SuppressWarnings("deprecation") @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    if (mWeiboList == null || mWeiboList.isEmpty()) {
      return;
    }
    Weibo weibo = mWeiboList.get(position);
    // 发送时间
    String pattern = "E MMM dd HH:mm:ss Z yyyy";
    String dateStr = TimeUtils.getYmd(weibo.getCreatedAt(), pattern);
    holder.setPostTime(dateStr);
    // 微博内容
    String content = weibo.getText();
    holder.setContent(content);
    // 转发数
    long repostCount = weibo.getRepostsCount();
    holder.setRepostNumber(repostCount);
    // 评论数
    long commentCount = weibo.getCommentsCount();
    holder.setReplyNumber(commentCount);
    // 点赞数
    long likeCount = weibo.getAttitudesCount();
    holder.setLikeNumber(likeCount);

    // 发送来源
    String source = weibo.getSource();
    if (!TextUtils.isEmpty(source)) {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        holder.setPostSource(Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY));
      } else {
        holder.setPostSource(Html.fromHtml(source));
      }
    }
    // 用户信息
    setUserInfo(holder, weibo.getUser());

    // 多媒体消息
    String media = weibo.getBmiddlePic();
    holder.setMediaContent(mContext, media);
  }

  private void setUserInfo(ViewHolder holder, User user) {
    if (user == null) {
      return;
    }
    holder.setAvatar(mContext, user.getProfileImageUrl());
    holder.setNickname(user.getScreenName());
    String username = "@" + user.getDomain();
    holder.setUsername(username);
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
    @BindView(R.id.weibo_list_item_media_container) ImageView mMediaContainer;
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

    public void setUsername(String username) {
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

    public void setPostSource(Spanned postSource) {
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

    public void setMediaContent(Context context, String mediaUrl) {
      if (TextUtils.isEmpty(mediaUrl)) {
        return;
      }
      Glide.with(context).load(mediaUrl).fitCenter().into(mMediaContainer);
      mMediaContainer.setVisibility(View.VISIBLE);
    }

    public void setRepostNumber(long repostNumber) {
      mRepostBtn.setText(String.format(Locale.getDefault(), "%d", repostNumber));
    }

    public void setReplyNumber(long replyNumber) {
      mReplyBtn.setText(String.format(Locale.getDefault(), "%d", replyNumber));
    }

    public void setLikeNumber(long likeNumber) {
      mLikeBtn.setText(String.format(Locale.getDefault(), "%d", likeNumber));
    }
  }
}
