package com.zac4j.yoda.ui.home.timeline;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zac4j.yoda.data.DataManager;
import com.zac4j.yoda.data.model.Timeline;
import com.zac4j.yoda.data.model.response.Error;
import com.zac4j.yoda.data.remote.RequestState;
import com.zac4j.yoda.di.PerConfig;
import com.zac4j.yoda.ui.base.RxPresenter;
import com.zac4j.yoda.util.RxUtils;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import java.io.IOException;
import javax.inject.Inject;
import retrofit2.HttpException;
import retrofit2.Response;

/**
 * Timeline Presenter
 * Created by zac on 3/17/2017.
 */

@PerConfig public class TimelinePresenter extends RxPresenter<TimelineView> {

    private final DataManager mDataManager;
    private CompositeDisposable mDisposable;

    @Inject
    public TimelinePresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attach(TimelineView mvpView) {
        super.attach(mvpView);
        mDisposable = new CompositeDisposable();
    }

    @Override
    public void detach() {
        super.detach();
        mDisposable.clear();
    }

    void getTimeline(String token, int count, int page) {
        checkViewAttached();

        Observable<Response<Object>> responseObservable =
            mDataManager.getHomeTimeline(token, count, page)
                .compose(RxUtils.applyObservableSchedulers())
                .doOnSubscribe(disposable -> {
                    if (!getMvpView().isRefreshing()) {
                        publishRequestState(RequestState.LOADING);
                    }
                })
                .doOnError(throwable -> {
                    getMvpView().showRefresh(false);
                    publishRequestState(RequestState.ERROR);
                })
                .doOnComplete(() -> {
                    getMvpView().showRefresh(false);
                    publishRequestState(RequestState.COMPLETE);
                })
                .share();

        // 处理正常情况
        responseObservable.filter(Response::isSuccessful)
            .subscribeWith(new DisposableObserver<Response<Object>>() {
                @Override
                public void onNext(Response<Object> objectResponse) {
                    publishResponse(objectResponse);
                }

                @Override
                public void onError(Throwable throwable) {
                    publishErrors(throwable);
                }

                @Override
                public void onComplete() {

                }
            });

        // 处理 403 的情况
        responseObservable.filter(objectResponse -> objectResponse.code() == 403)
            .subscribeWith(new DisposableObserver<Response<Object>>() {
                @Override
                public void onNext(Response<Object> response) {
                    Error error = null;
                    Object data = response.body();
                    ObjectMapper mapper = mDataManager.getObjectMapper();
                    try {
                        String value = mapper.writeValueAsString(data);
                        error = mapper.readValue(value, Error.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                        getMvpView().showMessage(e.getMessage());
                    }

                    if (error == null) {
                        return;
                    }

                    switch (error.getErrorCode()) {
                        // 请求次数已达上限
                        case 10023:
                            getMvpView().showMessage(Error.LIMITED_REQUEST_RATE);
                            break;
                    }
                }

                @Override
                public void onError(Throwable throwable) {
                    publishErrors(throwable);
                }

                @Override
                public void onComplete() {

                }
            });
    }

    @Override
    protected void publishResponse(Response<Object> response) {
        Timeline timeline = null;
        Object data = response.body();
        ObjectMapper mapper = mDataManager.getObjectMapper();
        try {
            String value = mapper.writeValueAsString(data);
            timeline = mapper.readValue(value, Timeline.class);
        } catch (IOException e) {
            e.printStackTrace();
            getMvpView().showMessage(e.getMessage());
        }

        if (timeline == null || timeline.getStatuses() == null || timeline.getStatuses()
            .isEmpty()) {
            getMvpView().showEmptyView(true);
        } else {
            getMvpView().showTimeline(timeline.getStatuses());
        }
    }

    @Override
    protected void publishErrors(Throwable throwable) {
        if (throwable instanceof HttpException) {

        }
    }
}
