package com.zac4j.yoda.ui.base;

/**
 * Base MvpView
 * Created by zac on 16-7-21.
 */

public interface MvpView {

  void showMainView(boolean show);

  void showProgress(boolean show);

  void showError(String message);

  boolean isProcessing();

  void onTokenInvalid();
}
