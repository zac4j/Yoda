package com.zac4j.yoda.ui.base;

import com.jakewharton.rxrelay2.BehaviorRelay;
import com.zac4j.yoda.data.remote.RequestState;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import retrofit2.Response;

/**
 * Reactive Presenter
 * Created by zaccc on 6/1/2017.
 */

public abstract class RxPresenter<T extends MvpView> extends BasePresenter<T> {

    private BehaviorRelay<RequestState> mRequestState =
        BehaviorRelay.createDefault(RequestState.IDLE);

    protected void publishRequestState(RequestState requestState) {
        Observable.just(requestState)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(mRequestState);

        mRequestState.subscribe(requestState1 -> {
            switch (requestState1) {
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
        });
    }

    protected abstract void publishResponse(Response<Object> response);

    protected abstract void publishErrors(Throwable throwable);
}
