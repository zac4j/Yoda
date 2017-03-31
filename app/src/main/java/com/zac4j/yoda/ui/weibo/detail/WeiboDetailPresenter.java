package com.zac4j.yoda.ui.weibo.detail;

import com.zac4j.yoda.data.DataManager;
import com.zac4j.yoda.di.PerConfig;
import com.zac4j.yoda.ui.base.BasePresenter;
import io.reactivex.disposables.CompositeDisposable;
import javax.inject.Inject;

/**
 * Presenter for Weibo Detail
 * Created by zac on 3/30/2017.
 */

@PerConfig public class WeiboDetailPresenter extends BasePresenter<WeiboDetailView> {

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
}
