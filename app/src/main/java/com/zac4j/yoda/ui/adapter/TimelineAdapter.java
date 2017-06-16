package com.zac4j.yoda.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.BitmapRequestBuilder;
import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.zac4j.yoda.R;
import com.zac4j.yoda.data.model.ThumbUrl;
import com.zac4j.yoda.data.model.User;
import com.zac4j.yoda.data.model.Weibo;
import com.zac4j.yoda.di.ActivityContext;
import com.zac4j.yoda.ui.WebViewActivity;
import com.zac4j.yoda.ui.main.MainActivity;
import com.zac4j.yoda.ui.weibo.WeiboImageActivity;
import com.zac4j.yoda.ui.weibo.detail.WeiboDetailActivity;
import com.zac4j.yoda.ui.weibo.repost.WeiboRepostDialogFragment;
import com.zac4j.yoda.util.RxUtils;
import com.zac4j.yoda.util.WeiboParser;
import com.zac4j.yoda.util.WeiboReader;
import com.zac4j.yoda.util.image.PhotoUtils;
import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Home Weibo List Adapter
 * Created by zac on 3/17/2017.
 */

public class TimelineAdapter extends RecyclerView.Adapter<TimelineAdapter.ViewHolder> {

  private Context mContext;
  private List<Weibo> mWeiboList;

  @Inject public TimelineAdapter(@ActivityContext Context context) {
    mContext = context;
    mWeiboList = new ArrayList<>();
  }

  public void addWeiboList(final List<Weibo> weiboList) {
    if (mWeiboList.isEmpty()) {
      mWeiboList.addAll(weiboList);
      notifyDataSetChanged();
      return;
    }

    Observable.fromIterable(weiboList).filter(weibo -> {
      // 为了去除重复的 weibo 请求结果, yep cuz of a bad server api.
      return !mWeiboList.contains(weibo);
    }).compose(RxUtils.applyObservableSchedulers()).subscribeWith(new DisposableObserver<Weibo>() {
      @Override public void onNext(Weibo weibo) {
        mWeiboList.add(weibo);
      }

      @Override public void onError(Throwable throwable) {
        System.out.println(throwable.getMessage());
      }

      @Override public void onComplete() {
        TimelineAdapter.this.notifyDataSetChanged();
      }
    });
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

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.list_item_weibo, parent, false);
    return new ViewHolder(view);
  }

  @SuppressWarnings("deprecation") @Override
  public void onBindViewHolder(final ViewHolder holder, int position) {
    if (mWeiboList == null || mWeiboList.isEmpty()) {
      return;
    }
    final Weibo weibo = mWeiboList.get(position);

    // 发送时间
    WeiboReader.readPostTime(holder.mPostTimeView, weibo.getCreatedAt());

    // 微博内容
    WeiboReader.readContent(holder.mContentView, weibo.getText());

    // 微博转发内容
    holder.setRepostContent(mContext, weibo.getRepostWeibo());

    // 转发数
    WeiboReader.readRepostNumber(holder.mRepostBtn, weibo.getRepostsCount());
    // 评论数
    WeiboReader.readCommentsNumber(holder.mCommentBtn, weibo.getCommentsCount());
    // 点赞数
    WeiboReader.readLikeState(holder.mLikeBtn, weibo.getFavorited());
    WeiboReader.readLikeNumber(holder.mLikeBtn, weibo.getAttitudesCount());

    // 发送来源
    WeiboReader.readPostSource(holder.mPostSourceView, weibo.getSource());

    // 用户信息
    setupUserInfo(holder, weibo.getUser());

    // 多媒体消息
    String media = weibo.getBmiddlePic();
    boolean isMultiImages = weibo.getPicUrls().size() > 1;
    holder.setMediaContent(mContext, media, isMultiImages);
    ArrayList<ThumbUrl> imgUrlList = new ArrayList<>();
    if (isMultiImages) {
      imgUrlList = (ArrayList<ThumbUrl>) weibo.getPicUrls();
    } else {
      final String imgUrl = weibo.getOriginalPic();
      ThumbUrl thumbUrl = new ThumbUrl(imgUrl);
      imgUrlList.add(thumbUrl);
    }
    final Intent intent = new Intent(mContext, WeiboImageActivity.class);
    intent.putExtra(WeiboImageActivity.EXTRA_IMAGE_LIST, imgUrlList);
    holder.mMediaView.setOnClickListener(v -> mContext.startActivity(intent));

    holder.mContentView.setOnClickListener(v -> startDetailPage(mContext, weibo));

    holder.mRepostBtn.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        WeiboRepostDialogFragment dialogFragment =
            WeiboRepostDialogFragment.newInstance(weibo.getIdstr());
        dialogFragment.show(((MainActivity) mContext).getSupportFragmentManager());
      }
    });
  }

  private void startDetailPage(Context context, Weibo weibo) {
    if (weibo == null) {
      return;
    }
    final String uid = AccessTokenKeeper.readAccessToken(context).getUid();
    final String weiboUserId = weibo.getUser().getIdstr();
    if (uid.equals(weiboUserId)) { // 如果是自己发的微博
      Intent intent = new Intent(context, WeiboDetailActivity.class);
      intent.putExtra(WeiboDetailActivity.WEIBO_ID_EXTRA, weibo.getId());
      context.startActivity(intent);
    } else { // 不是自己的微博目前没权限看详情
      Intent intent = new Intent(context, WebViewActivity.class);
      intent.putExtra(WebViewActivity.EXTRA_WEIBO_ID, weibo.getIdstr());
      intent.putExtra(WebViewActivity.EXTRA_UID, weiboUserId);
      context.startActivity(intent);
    }
  }

  private void setupUserInfo(ViewHolder holder, User user) {
    if (user == null) {
      return;
    }

    WeiboReader.readAvatar(mContext, holder.mAvatarView, user.getProfileImageUrl());
    WeiboReader.readNickname(holder.mNicknameView, user.getScreenName());
    WeiboReader.readUsername(holder.mUsernameView, user.getDomain());
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
    @BindView(R.id.weibo_list_item_repost_container) View mRepostContentView;
    @BindView(R.id.weibo_list_item_iv_media) View mMediaView;
    @BindView(R.id.weibo_list_item_tv_repost) TextView mRepostBtn;
    @BindView(R.id.weibo_list_item_tv_comment) TextView mCommentBtn;
    @BindView(R.id.weibo_list_item_tv_like) TextView mLikeBtn;

    ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(ViewHolder.this, itemView);
    }

    // 没办法迁移，api 获取的数据跟翔一样
    void setRepostContent(Context context, Weibo repostWeibo) {
      final TextView repostTextView =
          (TextView) mRepostContentView.findViewById(R.id.weibo_list_item_tv_repost_content);
      // 转发微博多媒体内容的容器
      final View mediaContainer =
          mRepostContentView.findViewById(R.id.weibo_list_item_tv_repost_media);
      final ImageView mediaImageView =
          (ImageView) mRepostContentView.findViewById(R.id.weibo_list_item_iv_repost_img);
      final TextView mediaImageType =
          (TextView) mRepostContentView.findViewById(R.id.weibo_list_item_tv_repost_type);

      if (repostWeibo == null) {
        clearWeiboContainer(mRepostContentView, repostTextView, mediaImageView, mediaImageType);
        mediaContainer.setVisibility(View.GONE);
      } else {
        String weiboContent = repostWeibo.getText();
        String name = repostWeibo.getUser().getScreenName();

        if (TextUtils.isEmpty(weiboContent)) {
          clearWeiboContainer(mRepostContentView, repostTextView, mediaImageView, mediaImageType);
          return;
        }

        weiboContent = "@" + name + ": " + weiboContent;
        WeiboParser.setupText(repostTextView, weiboContent);
        mRepostContentView.setBackgroundResource(R.drawable.bg_gray_border);

        String imgUrl = repostWeibo.getBmiddlePic();
        boolean isMultiImages = repostWeibo.getPicUrls().size() > 1;

        setRepostMediaContent(context, imgUrl, isMultiImages, mediaImageView, mediaImageType,
            mediaContainer);
      }
    }

    void clearWeiboContainer(View containerView, TextView weiboTextView, ImageView weiboMediaView,
        TextView weiboMediaTypeView) {
      containerView.setBackgroundResource(0);
      containerView.setPadding(0, 0, 0, 0);
      weiboTextView.setText("");
      // 我费尽千辛万苦终于发现你躺在这里 >>
      weiboTextView.setVisibility(View.GONE);
      Glide.clear(weiboMediaView);
      weiboMediaTypeView.setText("");
    }

    void setRepostMediaContent(final Context context, final String mediaUrl,
        final boolean isMultiImage, final ImageView mediaImageView, final TextView mediaImageType,
        final View mediaContainer) {

      if (TextUtils.isEmpty(mediaUrl)) {
        Glide.clear(mediaImageView);
        mediaImageType.setText("");
        mediaContainer.setVisibility(View.GONE);
      } else {
        mediaContainer.setVisibility(View.VISIBLE);

        final BitmapRequestBuilder<String, Bitmap> bitmapRequestBuilder =
            Glide.with(context).load(mediaUrl).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL);

        if (isMultiImage) {
          mediaImageType.setText(R.string.weibo_media_type_multiple_image);
          bitmapRequestBuilder.fitCenter().into(mediaImageView);
          return;
        }

        if (mediaUrl.endsWith("gif")) {
          mediaImageType.setText(R.string.weibo_media_type_gif);
          bitmapRequestBuilder.fitCenter().into(mediaImageView);
          return;
        }

        // Request has DiskCacheStrategy.ALL
        GenericRequestBuilder builder =
            PhotoUtils.getNetworkImageSizeRequest(context).load(Uri.parse(mediaUrl));
        builder.into(new SimpleTarget<BitmapFactory.Options>() {
          @Override public void onResourceReady(BitmapFactory.Options resource,
              GlideAnimation<? super BitmapFactory.Options> glideAnimation) {
            int imageHeight = resource.outHeight;
            if (imageHeight >= PhotoUtils.LONG_IMAGE_LENGTH) {
              mediaImageType.setText(R.string.weibo_media_type_long_image);
              bitmapRequestBuilder.centerCrop().into(mediaImageView);
            } else {
              mediaImageType.setText("");
              bitmapRequestBuilder.fitCenter().into(mediaImageView);
            }
          }
        });
      }
    }

    void setMediaContent(final Context context, final String mediaUrl, final boolean isMultiImage) {
      final ImageView mediaImageView = (ImageView) mMediaView.findViewById(R.id.weibo_media_iv_img);
      final TextView mediaImageType = (TextView) mMediaView.findViewById(R.id.weibo_media_tv_type);
      if (TextUtils.isEmpty(mediaUrl)) {
        Glide.clear(mediaImageView);
        mediaImageType.setText("");
        mMediaView.setVisibility(View.GONE);
      } else {
        mMediaView.setVisibility(View.VISIBLE);

        final BitmapRequestBuilder<String, Bitmap> bitmapRequestBuilder =
            Glide.with(context).load(mediaUrl).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL);

        // Request has DiskCacheStrategy.ALL
        GenericRequestBuilder builder =
            PhotoUtils.getNetworkImageSizeRequest(context).load(Uri.parse(mediaUrl));
        builder.into(new SimpleTarget<BitmapFactory.Options>() {
          @Override public void onResourceReady(BitmapFactory.Options resource,
              GlideAnimation<? super BitmapFactory.Options> glideAnimation) {
            int imageHeight = resource.outHeight;
            if (imageHeight >= PhotoUtils.LONG_IMAGE_LENGTH) {
              mediaImageType.setText(R.string.weibo_media_type_long_image);
              bitmapRequestBuilder.centerCrop().into(mediaImageView);
            } else {
              mediaImageType.setText("");
              bitmapRequestBuilder.fitCenter().into(mediaImageView);
            }
          }
        });

        if (mediaUrl.endsWith("gif")) {
          mediaImageType.setText(R.string.weibo_media_type_gif);
          bitmapRequestBuilder.fitCenter().into(mediaImageView);
        }

        if (isMultiImage) {
          mediaImageType.setText(R.string.weibo_media_type_multiple_image);
          bitmapRequestBuilder.fitCenter().into(mediaImageView);
        }
      }
    }
  }
}
