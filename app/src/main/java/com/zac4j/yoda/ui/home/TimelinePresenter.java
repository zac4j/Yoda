package com.zac4j.yoda.ui.home;

import com.zac4j.yoda.data.DataManager;
import com.zac4j.yoda.data.model.Timeline;
import com.zac4j.yoda.data.model.Weibo;
import com.zac4j.yoda.ui.base.BasePresenter;
import com.zac4j.yoda.util.RxUtils;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import retrofit2.Response;
import timber.log.Timber;

/**
 * Timeline Presenter
 * Created by zac on 3/17/2017.
 */

public class TimelinePresenter extends BasePresenter<TimelineView> {

  private static final String TAG = "TimelinePresenter";
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
    if (mDisposable != null) {
      mDisposable.clear();
    }
  }

  public void getTimeline(String token, int count, int page) {
    checkViewAttached();
    if (!getMvpView().isRefreshing()) {
      getMvpView().showProgress(true);
    }
    mDataManager.getTimeline(token, count, page)
        .compose(RxUtils.<Response<Object>>applySchedulers())
        .compose(RxUtils.handleResponse(getMvpView()))
        .subscribe(new DisposableSingleObserver<Response<Object>>() {
          @Override public void onSuccess(Response<Object> response) {
            hideProgress();
            if (response.isSuccessful()) {
              String data = response.body().toString();
              Timeline timeline = null;
              try {
                timeline = mDataManager.getObjectMapper().readValue(data, Timeline.class);
              } catch (IOException e) {
                e.printStackTrace();
              }

              if (timeline == null) {
                return;
              }

              List<Weibo> weiboList = timeline.getStatuses();
              getMvpView().showTimeline(weiboList);
            }
          }

          @Override public void onError(Throwable e) {
            hideProgress();
            Timber.e(e);
          }
        });
  }

  private void hideProgress() {
    getMvpView().showRefresh(false);
    getMvpView().showProgress(false);
  }
}
