package com.zac4j.yoda.util.image;

import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import java.util.Locale;
import timber.log.Timber;

/**
 * Image Loading Logging Listener
 * Created by zac on 4/12/2017.
 */

public class ImageLoggingListener<T, R> implements RequestListener<T, R> {

  @Override
  public boolean onException(Exception e, T model, Target<R> target, boolean isFirstResource) {
    Timber.e("GLIDE", String.format(Locale.ROOT, "onException(%s, %s, %s, %s)", e, model, target,
        isFirstResource), e);
    return false;
  }

  @Override
  public boolean onResourceReady(R resource, T model, Target<R> target, boolean isFromMemoryCache,
      boolean isFirstResource) {
    Timber.d("GLIDE",
        String.format(Locale.ROOT, "onResourceReady(%s, %s, %s, %s, %s)", resource, model, target,
            isFromMemoryCache, isFirstResource));
    return false;
  }
}