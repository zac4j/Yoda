package com.zac4j.yoda.ui.home.hot;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.zac4j.yoda.R;
import com.zac4j.yoda.data.model.Tag;
import com.zac4j.yoda.ui.adapter.HotTagListAdapter;
import com.zac4j.yoda.ui.base.BaseFragment;
import java.util.List;
import javax.inject.Inject;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * UI for Hot Tags
 * Created by zaccc on 6/1/2017.
 */

public class HotTagFragment extends BaseFragment
    implements HotTagView, AdapterView.OnItemClickListener {

  @BindView(R.id.home_search_lv_hot_tags) ListView mTagListView;
  @BindView(R.id.empty_view) ViewStub mEmptyView;
  @BindView(R.id.progress_bar) ProgressBar mProgressBar;
  Unbinder unbinder;

  @Inject HotTagPresenter mPresenter;
  @Inject HotTagListAdapter mAdapter;

  public static HotTagFragment newInstance() {
    Bundle args = new Bundle();
    HotTagFragment fragment = new HotTagFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_home_search, container, false);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    getFragmentComponent().inject(this);
    unbinder = ButterKnife.bind(this, view);
    mPresenter.attach(this);

    mTagListView.setAdapter(mAdapter);
    mTagListView.setOnItemClickListener(this);
  }

  @Override public void onResume() {
    super.onResume();

    String token = AccessTokenKeeper.readAccessToken(getContext()).getToken();
    mPresenter.getHotTags(token);
  }

  @Override public void onDestroyView() {
    super.onDestroyView();

    unbinder.unbind();
    mPresenter.detach();
  }

  @Override public void showProgress(boolean show) {
    if (mProgressBar != null) {
      mProgressBar.setVisibility(show ? VISIBLE : GONE);
    }
  }

  @Override public void showMessage(String msg) {
    Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
  }

  @Override public void showHotTags(List<Tag> tagList) {
    mAdapter.addTagList(tagList);
  }

  @Override public void showEmptyView(boolean show) {
    if (show) {
      mEmptyView.inflate();
    }
  }

  @Override public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
    AlertDialog.Builder builder =
        new AlertDialog.Builder(getContext()).setMessage(R.string.request_permission)
            .setIcon(R.drawable.ic_weibo)
            .setTitle(R.string.main_nav_hot)
            .setPositiveButton(R.string.ok_i_know,
                (dialogInterface, i1) -> dialogInterface.dismiss());
    builder.create().show();
  }
}
