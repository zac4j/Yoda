package com.zac4j.yoda.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.LongSparseArray;
import com.zac4j.yoda.App;
import com.zac4j.yoda.di.component.DaggerPerConfigComponent;
import com.zac4j.yoda.di.component.FragmentComponent;
import com.zac4j.yoda.di.component.PerConfigComponent;
import com.zac4j.yoda.di.module.FragmentModule;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Base Fragment
 * Created by zac on 3/17/2017.
 */

public class BaseFragment extends Fragment {

    private static final String KEY_FRAGMENT_ID = "fragment_id";
    private static final AtomicLong NEXT_ID = new AtomicLong(0);
    private static final LongSparseArray<PerConfigComponent> sComponentsArray =
        new LongSparseArray<>();

    private FragmentComponent mFragmentComponent;
    private long mFragmentId;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
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
        mFragmentComponent = perConfigComponent.fragmentComponent(new FragmentModule(this));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(KEY_FRAGMENT_ID, mFragmentId);
    }

    @Override
    public void onDestroy() {
        if (!getActivity().isChangingConfigurations()) {
            sComponentsArray.remove(mFragmentId);
        }
        super.onDestroy();
    }

    protected FragmentComponent getFragmentComponent() {
        return mFragmentComponent;
    }
}
