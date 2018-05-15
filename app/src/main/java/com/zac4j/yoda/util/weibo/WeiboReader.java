package com.zac4j.yoda.util.weibo;

import android.content.Context;
import android.os.Build;
import android.support.annotation.MainThread;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.zac4j.yoda.R;
import com.zac4j.yoda.data.model.Size;
import com.zac4j.yoda.data.model.Weibo;
import com.zac4j.yoda.ui.widget.HeartView;
import com.zac4j.yoda.util.NumberUtils;
import com.zac4j.yoda.util.TimeUtils;
import com.zac4j.yoda.util.image.GlideApp;
import com.zac4j.yoda.util.image.GlideCircleTransformation;
import com.zac4j.yoda.util.image.ImageSize;
import com.zac4j.yoda.util.image.WeiboImageManager;
import com.zac4j.yoda.util.loader.WeiboImageLoader;

/**
 * Weibo content read helper
 * Created by zaccc on 6/8/2017.
 */
public class WeiboReader {

    private static WeiboReader sReader;
    private LayoutInflater mLayoutInflater;

    private WeiboReader() {

    }

    @MainThread
    public static WeiboReader getInstance() {
        if (sReader == null) {
            sReader = new WeiboReader();
        }
        return sReader;
    }

    public void readAvatar(ImageView avatarView, String avatarUrl) {
        if (TextUtils.isEmpty(avatarUrl)) {
            GlideApp.with(avatarView).clear(avatarView);
        } else {
            GlideApp.with(avatarView)
                .load(avatarUrl)
                .transform(new GlideCircleTransformation())
                .into(avatarView);
        }
    }

    public void readNickname(TextView nicknameView, String nickname) {
        nicknameView.setText(TextUtils.isEmpty(nickname) ? "" : nickname);
    }

    public void readUsername(TextView usernameView, String username) {
        usernameView.setText(TextUtils.isEmpty(username) ? "" : "@" + username);
    }

    public void readDescription(TextView descriptionView, String description) {
        descriptionView.setText(TextUtils.isEmpty(description) ? "这个人虽勤快，依然懒得写简介。" : description);
    }

    public void readLocation(TextView locationView, String location) {
        locationView.setText(TextUtils.isEmpty(location) ? "Galactic Republic" : location);
    }

    //todo read image, video, multiple image
    public void parseWeiboMedia(ViewGroup mediaContainer, Weibo weibo) {
        boolean hasMultipleImage = weibo.getPicUrls() != null && weibo.getPicUrls().size() > 1;
        setupImageLayout(mediaContainer, weibo.getBmiddlePic(), hasMultipleImage);
    }

    public void readRepostContent(ViewGroup repostContainer, Weibo repostWeibo) {
        if (repostContainer.getChildCount() > 0) {
            repostContainer.removeAllViews();
        }

        if (repostWeibo == null) {
            return;
        }

        Context context = repostContainer.getContext();
        View repostContentView =
            getLayoutInflater(context).inflate(R.layout.layout_repost_content, repostContainer,
                false);
        TextView textContainer = repostContentView.findViewById(R.id.weibo_repost_text_container);
        ViewGroup mediaContainer =
            repostContentView.findViewById(R.id.weibo_repost_media_container);

        String weiboContent = repostWeibo.getText();

        String name = repostWeibo.getUser().getScreenName();
        weiboContent = "@" + name + ": " + weiboContent;
        WeiboParser.setupText(textContainer, weiboContent);

        readPictureContent(mediaContainer, repostWeibo);
        repostContainer.addView(repostContentView);
    }

    public void readPostTime(TextView postTimeView, String postTime) {
        String pattern = "E MMM dd HH:mm:ss Z yyyy";
        String timeAgo = TimeUtils.getTimeAgo(postTime, pattern);
        postTimeView.setText(TextUtils.isEmpty(timeAgo) ? "" : timeAgo);
    }

    public void readPostSource(TextView sourceView, String source) {
        if (TextUtils.isEmpty(source)) {
            sourceView.setText("");
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                sourceView.setText(Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY));
            } else {
                sourceView.setText(Html.fromHtml(source));
            }
        }
    }

    public void readTextContent(TextView contentView, String content) {
        if (TextUtils.isEmpty(content)) {
            contentView.setText("");
        } else {
            WeiboParser.setupText(contentView, content);
        }
    }

    public void readRepostNumber(TextView repostBtn, long repostNumber) {
        repostBtn.setText(NumberUtils.getRelativeNumberSpanString(repostNumber));
    }

    public void readCommentsNumber(TextView commentBtn, long commentsNumber) {
        commentBtn.setText(NumberUtils.getRelativeNumberSpanString(commentsNumber));
    }

    public void readLikeNumber(TextView likeBtn, long likeNumber) {

    }

    public void readLikeState(HeartView heartView, long likeNumber, boolean likeState) {
        heartView.setLikeCount(NumberUtils.getRelativeNumberSpanString(likeNumber));
        heartView.setLike(likeState);
    }

    private void readPictureContent(ViewGroup mediaContainer, Weibo weibo) {
        boolean hasMultipleImage = weibo.getPicUrls() != null && weibo.getPicUrls().size() > 1;
        setupImageLayout(mediaContainer, weibo.getBmiddlePic(), hasMultipleImage);
    }

    private LayoutInflater getLayoutInflater(Context context) {
        if (mLayoutInflater == null) {
            mLayoutInflater = LayoutInflater.from(context);
        }
        return mLayoutInflater;
    }

    private void setupImageLayout(ViewGroup mediaContainer, String mediaUrl,
        boolean hasMultipleImage) {
        if (mediaContainer.getChildCount() > 0) {
            mediaContainer.removeAllViews();
        }

        if (TextUtils.isEmpty(mediaUrl)) {
            return;
        }

        View singleImageContainer = getLayoutInflater(mediaContainer.getContext()).inflate(
            R.layout.layout_weibo_single_image, mediaContainer, false);
        final ImageView imageHolder = singleImageContainer.findViewById(R.id.weibo_media_iv_img);
        final TextView imageDescView = singleImageContainer.findViewById(R.id.weibo_media_tv_desc);

        updateImageContainer(imageHolder, imageDescView, mediaUrl, hasMultipleImage);

        mediaContainer.addView(singleImageContainer);
    }

    private void updateImageContainer(ImageView imageView, TextView descriptionView,
        String imageUrl, boolean hasMultipleImage) {
        WeiboImageManager.decodeNetworkImageSize(imageView.getContext(), imageUrl)
            .into(new SimpleTarget<Size>() {
                @Override
                public void onResourceReady(Size imageSize, Transition<? super Size> transition) {
                    int width = imageSize.getWidth();
                    int height = imageSize.getHeight();

                    if (height >= ImageSize.LONG_IMAGE) {
                        descriptionView.setText(R.string.weibo_media_type_long_image);
                    } else if (height >= ImageSize.LARGE_IMAGE) {
                        descriptionView.setText(R.string.weibo_media_type_large_image);
                    } else if (hasMultipleImage) {
                        descriptionView.setText(R.string.weibo_media_type_large_image);
                    } else if (imageUrl.endsWith(".gif")) {
                        descriptionView.setText(R.string.weibo_media_type_gif);
                    } else {
                        descriptionView.setText(R.string.weibo_media_type_single_image);
                    }
                    RequestOptions options = RequestOptions.centerCropTransform();
                    WeiboImageLoader.loadSingleImage(imageView, options, imageUrl);
                }
            });
    }
}
