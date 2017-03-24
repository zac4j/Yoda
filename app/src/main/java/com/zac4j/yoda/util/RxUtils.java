package com.zac4j.yoda.util;

import android.text.TextUtils;
import com.zac4j.yoda.ui.base.MvpView;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import timber.log.Timber;

/**
 * ReactiveX Utilities
 * Created by zac on 3/20/2017.
 */

public class RxUtils {

  public static <T> SingleTransformer<T, T> applySchedulers() {
    return new SingleTransformer<T, T>() {
      @Override public SingleSource<T> apply(Single<T> upstream) {
        return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
      }
    };
  }

  public static SingleTransformer<Response<Object>, Response<Object>> handleResponse(
      final MvpView mvpView) {
    return new SingleTransformer<Response<Object>, Response<Object>>() {
      @Override public SingleSource<Response<Object>> apply(Single<Response<Object>> upstream) {
        return upstream.map(new Function<Response<Object>, Response<Object>>() {
          @Override public Response<Object> apply(@NonNull Response<Object> response)
              throws Exception {
            if (!response.isSuccessful()) {
              String errorBody = response.errorBody().string();
              System.out.println("error body: " + errorBody);
              if (!TextUtils.isEmpty(errorBody) && errorBody.contains("21327")) {
                mvpView.onTokenInvalid();
              } else {
                Timber.e(errorBody);
                mvpView.showError("Network bad, try request later!");
              }
            }
            return response;
          }
        });
      }
    };
  }
}
