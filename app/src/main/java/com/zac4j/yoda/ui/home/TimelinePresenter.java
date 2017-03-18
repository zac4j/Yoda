package com.zac4j.yoda.ui.home;

import com.zac4j.yoda.data.DataManager;
import com.zac4j.yoda.data.model.Timeline;
import com.zac4j.yoda.data.model.Weibo;
import com.zac4j.yoda.ui.base.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;
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
    mDataManager.getTimeline(token, count, page)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new DisposableSingleObserver<Timeline>() {
          @Override public void onSuccess(Timeline timeline) {
            if (timeline != null) {
              List<Weibo> weiboList = timeline.getWeiboList();
              getMvpView().showTimeline(weiboList);
            }
          }

          @Override public void onError(Throwable e) {
            getMvpView().showError(e.getMessage());
            Timber.e(e);
          }
        });
  }
}
