package com.zac4j.yoda.ui.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.zac4j.yoda.R;
import com.zac4j.yoda.ui.base.BaseActivity;

/**
 * UI for User
 * Created by Zac on 2017/3/24.
 */

public class UserActivity extends BaseActivity {

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user);
  }
}
