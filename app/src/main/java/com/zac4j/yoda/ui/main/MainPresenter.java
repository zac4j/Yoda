package com.zac4j.yoda.ui.main;

import com.zac4j.yoda.data.DataManager;
import com.zac4j.yoda.di.PerConfig;
import com.zac4j.yoda.ui.base.BasePresenter;
import javax.inject.Inject;

/**
 * Main presenter
 * Created by zac on 16-8-27.
 */
@PerConfig
public class MainPresenter extends BasePresenter<MainView> {

  private DataManager mDataManager;

  @Inject
  public MainPresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void attach(MainView mvpView) {
    super.attach(mvpView);
  }

  @Override public void detach() {
    super.detach();
  }
}
