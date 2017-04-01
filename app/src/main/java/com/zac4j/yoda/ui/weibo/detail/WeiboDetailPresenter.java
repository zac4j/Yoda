package com.zac4j.yoda.ui.weibo.detail;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zac4j.yoda.data.DataManager;
import com.zac4j.yoda.data.model.Weibo;
import com.zac4j.yoda.di.PerConfig;
import com.zac4j.yoda.ui.base.BasePresenter;
import com.zac4j.yoda.util.RxUtils;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import java.io.IOException;
import javax.inject.Inject;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Presenter for Weibo Detail
 * Created by zac on 3/30/2017.
 */

@PerConfig public class WeiboDetailPresenter extends BasePresenter<WeiboDetailView> {

  private final DataManager mDataManager;
  private CompositeDisposable mDisposable;

  @Inject public WeiboDetailPresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attach(WeiboDetailView mvpView) {
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

  public void getWeibo(String token, long id) {
    checkViewAttached();
    if (isProcessing()) {
      return;
    }
    showProgress(true);
    mDataManager.getWeiboById(token, id)
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
              getMvpView().showWeiboInfo(weibo);
            }
          }

          @Override public void onError(Throwable e) {
            showProgress(false);
            Timber.e(e);
          }
        });
  }
}
