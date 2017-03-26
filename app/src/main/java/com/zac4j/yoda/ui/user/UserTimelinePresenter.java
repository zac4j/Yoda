package com.zac4j.yoda.ui.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zac4j.yoda.data.DataManager;
import com.zac4j.yoda.data.model.Timeline;
import com.zac4j.yoda.data.model.Weibo;
import com.zac4j.yoda.ui.base.BasePresenter;
import com.zac4j.yoda.ui.home.HomeTimelineView;
import com.zac4j.yoda.util.RxUtils;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
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

public class UserTimelinePresenter extends BasePresenter<UserTimelineView> {

  private final DataManager mDataManager;
  private CompositeDisposable mDisposable;

  @Inject public UserTimelinePresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attach(UserTimelineView mvpView) {
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
    Disposable disposable = mDataManager.getUserTimeline(token, count, page)
        .compose(RxUtils.<Response<Object>>applySchedulers())
        .compose(RxUtils.handleResponse(getMvpView()))
        .subscribeWith(new DisposableSingleObserver<Response<Object>>() {
          @Override public void onSuccess(Response<Object> response) {
            hideProgress();
            getMvpView().showEmpty(false);
            if (response.isSuccessful()) {
              Timeline timeline = null;
              Object data = response.body();
              ObjectMapper mapper = mDataManager.getObjectMapper();
              try {
                String value = mapper.writeValueAsString(data);
                timeline = mapper.readValue(value, Timeline.class);
              } catch (IOException e) {
                e.printStackTrace();
              }

              if (timeline == null) {
                return;
              }

              List<Weibo> weiboList = timeline.getStatuses();
              if (weiboList.isEmpty()) {
                getMvpView().showEmpty(true);
              } else {
                getMvpView().showTimeline(weiboList);
              }
            }
          }

          @Override public void onError(Throwable e) {
            hideProgress();
            Timber.e(e);
          }
        });
    mDisposable.add(disposable);
  }

  private void hideProgress() {
    getMvpView().showRefresh(false);
    getMvpView().showProgress(false);
  }
}