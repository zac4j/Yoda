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
import com.bumptech.glide.Glide;
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
        View mRepostContentView;
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

            setupMediaContent(context, weibo.getBmiddlePic(), weibo.getPicUrls().size() >= 2);

            // 微博转发内容
            setRepostContent(context, weibo.getRepostWeibo());

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

        void setupMediaContent(Context context, String mediaUrl, boolean isMultipleMedia) {
            if (TextUtils.isEmpty(mediaUrl)) {
                mMediaContainer.removeAllViews();
                mMediaContainer.setVisibility(View.GONE);
                mRootLayout.removeView(mMediaContainer);
            } else {
                if (isMultipleMedia) {
                    // todo process multiple media
                    mMediaContainer.removeAllViews();
                    mMediaContainer.setVisibility(View.GONE);
                    mRootLayout.removeView(mMediaContainer);
                } else {
                    View singleImageContainer =
                        mLayoutInflater.inflate(R.layout.layout_weibo_single_image, null);
                    final ImageView imageHolder =
                        singleImageContainer.findViewById(R.id.weibo_media_iv_img);
                    WeiboImageLoader.loadSingleImage(context, imageHolder, mediaUrl);
                    mMediaContainer.addView(singleImageContainer);
                    mMediaContainer.setVisibility(View.VISIBLE);
                }
            }
        }

        // 没办法迁移，api 获取的数据跟翔一样
        void setRepostContent(Context context, Weibo repostWeibo) {
            final TextView repostTextView =
                mRepostContentView.findViewById(R.id.weibo_list_item_tv_repost_content);
            // 转发微博多媒体内容的容器
            final View mediaContainer =
                mRepostContentView.findViewById(R.id.weibo_list_item_tv_repost_media);
            final ImageView mediaImageView =
                mRepostContentView.findViewById(R.id.weibo_list_item_iv_repost_img);
            final TextView mediaImageType =
                mRepostContentView.findViewById(R.id.weibo_list_item_tv_repost_type);

            if (repostWeibo == null) {
                clearWeiboContainer(mRepostContentView, repostTextView, mediaImageView,
                    mediaImageType);
                mediaContainer.setVisibility(View.GONE);
            } else {
                String weiboContent = repostWeibo.getText();
                String name = repostWeibo.getUser().getScreenName();

                if (TextUtils.isEmpty(weiboContent)) {
                    clearWeiboContainer(mRepostContentView, repostTextView, mediaImageView,
                        mediaImageType);
                    return;
                }

                weiboContent = "@" + name + ": " + weiboContent;
                WeiboParser.setupText(repostTextView, weiboContent);
                mRepostContentView.setBackgroundResource(R.drawable.bg_gray_border);

                String imgUrl = repostWeibo.getBmiddlePic();
                boolean isMultiImages = repostWeibo.getPicUrls().size() > 1;

                //setRepostMediaContent(context, imgUrl, isMultiImages, mediaImageView, mediaImageType,
                //    mediaContainer);
            }
        }

        void clearWeiboContainer(View containerView, TextView weiboTextView,
            ImageView weiboMediaView, TextView weiboMediaTypeView) {
            containerView.setBackgroundResource(0);
            containerView.setPadding(0, 0, 0, 0);
            weiboTextView.setText("");
            // 我费尽千辛万苦终于发现你在这里 >>
            weiboTextView.setVisibility(View.GONE);
            Glide.with(mContext).clear(weiboMediaView);
            weiboMediaTypeView.setText("");
        }

        //void setRepostMediaContent(final Context context, final String mediaUrl,
        //    final boolean isMultiImage, final ImageView mediaImageView, final TextView mediaImageType,
        //    final View mediaContainer) {
        //
        //  if (TextUtils.isEmpty(mediaUrl)) {
        //    Glide.with(mContext).clear(mediaImageView);
        //    mediaImageType.setText("");
        //    mediaContainer.setVisibility(View.GONE);
        //  } else {
        //    mediaContainer.setVisibility(View.VISIBLE);
        //
        //    final RequestBuilder<Bitmap> bitmapRequestBuilder = Glide.with(context)
        //        .asBitmap()
        //        .load(mediaUrl)
        //        .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).fitCenter());
        //
        //    if (isMultiImage) {
        //      mediaImageType.setText(R.string.weibo_media_type_multiple_image);
        //      bitmapRequestBuilder.into(mediaImageView);
        //      return;
        //    }
        //
        //    if (mediaUrl.endsWith("gif")) {
        //      mediaImageType.setText(R.string.weibo_media_type_gif);
        //      bitmapRequestBuilder.into(mediaImageView);
        //      return;
        //    }
        //
        //    // Request has DiskCacheStrategy.ALL
        //    RequestBuilder builder =
        //        PhotoUtils.getNetworkImageSizeRequest(context).load(Uri.parse(mediaUrl));
        //    builder.into(new SimpleTarget<BitmapFactory.Options>() {
        //      @Override public void onResourceReady(BitmapFactory.Options resource,
        //          GlideAnimation<? super BitmapFactory.Options> glideAnimation) {
        //        int imageHeight = resource.outHeight;
        //        if (imageHeight >= PhotoUtils.LONG_IMAGE_LENGTH) {
        //          mediaImageType.setText(R.string.weibo_media_type_long_image);
        //          bitmapRequestBuilder.centerCrop().into(mediaImageView);
        //        } else {
        //          mediaImageType.setText("");
        //          bitmapRequestBuilder.fitCenter().into(mediaImageView);
        //        }
        //      }
        //    });
        //  }
        //}
        //
        //void setMediaContent(final Context context, final String mediaUrl, final boolean isMultiImage) {
        //  final ImageView mediaImageView = (ImageView) mMediaView.findViewById(R.id.weibo_media_iv_img);
        //  final TextView mediaImageType = (TextView) mMediaView.findViewById(R.id.weibo_media_tv_type);
        //  if (TextUtils.isEmpty(mediaUrl)) {
        //    Glide.clear(mediaImageView);
        //    mediaImageType.setText("");
        //    mMediaView.setVisibility(View.GONE);
        //  } else {
        //    mMediaView.setVisibility(View.VISIBLE);
        //
        //    final BitmapRequestBuilder<String, Bitmap> bitmapRequestBuilder =
        //        Glide.with(context).load(mediaUrl).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL);
        //
        //    // Request has DiskCacheStrategy.ALL
        //    GenericRequestBuilder builder =
        //        PhotoUtils.getNetworkImageSizeRequest(context).load(Uri.parse(mediaUrl));
        //    builder.into(new SimpleTarget<BitmapFactory.Options>() {
        //      @Override public void onResourceReady(BitmapFactory.Options resource,
        //          GlideAnimation<? super BitmapFactory.Options> glideAnimation) {
        //        int imageHeight = resource.outHeight;
        //        if (imageHeight >= PhotoUtils.LONG_IMAGE_LENGTH) {
        //          mediaImageType.setText(R.string.weibo_media_type_long_image);
        //          bitmapRequestBuilder.centerCrop().into(mediaImageView);
        //        } else {
        //          mediaImageType.setText("");
        //          bitmapRequestBuilder.fitCenter().into(mediaImageView);
        //        }
        //      }
        //    });
        //
        //    if (mediaUrl.endsWith("gif")) {
        //      mediaImageType.setText(R.string.weibo_media_type_gif);
        //      bitmapRequestBuilder.fitCenter().into(mediaImageView);
        //    }
        //
        //    if (isMultiImage) {
        //      mediaImageType.setText(R.string.weibo_media_type_multiple_image);
        //      bitmapRequestBuilder.fitCenter().into(mediaImageView);
        //    }
        //  }
        //}mediaImageView

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
            String media = weibo.getBmiddlePic();
            boolean isMultiImages = weibo.getPicUrls().size() > 1;
            //setMediaContent(context, media, isMultiImages);
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
