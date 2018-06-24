package com.zac4j.yoda.util.image;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.SimpleResource;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;
import com.bumptech.glide.module.AppGlideModule;
import com.zac4j.yoda.data.model.Size;
import java.io.File;
import java.io.IOException;

/**
 * A AppGlideModule implementation
 * Created by Zaccc on 2017/12/6.
 */

@GlideModule public class WeiboGlideModule extends AppGlideModule {

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide,
        @NonNull Registry registry) {
        registry.prepend(File.class, BitmapFactory.Options.class, new BitmapSizeDecoder());
        registry.register(BitmapFactory.Options.class, Size.class,
            new OptionsSizeResourceTranscoder());
    }

    class BitmapSizeDecoder implements ResourceDecoder<File, BitmapFactory.Options> {

        @Override
        public boolean handles(@NonNull File source, @NonNull Options options) {
            return true;
        }

        @Nullable
        @Override
        public Resource<BitmapFactory.Options> decode(@NonNull File source, int width, int height,
            @NonNull Options options) {
            BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
            bitmapOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(source.getAbsolutePath(), bitmapOptions);
            return new SimpleResource<>(bitmapOptions);
        }
    }

    class OptionsSizeResourceTranscoder implements ResourceTranscoder<BitmapFactory.Options, Size> {

        @Override
        public Resource<Size> transcode(@NonNull Resource<BitmapFactory.Options> resource,
            @NonNull Options options) {
            BitmapFactory.Options bitmapOptions = resource.get();
            Size size = new Size(bitmapOptions.outWidth, bitmapOptions.outHeight);
            return new SimpleResource<>(size);
        }
    }
}
