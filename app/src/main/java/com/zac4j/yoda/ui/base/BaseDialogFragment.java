package com.zac4j.yoda.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.util.LongSparseArray;
import com.zac4j.yoda.App;
import com.zac4j.yoda.di.component.DaggerPerConfigComponent;
import com.zac4j.yoda.di.component.DialogFragmentComponent;
import com.zac4j.yoda.di.component.PerConfigComponent;
import com.zac4j.yoda.di.module.DialogFragmentModule;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Base dialog fragment
 * Created by Zheng on 6/16/2017.
 */

public class BaseDialogFragment extends DialogFragment {

  private static final String KEY_FRAGMENT_ID = "fragment_id";
  private static final AtomicLong NEXT_ID = new AtomicLong(0);
  private static final LongSparseArray<PerConfigComponent> sComponentsArray =
      new LongSparseArray<>();

  private DialogFragmentComponent mDialogFragmentComponent;
  private long mFragmentId;

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mFragmentId = savedInstanceState != null ? savedInstanceState.getLong(KEY_FRAGMENT_ID)
        : NEXT_ID.getAndIncrement();
    PerConfigComponent perConfigComponent;
    if (sComponentsArray.indexOfKey(mFragmentId) < 0) {
      perConfigComponent = DaggerPerConfigComponent.builder()
          .applicationComponent(App.get(getContext()).getApplicationComponent())
          .build();
      sComponentsArray.put(mFragmentId, perConfigComponent);
    } else {
      perConfigComponent = sComponentsArray.get(mFragmentId);
    }
    mDialogFragmentComponent =
        perConfigComponent.dialogFragmentComponent(new DialogFragmentModule(this));
  }

  @Override public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putLong(KEY_FRAGMENT_ID, mFragmentId);
  }

  @Override public void onDestroy() {
    if (!getActivity().isChangingConfigurations()) {
      sComponentsArray.remove(mFragmentId);
    }
    super.onDestroy();
  }

  protected DialogFragmentComponent getDialogFragmentComponent() {
    return mDialogFragmentComponent;
  }
}
