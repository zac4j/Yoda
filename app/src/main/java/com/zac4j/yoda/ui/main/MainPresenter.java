package com.zac4j.yoda.ui.main;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zac4j.yoda.data.DataManager;
import com.zac4j.yoda.data.model.User;
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
 * Main presenter
 * Created by zac on 16-8-27.
 */
@PerConfig public class MainPresenter extends BasePresenter<MainView> {

  private final DataManager mDataManager;
  private CompositeDisposable mDisposable;

  @Inject public MainPresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attach(MainView mvpView) {
    super.attach(mvpView);
    mDisposable = new CompositeDisposable();
  }

  @Override public void detach() {
    super.detach();
    if (mDisposable != null) {
      mDisposable.clear();
    }
  }

  public void getUserProfile(String token, final String uid) {
    checkViewAttached();
    if (isProcessing()) {
      return;
    }
    showProgress(true);
    mDisposable.add(mDataManager.getUserProfile(token, uid)
        .compose(RxUtils.<Response<Object>>applySchedulers())
        .compose(RxUtils.handleResponse(getMvpView()))
        .subscribeWith(new DisposableSingleObserver<Response<Object>>() {
          @Override public void onSuccess(Response<Object> response) {
            showProgress(false);
            if (response.isSuccessful()) {
              User user = null;
              Object data = response.body();
              ObjectMapper mapper = mDataManager.getObjectMapper();
              try {
                String value = mapper.writeValueAsString(data);
                user = mapper.readValue(value, User.class);
              } catch (IOException e) {
                e.printStackTrace();
              }

              if (user == null) {
                return;
              }

              setUserProfile(user);
            }
          }

          @Override public void onError(Throwable e) {
            Timber.e(e);
            showProgress(false);
          }
        }));
  }

  private void setUserProfile(User user) {
    getMvpView().showAvatar(user.getProfileImageUrl());
    getMvpView().showUserDescription(user.getDescription());
  }
}
