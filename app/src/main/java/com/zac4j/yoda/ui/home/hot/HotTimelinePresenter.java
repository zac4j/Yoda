package com.zac4j.yoda.ui.home.hot;

import com.fasterxml.jackson.databind.ObjectMapper;
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
 * Presenter for Hot Timeline.
 * Created by zac on 5/2/17.
 */

public class HotTimelinePresenter extends BasePresenter<HotTimelineView> {

  private final DataManager mDataManager;
  private CompositeDisposable mDisposable;

  @Inject public HotTimelinePresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attach(HotTimelineView mvpView) {
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

  void getHotTimeline(String token, int count, int page) {
    checkViewAttached();

    if (isProcessing()) {
      return;
    }

    if (!getMvpView().isRefreshing()) {
      getMvpView().showProgress(true);
    }

    mDataManager.getHomeTimeline(token, count, page, true)
        .compose(RxUtils.<Response<Object>>applySchedulers())
        .compose(RxUtils.handleResponse(getMvpView()))
        .subscribeWith(new DisposableSingleObserver<Response<Object>>() {
          @Override public void onSuccess(Response<Object> response) {
            hideProgress();
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
