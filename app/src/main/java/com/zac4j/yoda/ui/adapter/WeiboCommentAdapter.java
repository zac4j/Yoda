package com.zac4j.yoda.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.zac4j.yoda.R;
import com.zac4j.yoda.data.model.Comment;
import com.zac4j.yoda.data.model.User;
import com.zac4j.yoda.di.ActivityContext;
import com.zac4j.yoda.util.WeiboReader;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Adapter for weibo comment
 * Created by Zheng on 6/23/2017.
 */

public class WeiboCommentAdapter extends RecyclerView.Adapter<WeiboCommentAdapter.ViewHolder> {

  private Context mContext;
  private List<Comment> mCommentList;

  @Inject public WeiboCommentAdapter(@ActivityContext Context context) {
    mContext = context;
    mCommentList = new ArrayList<>();
  }

  public void addCommentList(List<Comment> comments) {
    if (comments == null || comments.isEmpty()) {
      return;
    }

    mCommentList.addAll(comments);
    notifyDataSetChanged();
  }

  public boolean isEmpty() {
    return mCommentList == null || mCommentList.isEmpty();
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_comment, parent, false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    if (mCommentList.isEmpty()) {
      return;
    }

    holder.bindTo(mContext, mCommentList.get(position));
  }

  @Override public int getItemCount() {
    if (mCommentList == null || mCommentList.isEmpty()) {
      return 0;
    }
    return mCommentList.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.comment_list_item_iv_avatar) ImageView mAvatarView;
    @BindView(R.id.comment_list_item_tv_nickname) TextView mNicknameView;
    @BindView(R.id.comment_list_item_tv_username) TextView mUsernameView;
    @BindView(R.id.comment_list_item_tv_post_time) TextView mPostTimeView;
    @BindView(R.id.comment_list_item_tv_post_source) TextView mPostSourceView;
    @BindView(R.id.comment_list_item_tv_comment_content) TextView mCommentContentView;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    void bindTo(Context context, Comment comment) {
      WeiboReader.readPostTime(mPostTimeView, comment.getCreatedAt());
      WeiboReader.readPostSource(mPostSourceView, comment.getSource());
      WeiboReader.readContent(mCommentContentView, comment.getText());

      showCommentUser(context, comment.getUser());
    }

    private void showCommentUser(Context context, User user) {
      if (user == null) {
        return;
      }
      WeiboReader.readAvatar(context, mAvatarView, user.getProfileImageUrl());
      WeiboReader.readNickname(mNicknameView, user.getScreenName());
      WeiboReader.readUsername(mUsernameView, user.getDomain());
    }
  }
}
