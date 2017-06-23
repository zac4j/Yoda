package com.zac4j.yoda.ui.home.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zac4j.yoda.data.DataManager;
import com.zac4j.yoda.data.model.User;
import com.zac4j.yoda.data.model.response.Error;
import com.zac4j.yoda.data.remote.RequestState;
import com.zac4j.yoda.di.PerConfig;
import com.zac4j.yoda.ui.base.RxPresenter;
import com.zac4j.yoda.util.RxUtils;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import java.io.IOException;
import javax.inject.Inject;
import retrofit2.Response;

/**
 * Presenter for User
 * Created by zac on 17-5-10.
 */

@PerConfig public class UserPresenter extends RxPresenter<UserView> {

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
    mDisposable.add(mDataManager.getUserProfile(token, uid)
        .compose(RxUtils.applySchedulers())
        .doOnSubscribe(disposable -> publishRequestState(RequestState.LOADING))
        .doOnError(throwable -> publishRequestState(RequestState.ERROR))
        .doOnSuccess(objectResponse -> publishRequestState(RequestState.COMPLETE))
        .subscribeWith(new DisposableSingleObserver<Response<Object>>() {
          @Override public void onSuccess(Response<Object> response) {
            publishResponse(response);
          }

          @Override public void onError(Throwable throwable) {
            publishErrors(throwable);
          }
        }));
  }

  @Override protected void publishResponse(Response<Object> response) {
    if (response.isSuccessful()) {
      User user = null;
      Object data = response.body();
      ObjectMapper mapper = mDataManager.getObjectMapper();
      try {
        String value = mapper.writeValueAsString(data);
        user = mapper.readValue(value, User.class);
      } catch (IOException e) {
        e.printStackTrace();
        getMvpView().showMessage(e.getMessage());
      }

      if (user == null) {
        getMvpView().showEmptyView(true);
      } else {
        getMvpView().showProfile(user);
      }
    } else {
      getMvpView().showMessage(Error.NETWORK);
    }
  }

  @Override protected void publishErrors(Throwable throwable) {

  }
}
