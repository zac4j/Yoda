package com.zac4j.yoda.ui.user.friend;

import com.zac4j.yoda.data.DataManager;
import com.zac4j.yoda.di.PerConfig;
import com.zac4j.yoda.ui.base.BasePresenter;
import com.zac4j.yoda.util.RxUtils;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import javax.inject.Inject;
import retrofit2.Response;

/**
 * Presenter for User Friends
 * Created by zac on 4/13/2017.
 */

@PerConfig public class UserFriendListPresenter extends BasePresenter<UserFriendListView> {

  private CompositeDisposable mDisposable;
  private DataManager mDataManager;

  @Inject public UserFriendListPresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attach(UserFriendListView mvpView) {
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

  public void getUserFriends(String token, String uid, int count, int cursor) {
    checkViewAttached();
    if (isProcessing() || getMvpView().isRefreshing()) {
      return;
    }
    mDataManager.getUserFriends(token, Long.parseLong(uid), count, cursor)
        .compose(RxUtils.<Response<Object>>applySchedulers())
        .compose(RxUtils.handleResponse(getMvpView()))
        .subscribeWith(new DisposableSingleObserver<Response<Object>>() {
          @Override public void onSuccess(Response<Object> response) {

          }

          @Override public void onError(Throwable e) {

          }
        });
  }
}
