package com.zac4j.yoda.ui.home.timeline;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zac4j.yoda.data.DataManager;
import com.zac4j.yoda.data.model.Timeline;
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
 * Timeline Presenter
 * Created by zac on 3/17/2017.
 */

@PerConfig public class TimelinePresenter extends RxPresenter<TimelineView> {

  private final DataManager mDataManager;
  private CompositeDisposable mDisposable;

  @Inject public TimelinePresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attach(TimelineView mvpView) {
    super.attach(mvpView);
    mDisposable = new CompositeDisposable();
  }

  @Override public void detach() {
    super.detach();
    mDisposable.clear();
  }

  void getTimeline(String token, int count, int page) {
    checkViewAttached();

    mDisposable.add(mDataManager.getHomeTimeline(token, count, page)
        .compose(RxUtils.applySchedulers())
        .doOnSubscribe(disposable -> publishRequestState(RequestState.LOADING))
        .doOnError(throwable -> publishRequestState(RequestState.ERROR))
        .doOnSuccess(objectResponse -> publishRequestState(RequestState.COMPLETE))
        .subscribeWith(new DisposableSingleObserver<Response<Object>>() {
          @Override public void onSuccess(Response<Object> response) {
            publishResponse(response);
          }

          @Override public void onError(Throwable e) {
            publishErrors(e);
          }
        }));
  }

  @Override protected void publishResponse(Response<Object> response) {
    super.publishResponse(response);

    mDisposable.add(mResponse.subscribe(response1 -> {
      if (response1.isSuccessful()) {
        Timeline timeline = null;
        Object data = response1.body();
        ObjectMapper mapper = mDataManager.getObjectMapper();
        try {
          String value = mapper.writeValueAsString(data);
          timeline = mapper.readValue(value, Timeline.class);
        } catch (IOException e) {
          e.printStackTrace();
          getMvpView().showErrorView(e.getMessage());
        }

        if (timeline == null || timeline.getStatuses() == null || timeline.getStatuses()
            .isEmpty()) {
          getMvpView().showEmptyView(true);
        } else {
          getMvpView().showTimeline(timeline.getStatuses());
        }
      }
    }));
  }

  @Override protected void publishErrors(Throwable throwable) {
    super.publishErrors(throwable);
  }
}
