package com.zac4j.yoda.ui.base;

/**
 * Base MvpView
 * Created by zac on 16-7-21.
 */

public interface MvpView {

  void showProgress(boolean show);

  void showEmptyView(boolean show);

  void showMessage(String msg);
}
