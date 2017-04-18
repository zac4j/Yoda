package com.zac4j.yoda.ui.user.friend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zac4j.yoda.data.DataManager;
import com.zac4j.yoda.data.model.Friend;
import com.zac4j.yoda.data.model.response.Error;
import com.zac4j.yoda.di.PerConfig;
import com.zac4j.yoda.ui.base.BasePresenter;
import com.zac4j.yoda.util.RxUtils;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import java.io.IOException;
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
    if (isProcessing()) {
      return;
    }
    if (!getMvpView().isRefreshing()) {
      getMvpView().showProgress(true);
    }
    Disposable disposable = mDataManager.getUserFriends(token, Long.parseLong(uid), count, cursor)
        .compose(RxUtils.<Response<Object>>applySchedulers())
        .compose(RxUtils.handleResponse(getMvpView()))
        .subscribeWith(new DisposableSingleObserver<Response<Object>>() {
          @Override public void onSuccess(Response<Object> response) {
            hideProgress();
            if (response.isSuccessful()) {
              Friend friend = null;
              Object data = response.body();
              ObjectMapper mapper = mDataManager.getObjectMapper();
              try {
                String value = mapper.writeValueAsString(data);
                friend = mapper.readValue(value, Friend.class);
              } catch (IOException e) {
                e.printStackTrace();
              }

              if (friend == null) {
                showMainView(false);
              } else {
                showMainView(true);
                getMvpView().showFriendList(friend);
              }
            }
          }

          @Override public void onError(Throwable e) {
            hideProgress();
            getMvpView().showError(Error.NETWORK);
          }
        });

    mDisposable.add(disposable);
  }

  private void hideProgress() {
    getMvpView().showProgress(false);
    getMvpView().showRefresh(false);
  }
}
