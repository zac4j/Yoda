package com.zac4j.yoda.ui.user.moment;

import com.zac4j.yoda.data.DataManager;
import com.zac4j.yoda.di.PerConfig;
import com.zac4j.yoda.ui.base.BasePresenter;
import io.reactivex.disposables.CompositeDisposable;
import javax.inject.Inject;

/**
 * Timeline Presenter
 * Created by zac on 3/17/2017.
 */

@PerConfig public class UserMomentPresenter extends BasePresenter<UserMomentView> {

    private final DataManager mDataManager;
    private CompositeDisposable mDisposable;

    @Inject
    public UserMomentPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attach(UserMomentView mvpView) {
        super.attach(mvpView);
        mDisposable = new CompositeDisposable();
    }

    @Override
    public void detach() {
        super.detach();
        if (mDisposable != null) {
            mDisposable.clear();
        }
    }

    public void getTimeline(String token, int count, int page) {
        checkViewAttached();
    }

    private void hideProgress() {
        getMvpView().showRefresh(false);
    }
}
