package com.zac4j.yoda.util.image;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import java.nio.charset.Charset;
import java.security.MessageDigest;

/**
 * Created by zac on 2018/6/24.
 * Description:
 */
public class EmojiTransformation extends BitmapTransformation {

    private static final String ID = EmojiTransformation.class.getCanonicalName();
    private static final byte[] ID_BYTES = ID.getBytes(Charset.forName("UTF-8"));

    @Override
    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth,
        int outHeight) {
        if (toTransform.getWidth() == outWidth && toTransform.getHeight() == outHeight) {
            return toTransform;
        }
        return Bitmap.createScaledBitmap(toTransform, outWidth, outHeight, true);
    }

    @Override
    public int hashCode() {
        return ID.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof EmojiTransformation;
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
    }
}
