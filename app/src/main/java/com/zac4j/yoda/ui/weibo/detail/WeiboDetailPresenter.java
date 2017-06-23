package com.zac4j.yoda.ui.weibo.detail;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zac4j.yoda.data.DataManager;
import com.zac4j.yoda.data.model.Comment;
import com.zac4j.yoda.data.model.Notification;
import com.zac4j.yoda.data.model.Weibo;
import com.zac4j.yoda.data.remote.RequestState;
import com.zac4j.yoda.di.PerConfig;
import com.zac4j.yoda.ui.base.BasePresenter;
import com.zac4j.yoda.ui.base.RxPresenter;
import com.zac4j.yoda.util.RxUtils;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Presenter for Weibo Detail
 * Created by zac on 3/30/2017.
 */

@PerConfig public class WeiboDetailPresenter extends RxPresenter<WeiboDetailView> {

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

  public void getWeiboComments(String token, long id, int page, int count) {
    checkViewAttached();
    mDataManager.getWeiboComments(token, id, page, count)
        .compose(RxUtils.applySchedulers())
        .doOnSubscribe(disposable -> publishRequestState(RequestState.LOADING))
        .doOnError(throwable -> publishRequestState(RequestState.ERROR))
        .doOnSuccess(objectResponse -> publishRequestState(RequestState.COMPLETE))
        .subscribeWith(new DisposableSingleObserver<Response<Object>>() {
          @Override public void onSuccess(Response<Object> response) {
            publishResponse(response);
          }

          @Override public void onError(Throwable e) {
            Timber.e(e);
          }
        });
  }

  @Override protected void publishResponse(Response<Object> response) {
    if (response.isSuccessful()) {
      Notification notification = null;
      Object data = response.body();
      ObjectMapper mapper = mDataManager.getObjectMapper();
      try {
        String value = mapper.writeValueAsString(data);
        notification = mapper.readValue(value, Notification.class);
      } catch (IOException e) {
        e.printStackTrace();
      }

      if (notification == null) {
        getMvpView().showEmptyCommentView(true);
      } else {
        List<Comment> commentList = notification.getComments();
        if (commentList == null || commentList.isEmpty()) {
          getMvpView().showEmptyCommentView(true);
        } else {
          getMvpView().showWeiboComments(commentList);
        }
      }
    }
  }

  @Override protected void publishErrors(Throwable throwable) {

  }
}
