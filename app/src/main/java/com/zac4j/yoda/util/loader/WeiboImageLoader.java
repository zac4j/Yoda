package com.zac4j.yoda.util.loader;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.zac4j.yoda.data.model.Size;
import com.zac4j.yoda.util.image.GlideApp;

/**
 * Weibo image reader
 * Created by Zac on 2018/1/1.
 */

public class WeiboImageLoader {

    /**
     * Helper method for load and display single image
     *
     * @param holder image holder
     * @param url image link url
     */
    public static void loadSingleImage(ImageView holder, RequestOptions options, String url) {
        GlideApp.with(holder).load(url).apply(options).into(holder);
    }

    /**
     * Helper method for load and display single image
     *
     * @param holder image holder
     * @param url image link url
     */
    public static void loadSingleImage(ImageView holder, Size size, String url) {
        GlideApp.with(holder)
            .load(url)
            .override(size.getWidth(), size.getHeight())
            .fitCenter()
            .into(holder);
    }

    public static void loadEmoji(TextView textView, String emojiUrl,
        SpannableString spannableString, int startIndex, int endIndex) {
        GlideApp.with(textView.getContext())
            .load(emojiUrl)
            .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.RESOURCE))
            .into(new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(@NonNull Drawable resource,
                    @Nullable Transition<? super Drawable> transition) {
                    resource.setBounds(0, 0, 48, 48);
                    ImageSpan imageSpan = new ImageSpan(resource);
                    spannableString.setSpan(imageSpan, startIndex, endIndex,
                        Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
                    textView.setText(spannableString, TextView.BufferType.SPANNABLE);
                }
            });
    }
}
