package com.zac4j.yoda.ui.home;

import com.zac4j.yoda.data.DataManager;
import com.zac4j.yoda.ui.base.BasePresenter;
import io.reactivex.disposables.CompositeDisposable;
import javax.inject.Inject;

/**
 * Presenter for Notification
 * Created by zac on 3/22/2017.
 */

public class HomeNotificationPresenter extends BasePresenter<HomeNotificationView> {

  private final DataManager mDataManager;
  private CompositeDisposable mDisposable;

  @Inject public HomeNotificationPresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attach(HomeNotificationView mvpView) {
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
