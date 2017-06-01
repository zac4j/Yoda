package com.zac4j.yoda.ui.home.hot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zac4j.yoda.data.DataManager;
import com.zac4j.yoda.data.model.HotTag;
import com.zac4j.yoda.data.model.Tag;
import com.zac4j.yoda.data.remote.RequestState;
import com.zac4j.yoda.di.PerConfig;
import com.zac4j.yoda.ui.base.RxPresenter;
import com.zac4j.yoda.util.RxUtils;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableSingleObserver;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import retrofit2.HttpException;
import retrofit2.Response;

/**
 * Presenter for Hot Tag
 * Created by zaccc on 6/1/2017.
 */

@PerConfig public class HotTagPresenter extends RxPresenter<HotTagView> {

  private final DataManager mDataManager;
  private CompositeDisposable mDisposable;

  @Inject public HotTagPresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attach(HotTagView mvpView) {
    super.attach(mvpView);
    mDisposable = new CompositeDisposable();
  }

  @Override public void detach() {
    super.detach();
    if (mDisposable != null) {
      mDisposable.clear();
    }
  }

  public void getHotTags(String token) {
    checkViewAttached();

    mDataManager.getHotTags(token)
        .compose(RxUtils.<Response<Object>>applySchedulers())
        .doOnSubscribe(new Consumer<Disposable>() {
          @Override public void accept(@NonNull Disposable disposable) throws Exception {
            publishRequestState(RequestState.LOADING);
          }
        })
        .doOnError(new Consumer<Throwable>() {
          @Override public void accept(@NonNull Throwable throwable) throws Exception {
            publishRequestState(RequestState.ERROR);
          }
        })
        .doOnSuccess(new Consumer<Response<Object>>() {
          @Override public void accept(@NonNull Response<Object> objectResponse) throws Exception {
            publishRequestState(RequestState.COMPLETE);
          }
        })
        .subscribeWith(new DisposableSingleObserver<Response<Object>>() {
          @Override public void onSuccess(@NonNull Response<Object> objectResponse) {
            publishResponse(objectResponse);
          }

          @Override public void onError(@NonNull Throwable throwable) {
            publishErrors(throwable);
          }
        });
  }

  @Override protected void publishResponse(Response<Object> response) {
    super.publishResponse(response);
    if (response.isSuccessful()) {
      HotTag hotTag = null;
      Object data = response.body();
      ObjectMapper mapper = mDataManager.getObjectMapper();
      try {
        String value = mapper.writeValueAsString(data);
        hotTag = mapper.readValue(value, HotTag.class);
      } catch (IOException e) {
        e.printStackTrace();
      }

      if (hotTag != null) {
        List<Tag> tagList = hotTag.getTagList();
        if (tagList == null || hotTag.getTagList().isEmpty()) {
          getMvpView().showEmptyView(true);
        } else {
          getMvpView().showHotTags(tagList);
        }
      } else {
        getMvpView().showEmptyView(true);
      }
    }
  }

  @Override protected void publishErrors(Throwable error) {
    super.publishErrors(error);
    if (error instanceof HttpException) {
      Response response = ((HttpException) error).response();
      int responseCode = response.code();
      System.out.println("request error: " + responseCode);
    }
  }
}
