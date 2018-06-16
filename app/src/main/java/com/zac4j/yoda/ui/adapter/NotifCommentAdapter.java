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
import com.zac4j.yoda.util.weibo.WeiboReader;
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
            WeiboReader reader = WeiboReader.getInstance();
            reader.readPostTime(mPostTimeView, comment.getCreatedAt());
            reader.readTextContent(mCommentContentView, comment.getText());
            if (comment.getWeibo() != null) {
                mWeiboContainer.setBackgroundResource(R.drawable.bg_gray_border);

                // weibo pattern: @nickname:contents
                String weiboContent =
                    "@" + comment.getWeibo().getUser().getScreenName() + "：" + comment.getWeibo()
                        .getText();
                reader.readTextContent(mWeiboContentView, weiboContent);

                mWeiboContainer.setVisibility(View.VISIBLE);

                reader.readLikeNumber(mWeiboLikeBtn, comment.getWeibo().getAttitudesCount());
                reader.readRepostNumber(mWeiboRepostBtn, comment.getWeibo().getRepostsCount());
                reader.readCommentsNumber(mWeiboCommentBtn, comment.getWeibo().getCommentsCount());
            } else {
                mWeiboContainer.setBackgroundResource(0);
                mWeiboContainer.setVisibility(View.GONE);
            }
        }

        private void showCommentUser(User user) {
            if (user == null) {
                return;
            }
            WeiboReader reader = WeiboReader.getInstance();
            reader.readAvatar(mAvatarView, user.getProfileImageUrl());
            reader.readNickname(mNicknameView, user.getScreenName());
            reader.readUsername(mUsernameView, user.getDomain());
        }
    }
}
