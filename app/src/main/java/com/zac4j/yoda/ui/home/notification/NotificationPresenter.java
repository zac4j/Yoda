package com.zac4j.yoda.ui.home.notification;

import com.zac4j.yoda.data.DataManager;
import com.zac4j.yoda.di.PerConfig;
import com.zac4j.yoda.ui.base.BasePresenter;
import com.zac4j.yoda.util.RxUtils;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import javax.inject.Inject;
import retrofit2.Response;

/**
 * Presenter for Notification
 * Created by zac on 3/22/2017.
 */

@PerConfig public class NotificationPresenter extends BasePresenter<NotificationView> {

  private final DataManager mDataManager;
  private CompositeDisposable mDisposable;

  @Inject public NotificationPresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attach(NotificationView mvpView) {
    super.attach(mvpView);
    mDisposable = new CompositeDisposable();
  }

  @Override public void detach() {
    super.detach();
    if (mDisposable != null) {
      mDisposable.clear();
    }
  }

  public void getNotificationList(String token, int count, int page) {
    checkViewAttached();

    if (isProcessing()) {
      return;
    }

    if (!getMvpView().isRefreshing()) {
      getMvpView().showProgress(true);
    }

    mDataManager.getUserNotification(token, count, page)
        .compose(RxUtils.<Response<Object>>applySchedulers())
        .compose(RxUtils.handleResponse(getMvpView()))
        .subscribeWith(new DisposableSingleObserver<Response<Object>>() {
          @Override public void onSuccess(Response<Object> response) {
            hideProgress();



          }

          @Override public void onError(Throwable throwable) {
            hideProgress();
          }
        });
  }

  private void hideProgress() {
    getMvpView().showRefresh(false);
    getMvpView().showProgress(false);
  }
}
