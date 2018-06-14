package com.zac4j.yoda.ui.adapter;

import android.content.Context;
import android.support.v7.util.DiffUtil;
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
import com.zac4j.yoda.ui.widget.WeiboView;
import com.zac4j.yoda.util.weibo.WeiboReader;
import java.util.List;
import javax.inject.Inject;

/**
 * Adapter for weibo comment
 * Created by Zheng on 6/23/2017.
 */

public class WeiboDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_WEIBO = 0x101;
    private static final int VIEW_TYPE_COMMENTS = 0x102;

    private Context mContext;
    private List<Object> mWeiboDetails;

    @Inject
    public WeiboDetailAdapter(@ActivityContext Context context) {
        mContext = context;
    }

    public void setWeiboDetails(List<Object> weiboDetails) {
        mWeiboDetails = weiboDetails;
    }

    public boolean isEmpty() {
        return mWeiboDetails == null || mWeiboDetails.isEmpty();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_WEIBO:
                View weiboView = LayoutInflater.from(mContext)
                    .inflate(R.layout.layout_weibo_container, parent, false);
                return new WeiboViewHolder(weiboView);
            case VIEW_TYPE_COMMENTS:
            default:
                View view = LayoutInflater.from(mContext)
                    .inflate(R.layout.list_item_comment, parent, false);
                return new CommentViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mWeiboDetails.isEmpty()) {
            return;
        }

        int viewType = getItemViewType(position);

        switch (viewType) {
            case VIEW_TYPE_WEIBO:
                WeiboViewHolder weiboViewHolder = (WeiboViewHolder) holder;
                WeiboView weiboView = (WeiboView) mWeiboDetails.get(position);
                ViewGroup itemContainer = (ViewGroup) weiboViewHolder.itemView;

                // The WeiboViewHolder recycled by the RecyclerView may be a different
                // instance than the one used previously for this position. Clear the
                // WeiboViewHolder of any subviews in case it has a different
                // WeiboView associated with it, and make sure the WeiboView for this position doesn't
                // already have a parent of a different recycled WeiboViewHolder.
                if (itemContainer.getChildCount() > 0) {
                    itemContainer.removeAllViews();
                }

                if (weiboView.getParent() != null) {
                    ((ViewGroup) weiboView.getParent()).removeView(weiboView);
                }

                itemContainer.addView(weiboView);
                break;
            case VIEW_TYPE_COMMENTS:
                Comment comment = (Comment) mWeiboDetails.get(position);
                ((CommentViewHolder) holder).bindTo(comment);
                break;
        }
    }

    @Override
    public int getItemCount() {
        if (mWeiboDetails == null || mWeiboDetails.isEmpty()) {
            return 0;
        }
        return mWeiboDetails.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? VIEW_TYPE_WEIBO : VIEW_TYPE_COMMENTS;
    }

    private static class WeiboViewHolder extends RecyclerView.ViewHolder {

        WeiboViewHolder(View itemView) {
            super(itemView);
        }
    }

    class CommentViewHolder extends RecyclerView.ViewHolder {

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

        CommentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindTo(Comment comment) {
            WeiboReader reader = WeiboReader.getInstance();
            reader.readPostTime(mPostTimeView, comment.getCreatedAt());
            reader.readPostSource(mPostSourceView, comment.getSource());
            reader.readTextContent(mCommentContentView, comment.getText());

            showCommentUser(comment.getUser());
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

    private static class DiffCallback extends DiffUtil.Callback {

        private List<Comment> mOldList;
        private List<Comment> mNewList;

        public DiffCallback(List<Comment> oldList, List<Comment> newList) {
            mOldList = oldList;
            mNewList = newList;
        }

        @Override
        public int getOldListSize() {
            return mOldList.size();
        }

        @Override
        public int getNewListSize() {
            return mNewList.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return mOldList.get(oldItemPosition)
                .getId()
                .equals(mNewList.get(newItemPosition).getId());
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return mOldList.get(oldItemPosition)
                .getText()
                .equals(mNewList.get(newItemPosition).getText());
        }
    }
}
