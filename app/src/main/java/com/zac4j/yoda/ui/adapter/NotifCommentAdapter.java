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
 * Adapter for Home Notification
 * Created by zac on 17-5-11.
 */

public class NotifCommentAdapter extends RecyclerView.Adapter<NotifCommentAdapter.ViewHolder> {

    private Context mContext;
    private List<Comment> mCommentList;

    @Inject
    public NotifCommentAdapter(@ActivityContext Context context) {
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

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
            LayoutInflater.from(mContext).inflate(R.layout.list_item_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mCommentList.isEmpty()) {
            return;
        }
        holder.bindTo(mCommentList.get(position));
    }

    @Override
    public int getItemCount() {
        if (mCommentList == null || mCommentList.isEmpty()) {
            return 0;
        }
        return mCommentList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.comment_list_item_iv_avatar)
        ImageView mAvatarView;
        @BindView(R.id.comment_list_item_tv_nickname)
        TextView mNicknameView;
        @BindView(R.id.comment_list_item_tv_username)
        TextView mUsernameView;
        @BindView(R.id.comment_list_item_tv_post_time)
        TextView mPostTimeView;
        @BindView(R.id.comment_list_item_tv_post_source)
        TextView mPostSourceView;
        @BindView(R.id.comment_list_item_tv_comment_content)
        TextView mCommentContentView;
        @BindView(R.id.comment_list_item_weibo_container)
        View mWeiboContainer;
        @BindView(R.id.comment_list_item_tv_weibo_content)
        TextView mWeiboContentView;
        @BindView(R.id.weibo_list_item_tv_repost)
        TextView mWeiboRepostBtn;
        @BindView(R.id.weibo_list_item_tv_comment)
        TextView mWeiboCommentBtn;
        @BindView(R.id.weibo_list_item_tv_like)
        TextView mWeiboLikeBtn;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindTo(Comment comment) {
            showCommentUser(comment.getUser());

            WeiboReader.readPostTime(mPostTimeView, comment.getCreatedAt());
            WeiboReader.readPostSource(mPostSourceView, comment.getSource());
            WeiboReader.readContent(mCommentContentView, comment.getText());
            if (comment.getWeibo() != null) {
                mWeiboContainer.setBackgroundResource(R.drawable.bg_gray_border);

                // weibo pattern: @nickname:contents
                String weiboContent =
                    "@" + comment.getWeibo().getUser().getScreenName() + "：" + comment.getWeibo()
                        .getText();
                WeiboReader.readContent(mWeiboContentView, weiboContent);

                mWeiboContainer.setVisibility(View.VISIBLE);

                WeiboReader.readLikeNumber(mWeiboLikeBtn, comment.getLikeCount());
                WeiboReader.readRepostNumber(mWeiboRepostBtn, comment.getWeibo().getRepostsCount());
                WeiboReader.readCommentsNumber(mWeiboCommentBtn, comment.getReplyCount());
            } else {
                mWeiboContainer.setBackgroundResource(0);
                mWeiboContainer.setVisibility(View.GONE);
            }
        }

        private void showCommentUser(User user) {
            if (user == null) {
                return;
            }
            WeiboReader.readAvatar(mContext, mAvatarView, user.getProfileImageUrl());
            WeiboReader.readNickname(mNicknameView, user.getScreenName());
            WeiboReader.readUsername(mUsernameView, user.getDomain());
        }
    }
}
