package com.zac4j.yoda.ui.weibo;

import com.zac4j.yoda.data.DataManager;
import com.zac4j.yoda.data.model.post.TextWeibo;
import com.zac4j.yoda.ui.base.BasePresenter;
import com.zac4j.yoda.util.RxUtils;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import javax.inject.Inject;
import retrofit2.Response;

/**
 * Send Weibo Presenter
 * Created by zac on 3/27/2017.
 */

public class SendWeiboPresenter extends BasePresenter<SendWeiboView> {

  @Inject DataManager mDataManager;
  private CompositeDisposable mDisposable;

  public SendWeiboPresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attach(SendWeiboView mvpView) {
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

  public void sendWeibo(TextWeibo weibo) {
    checkViewAttached();
    mDisposable.add(mDataManager.sendWeibo(weibo)
        .compose(RxUtils.<Response<Object>>applySchedulers())
        .compose(RxUtils.handleResponse(getMvpView()))
        .subscribeWith(new DisposableSingleObserver<Response<Object>>() {
          @Override public void onSuccess(Response<Object> response) {

          }

          @Override public void onError(Throwable e) {

          }
        }));
  }
}
