package com.zac4j.yoda.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.zac4j.yoda.R;
import com.zac4j.yoda.data.model.ThumbUrl;
import com.zac4j.yoda.data.model.User;
import com.zac4j.yoda.data.model.Weibo;
import com.zac4j.yoda.di.ActivityContext;
import com.zac4j.yoda.ui.weibo.WeiboImageActivity;
import com.zac4j.yoda.util.WeiboImageLoader;
import com.zac4j.yoda.util.WeiboParser;
import com.zac4j.yoda.util.WeiboReader;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Home Weibo List Adapter
 * Created by zac on 3/17/2017.
 */

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.ViewHolder> {

    private OnItemClickListener mItemClickListener;
    private Context mContext;
    private List<Weibo> mWeiboList;
    private LayoutInflater mLayoutInflater;

    @Inject
    public TimelineAdapter(@ActivityContext Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mWeiboList = new ArrayList<>();
    }

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public void addWeiboList(final List<Weibo> weiboList) {
        List<Weibo> oldWeiboList = new ArrayList<>(mWeiboList);
        mWeiboList.addAll(weiboList);
        DiffUtil.DiffResult result =
            DiffUtil.calculateDiff(new DiffCallback(oldWeiboList, mWeiboList));
        result.dispatchUpdatesTo(TimelineAdapter.this);
    }

    public void clear() {
        if (mWeiboList != null) {
            mWeiboList.clear();
        }
        notifyDataSetChanged();
    }

    public boolean isEmpty() {
        return mWeiboList == null || mWeiboList.isEmpty();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item_weibo, parent, false);

        // Add view holder item click listener
        final ViewHolder holder = new ViewHolder(view);
        holder.itemView.setOnClickListener(v -> {
            int position = holder.getAdapterPosition();
            mItemClickListener.onClick(mWeiboList.get(position));
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (mWeiboList == null || mWeiboList.isEmpty()) {
            return;
        }
        // Happy view holder by Yigit Boyar
        holder.bindTo(mContext, mWeiboList.get(position));
    }

    @Override
    public int getItemCount() {
        if (mWeiboList == null || mWeiboList.isEmpty()) {
            return 0;
        }
        return mWeiboList.size();
    }

    public interface OnItemClickListener {
        void onClick(Weibo weibo);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.weibo_list_item_root_container)
        RelativeLayout mRootLayout;
        @BindView(R.id.weibo_list_item_iv_avatar)
        ImageView mAvatarView;
        @BindView(R.id.weibo_list_item_tv_nickname)
        TextView mNicknameView;
        @BindView(R.id.weibo_list_item_tv_username)
        TextView mUsernameView;
        @BindView(R.id.weibo_list_item_tv_post_time)
        TextView mPostTimeView;
        @BindView(R.id.weibo_list_item_tv_post_from)
        TextView mPostSourceView;
        @BindView(R.id.weibo_list_item_tv_content)
        TextView mContentView;
        @BindView(R.id.weibo_list_item_repost_container)
        ViewGroup mRepostContainer;
        @BindView(R.id.weibo_list_item_media_container)
        ViewGroup mMediaContainer;
        @BindView(R.id.weibo_list_item_bottom_btns)
        View mBottomBtns;
        @BindView(R.id.weibo_list_item_tv_repost)
        TextView mRepostBtn;
        @BindView(R.id.weibo_list_item_tv_comment)
        TextView mCommentBtn;
        @BindView(R.id.weibo_list_item_tv_like)
        TextView mLikeBtn;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(ViewHolder.this, itemView);
        }

        void bindTo(Context context, Weibo weibo) {
            // 发送时间
            WeiboReader.readPostTime(mPostTimeView, weibo.getCreatedAt());

            // 微博内容
            WeiboReader.readContent(mContentView, weibo.getText());

            // 转发内容
            setupMediaContent(mMediaContainer, weibo.getBmiddlePic());

            // 微博转发内容
            setupRepostContent(weibo.getRepostWeibo());

            // 转发数
            WeiboReader.readRepostNumber(mRepostBtn, weibo.getRepostsCount());
            // 评论数
            WeiboReader.readCommentsNumber(mCommentBtn, weibo.getCommentsCount());
            // 点赞数
            WeiboReader.readLikeState(mLikeBtn, weibo.getFavorited());
            WeiboReader.readLikeNumber(mLikeBtn, weibo.getAttitudesCount());

            // 发送来源
            WeiboReader.readPostSource(mPostSourceView, weibo.getSource());

            // 用户信息
            setupUserInfo(context, weibo.getUser());

            // 添加点击事件
            addMediaClickEvent(context, weibo);
        }

        private void setupMediaContent(ViewGroup mediaContainer, String mediaUrl) {
            if (mediaContainer.getChildCount() > 0) {
                mediaContainer.removeAllViews();
            }

            if (TextUtils.isEmpty(mediaUrl)) {
                return;
            }

            if (!TextUtils.isEmpty(mediaUrl)) {
                View singleImageContainer =
                    mLayoutInflater.inflate(R.layout.layout_weibo_single_image, mMediaContainer,
                        false);
                final ImageView imageHolder =
                    singleImageContainer.findViewById(R.id.weibo_media_iv_img);
                WeiboImageLoader.loadSingleImage(mContext, imageHolder, mediaUrl);
                mediaContainer.addView(singleImageContainer);
            }
        }

        private void setupRepostContent(Weibo repostWeibo) {

            if (mRepostContainer.getChildCount() > 0) {
                mRepostContainer.removeAllViews();
            }

            if (repostWeibo == null) {
                return;
            }

            View repostContentView =
                mLayoutInflater.inflate(R.layout.layout_repost_content, mRepostContainer, false);
            final TextView textContainer =
                repostContentView.findViewById(R.id.weibo_list_item_repost_text_container);
            final ViewGroup mediaContainer =
                repostContentView.findViewById(R.id.weibo_list_item_repost_media_container);

            String weiboContent = repostWeibo.getText();

            if (TextUtils.isEmpty(weiboContent)) {
                textContainer.setText("");
            } else {
                String name = repostWeibo.getUser().getScreenName();
                weiboContent = "@" + name + ": " + weiboContent;
                WeiboParser.setupText(textContainer, weiboContent);
            }

            setupMediaContent(mediaContainer, repostWeibo.getBmiddlePic());

            repostContentView.setBackgroundResource(R.drawable.bg_gray_border);
            mRepostContainer.addView(repostContentView);
        }

        private void setupUserInfo(Context context, User user) {
            if (user == null) {
                return;
            }

            WeiboReader.readAvatar(context, mAvatarView, user.getProfileImageUrl());
            WeiboReader.readNickname(mNicknameView, user.getScreenName());
            WeiboReader.readUsername(mUsernameView, user.getDomain());
        }

        /**
         * 设置图片点击事件
         */
        private void addMediaClickEvent(final Context context, Weibo weibo) {
            // 多媒体消息
            boolean isMultiImages = weibo.getPicUrls().size() > 1;
            ArrayList<ThumbUrl> imgUrlList = new ArrayList<>();
            if (isMultiImages) {
                imgUrlList = (ArrayList<ThumbUrl>) weibo.getPicUrls();
            } else {
                final String imgUrl = weibo.getOriginalPic();
                ThumbUrl thumbUrl = new ThumbUrl(imgUrl);
                imgUrlList.add(thumbUrl);
            }
            final Intent intent = new Intent(context, WeiboImageActivity.class);
            intent.putExtra(WeiboImageActivity.EXTRA_IMAGE_LIST, imgUrlList);
            mMediaContainer.setOnClickListener(v -> context.startActivity(intent));
        }
    }
}
