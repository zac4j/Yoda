package com.zac4j.yoda.ui.home.message;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.zac4j.yoda.R;
import com.zac4j.yoda.ui.base.BaseFragment;
import com.zac4j.yoda.ui.home.user.UserFragment;

/**
 * Messenger Page
 * Created by zac on 4/28/17.
 */

public class MessengerFragment extends BaseFragment {

  public static MessengerFragment newInstance() {
    Bundle args = new Bundle();
    MessengerFragment fragment = new MessengerFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_home_message, container, false);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }

  @Override public void onResume() {
    super.onResume();
  }

  @Override public void onPause() {
    super.onPause();
  }
}
