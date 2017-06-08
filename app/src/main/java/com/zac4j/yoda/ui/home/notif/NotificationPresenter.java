package com.zac4j.yoda.ui.home.notif;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zac4j.yoda.data.DataManager;
import com.zac4j.yoda.data.model.Comment;
import com.zac4j.yoda.data.model.Notification;
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
 * Presenter for Notification
 * Created by zac on 3/22/2017.
 */

@PerConfig public class NotificationPresenter extends RxPresenter<NotificationView> {

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
    mDisposable.clear();
  }

  public void getFollowers(String token, long uid) {
    checkViewAttached();

    mDisposable.add(mDataManager.getFollowers(token, uid)
        .doOnSubscribe(disposable -> publishRequestState(RequestState.LOADING))
        .doOnError(throwable -> publishRequestState(RequestState.ERROR))
        .doOnSuccess(objectResponse -> publishRequestState(RequestState.COMPLETE))
        .subscribeWith(new DisposableSingleObserver<Response<Object>>() {
          @Override public void onSuccess(Response<Object> objectResponse) {

          }

          @Override public void onError(Throwable throwable) {
            publishErrors(throwable);
          }
        }));
  }

  public void getLatestComment(String token) {
    checkViewAttached();

    mDisposable.add(mDataManager.getLatestComment(token)
        .compose(RxUtils.applySchedulers())
        .doOnSubscribe(disposable -> publishRequestState(RequestState.LOADING))
        .doOnError(throwable -> publishRequestState(RequestState.ERROR))
        .doOnSuccess(objectResponse -> publishRequestState(RequestState.COMPLETE))
        .subscribeWith(new DisposableSingleObserver<Response<Object>>() {
          @Override public void onSuccess(Response<Object> objectResponse) {
            if (objectResponse.isSuccessful()) {
              Notification notification = null;
              Object data = objectResponse.body();
              ObjectMapper mapper = mDataManager.getObjectMapper();
              try {
                String value = mapper.writeValueAsString(data);
                notification = mapper.readValue(value, Notification.class);
              } catch (IOException e) {
                e.printStackTrace();
              }

              if (notification == null
                  || notification.getComments() == null
                  || notification.getComments().isEmpty()) {
                getMvpView().showEmptyComment(true);
              } else {
                Comment latestComment = notification.getComments().get(0);
                getMvpView().showComment(latestComment);
              }
            }
          }

          @Override public void onError(Throwable throwable) {
            publishErrors(throwable);
          }
        }));
  }

  @Override protected void publishErrors(Throwable throwable) {
    super.publishErrors(throwable);

    mErrors.distinct().subscribe(throwable1 -> {

    });
  }
}
