package com.zac4j.yoda.ui.base;

import com.jakewharton.rxrelay2.BehaviorRelay;
import com.zac4j.yoda.data.remote.RequestState;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import retrofit2.Response;

/**
 * Reactive Presenter
 * Created by zaccc on 6/1/2017.
 */

public class RxPresenter<T extends MvpView> extends BasePresenter<T> {

  private BehaviorRelay<RequestState> mRequestState = BehaviorRelay.createDefault(RequestState.IDLE);
  protected BehaviorRelay<Response<Object>> mResponse = BehaviorRelay.create();
  protected BehaviorRelay<Throwable> mErrors = BehaviorRelay.create();

  protected void publishRequestState(RequestState requestState) {
    Observable.just(requestState)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(mRequestState);

    mRequestState.subscribe(new Consumer<RequestState>() {
      @Override public void accept(@NonNull RequestState requestState) throws Exception {
        switch (requestState) {
          case IDLE:
          case LOADING:
            getMvpView().showProgress(true);
            break;
          case COMPLETE:
            getMvpView().showProgress(false);
            break;
          case ERROR:
            getMvpView().showProgress(false);
            break;
        }
      }
    });
  }

  protected void publishResponse(Response<Object> response) {
    Observable.just(response).observeOn(AndroidSchedulers.mainThread()).subscribe(mResponse);
  }

  protected void publishErrors(Throwable throwable) {
    Observable.just(throwable).observeOn(AndroidSchedulers.mainThread()).subscribe(mErrors);
  }
}
