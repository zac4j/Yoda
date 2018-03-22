package com.zac4j.yoda.util.image;

import android.content.Context;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.zac4j.yoda.data.model.Size;

/**
 * Helper class provide lots of image features.
 * Created by Zac on 2018/3/22.
 */

public class WeiboImageManager {

    public static RequestOptions sRequestOptions =
        RequestOptions.skipMemoryCacheOf(true).diskCacheStrategy(DiskCacheStrategy.DATA);

    /**
     * Decode network image size via Glide.
     * https://github.com/bumptech/glide/issues/781
     * @param context context
     * @param imageUrl network image url
     * @return {@link Size} of network image.
     */
    public static GlideRequest<Size> decodeNetworkImageSize(Context context, String imageUrl) {
        return GlideApp.with(context).as(Size.class).apply(sRequestOptions).load(imageUrl);
    }
}
