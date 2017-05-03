package com.zac4j.yoda.ui.home.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.zac4j.yoda.R;
import com.zac4j.yoda.ui.base.BaseFragment;

/**
 * UI for Search
 * Created by zac on 17-5-3.
 */

public class SearchFragment extends BaseFragment {

  @BindView(R.id.home_search_lv_trend_list) ListView mTrendListView;
  Unbinder unbinder;

  public static SearchFragment newInstance() {
    return new SearchFragment();
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_home_search, container, false);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    unbinder = ButterKnife.bind(this, view);
  }

  @Override public void onResume() {
    super.onResume();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }
}
