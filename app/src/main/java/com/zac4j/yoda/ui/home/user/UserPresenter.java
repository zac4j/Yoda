package com.zac4j.yoda.ui.home.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zac4j.yoda.data.DataManager;
import com.zac4j.yoda.data.model.User;
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
 * Presenter for User
 * Created by zac on 17-5-10.
 */

@PerConfig public class UserPresenter extends BasePresenter<UserView> {

  private final DataManager mDataManager;
  private CompositeDisposable mDisposable;

  @Inject public UserPresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attach(UserView mvpView) {
    super.attach(mvpView);
    mDisposable = new CompositeDisposable();
  }

  @Override public void detach() {
    super.detach();
    mDisposable.clear();
  }

  void getUserProfile(String token, String uid) {
    checkViewAttached();

    if (isProcessing()) {
      return;
    }
    showProgress(true);
    Disposable disposable = mDataManager.getUserProfile(token, uid)
        .compose(RxUtils.<Response<Object>>applySchedulers())
        .compose(RxUtils.handleResponse(getMvpView()))
        .subscribeWith(new DisposableSingleObserver<Response<Object>>() {
          @Override public void onSuccess(Response<Object> response) {
            showProgress(false);
            if (response.isSuccessful()) {
              Object data = response.body();
              ObjectMapper mapper = mDataManager.getObjectMapper();
              User user = null;
              try {
                String value = mapper.writeValueAsString(data);
                user = mapper.readValue(value, User.class);
              } catch (IOException e) {
                e.printStackTrace();
              }

              if (user == null) {
                getMvpView().showMainView(false);
                return;
              }

              getMvpView().showUserProfile(user);
              getMvpView().showMainView(true);
            }
          }

          @Override public void onError(Throwable throwable) {
            getMvpView().showMainView(false);
          }
        });

    mDisposable.add(disposable);
  }
}
