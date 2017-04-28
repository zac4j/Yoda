package com.zac4j.yoda.ui.home.notification;

import com.zac4j.yoda.data.DataManager;
import com.zac4j.yoda.di.PerConfig;
import com.zac4j.yoda.ui.base.BasePresenter;
import io.reactivex.disposables.CompositeDisposable;
import javax.inject.Inject;

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

  public void getNotificationList() {

  }

}
