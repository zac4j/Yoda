package com.zac4j.yoda.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.zac4j.yoda.R;
import com.zac4j.yoda.ui.adapter.HomeTimelineAdapter;
import com.zac4j.yoda.data.model.Weibo;
import com.zac4j.yoda.ui.base.BaseFragment;
import java.util.List;
import javax.inject.Inject;

/**
 * Weibo list page
 * Created by zac on 3/17/2017.
 */

public class TimelineFragment extends BaseFragment implements TimelineView {

  @Inject TimelinePresenter mPresenter;

  @BindView(R.id.home_swipe_weibo_list_container) SwipeRefreshLayout mSwipeWeiboListContainer;
  @BindView(R.id.home_rv_weibo_list) RecyclerView mWeiboListView;
  @BindView(R.id.progress_bar) ProgressBar mProgressBar;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    getFragmentComponent().inject(this);

    View view = inflater.inflate(R.layout.fragment_home_weibo_list, container, false);
    ButterKnife.bind(this, view);
    mPresenter.attach(this);



    return view;
  }

  @Override public void showError(String message) {
    Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
  }

  @Override public void showProgress(boolean show) {
    if (show) {
      mProgressBar.setVisibility(View.VISIBLE);
    } else {
      mProgressBar.setVisibility(View.GONE);
    }
  }

  @Override public void showTimeline(List<Weibo> weiboList) {
    HomeTimelineAdapter adapter = new HomeTimelineAdapter(getContext(), weiboList);
    mWeiboListView.setAdapter(adapter);
  }
}
