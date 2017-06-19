package com.zac4j.yoda.ui.weibo.repost;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zac4j.yoda.data.DataManager;
import com.zac4j.yoda.data.model.Weibo;
import com.zac4j.yoda.data.remote.RequestState;
import com.zac4j.yoda.ui.base.RxPresenter;
import com.zac4j.yoda.util.RxUtils;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import java.io.IOException;
import javax.inject.Inject;
import retrofit2.Response;

/**
 * Presenter for weibo repost
 * Created by Zheng on 6/16/2017.
 */

public class WeiboRepostPresenter extends RxPresenter<WeiboRepostView> {
  private final DataManager mDataManager;
  private CompositeDisposable mDisposables;

  @Inject public WeiboRepostPresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attach(WeiboRepostView mvpView) {
    super.attach(mvpView);
    mDisposables = new CompositeDisposable();
  }

  @Override public void detach() {
    super.detach();
    mDisposables.clear();
  }

  public void repostWeibo(String token, String weiboId, String content, int ifAsComment) {
    mDisposables.add(mDataManager.repostWeibo(token, weiboId, content, ifAsComment)
        .compose(RxUtils.applySchedulers())
        .doOnSubscribe(disposable -> publishRequestState(RequestState.LOADING))
        .doOnError(throwable -> publishRequestState(RequestState.ERROR))
        .doOnSuccess(objectResponse -> publishRequestState(RequestState.COMPLETE))
        .subscribeWith(new DisposableSingleObserver<Response<Object>>() {
          @Override public void onSuccess(Response<Object> objectResponse) {
            publishResponse(objectResponse);
          }

          @Override public void onError(Throwable throwable) {
            publishErrors(throwable);
          }
        }));
  }

  @Override protected void publishResponse(Response<Object> response) {
    super.publishResponse(response);
    mDisposables.add(mResponse.subscribe(response1 -> {
      if (response1.isSuccessful()) {
        Object data = response1.body();
        ObjectMapper mapper = mDataManager.getObjectMapper();

        String value = mapper.writeValueAsString(data);
        Weibo weibo = mapper.readValue(value, Weibo.class);

        getMvpView().onRepostSuccess(weibo);
        getMvpView().showMessage("转发成功！");
        getMvpView().hide();
      } else {
        getMvpView().onRepostFailure();
        getMvpView().showMessage("转发失败！请检查网络连接");
        getMvpView().hide();
      }
    }));
  }

  @Override protected void publishErrors(Throwable throwable) {
    super.publishErrors(throwable);
    mDisposables.add(mErrors.subscribe(throwable1 -> {
      getMvpView().showMessage("转发失败！请检查网络连接");
      getMvpView().hide();
    }));
  }
}