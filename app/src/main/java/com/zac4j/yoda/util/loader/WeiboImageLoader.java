package com.zac4j.yoda.util.loader;

import android.widget.ImageView;
import com.bumptech.glide.request.RequestOptions;
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
}
