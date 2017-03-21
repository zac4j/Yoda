package com.zac4j.yoda.ui.base;

/**
 *
 * Created by zac on 16-7-21.
 */

public interface MvpView {
  void showError(String message);

  void onTokenInvalid();
}
