package com.zac4j.yoda.ui.weibo.send;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zac4j.yoda.data.DataManager;
import com.zac4j.yoda.data.model.Weibo;
import com.zac4j.yoda.di.PerConfig;
import com.zac4j.yoda.ui.base.BasePresenter;
import com.zac4j.yoda.util.RxUtils;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import java.io.IOException;
import java.util.Map;
import javax.inject.Inject;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Send Weibo Presenter
 * Created by zac on 3/27/2017.
 */

@PerConfig public class WeiboSendPresenter extends BasePresenter<WeiboSendView> {

  private final DataManager mDataManager;
  private CompositeDisposable mDisposable;

  @Inject public WeiboSendPresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attach(WeiboSendView mvpView) {
    super.attach(mvpView);
    if (mDisposable == null) {
      mDisposable = new CompositeDisposable();
    }
  }

  @Override public void detach() {
    super.detach();
    if (mDisposable != null) {
      mDisposable.clear();
    }
  }

  public void sendTextWeibo(Map<String, String> weiboMap) {
    checkViewAttached();
    if (isProcessing()) {
      return;
    }
    showProgress(true);
    mDisposable.add(mDataManager.sendTextWeibo(weiboMap)
        .compose(RxUtils.<Response<Object>>applySchedulers())
        .compose(RxUtils.handleResponse(getMvpView()))
        .subscribeWith(new DisposableSingleObserver<Response<Object>>() {
          @Override public void onSuccess(Response<Object> response) {
            showProgress(false);
            if (response.isSuccessful()) {
              Weibo weibo = null;
              Object data = response.body();
              ObjectMapper mapper = mDataManager.getObjectMapper();
              try {
                String value = mapper.writeValueAsString(data);
                weibo = mapper.readValue(value, Weibo.class);
              } catch (IOException e) {
                e.printStackTrace();
              }

              if (weibo == null) {
                return;
              }

              getMvpView().showMessage("Successfully sent !");
              getMvpView().finishPublish();
            }
          }

          @Override public void onError(Throwable e) {
            showProgress(false);
            Timber.e(e);
          }
        }));
  }

  public void sendPictureWeibo(Map<String, RequestBody> weiboMap, MultipartBody.Part image) {
    checkViewAttached();
    if (isProcessing()) {
      return;
    }
    showProgress(true);
    mDisposable.add(mDataManager.sendPictureWeibo(weiboMap, image)
        .compose(RxUtils.<Response<Object>>applySchedulers())
        .compose(RxUtils.handleResponse(getMvpView()))
        .subscribeWith(new DisposableSingleObserver<Response<Object>>() {
          @Override public void onSuccess(Response<Object> response) {
            showProgress(false);
            if (response.isSuccessful()) {
              Weibo weibo = null;
              Object data = response.body();
              ObjectMapper mapper = mDataManager.getObjectMapper();
              try {
                String value = mapper.writeValueAsString(data);
                weibo = mapper.readValue(value, Weibo.class);
              } catch (IOException e) {
                e.printStackTrace();
              }

              if (weibo == null) {
                return;
              }

              getMvpView().showMessage("Successfully sent !");
              getMvpView().finishPublish();
            }
          }

          @Override public void onError(Throwable e) {
            showProgress(false);
            Timber.e(e);
          }
        }));
  }
}
