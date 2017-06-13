package com.zac4j.yoda.ui.home.notif;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.zac4j.yoda.R;
import com.zac4j.yoda.data.model.Comment;
import com.zac4j.yoda.data.model.Friend;
import com.zac4j.yoda.data.model.User;
import com.zac4j.yoda.data.model.db.Profile;
import com.zac4j.yoda.ui.adapter.NotifCommentAdapter;
import com.zac4j.yoda.ui.adapter.NotifFollowerAdapter;
import com.zac4j.yoda.ui.base.BaseFragment;
import com.zac4j.yoda.ui.user.detail.UserDetailDialogFragment;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * UI for Notification
 * Created by zac on 3/22/2017.
 */

public class NotificationFragment extends BaseFragment implements NotificationView {

  @Inject NotificationPresenter mPresenter;
  @Inject NotifCommentAdapter mCommentAdapter;
  @Inject NotifFollowerAdapter mFollowerAdapter;

  @BindView(R.id.home_notif_gv_at_me_avatars) GridView mAvatarContainer;
  @BindView(R.id.home_notif_tv_at_me_count) TextView mAtMeCountView;
  @BindView(R.id.home_notif_rv_comment_list) RecyclerView mCommentContainer;
  @BindView(R.id.progress_bar) ProgressBar mProgressBar;

  private Unbinder unbinder;

  public static NotificationFragment newInstance() {
    return new NotificationFragment();
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_home_notif, container, false);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    getFragmentComponent().inject(this);
    unbinder = ButterKnife.bind(this, view);
    mPresenter.attach(this);

    mAvatarContainer.setAdapter(mFollowerAdapter);
    mAvatarContainer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        long userId = mFollowerAdapter.getItem(i).getId();
        mPresenter.showProfile(userId);
      }
    });

    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
    mCommentContainer.setLayoutManager(layoutManager);
    mCommentContainer.setAdapter(mCommentAdapter);
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
    mPresenter.detach();
  }

  @Override public void onResume() {
    super.onResume();

    Oauth2AccessToken accessToken = AccessTokenKeeper.readAccessToken(getContext());

    if (mFollowerAdapter.isEmpty()) {
      mPresenter.getLatestFollowers(accessToken.getToken(), accessToken.getUid());
    }

    if (mCommentAdapter.isEmpty()) {
      mPresenter.getLatestComments(accessToken.getToken());
    }
  }

  @Override public void showErrorView(String message) {
    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
  }

  @Override public void showProgress(boolean show) {
    if (mProgressBar == null) {
      return;
    }
    // 因为有两个 stream 可以 dispatch progressbar
    if (show && mProgressBar.isShown()) {
      return;
    }

    mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
  }

  @Override public void showEmptyView(boolean show) {
    if (show) {
      showEmptyComment();
      showEmptyFollower();
    }
  }

  @Override public void showComments(List<Comment> comments) {
    mCommentAdapter.addCommentList(comments);
  }

  @Override public void showEmptyFollower() {
    mAtMeCountView.setText(R.string.notif_no_latest_follows);
  }

  @Override public void showLatestFollowers(List<User> users) {
    String nickname = users.get(0).getScreenName();

    String atMeContent = getString(R.string.notif_follow_me, nickname, users.size() - 1);
    mAtMeCountView.setText(atMeContent);

    mFollowerAdapter.addAvatarList(users);
  }

  @Override public void showProfileDialog(Profile profile) {
    if (profile == null) {
      return;
    }

    UserDetailDialogFragment dialog = UserDetailDialogFragment.newInstance(profile);
    dialog.show(getFragmentManager());
  }

  @Override public void showEmptyComment() {
    mCommentContainer.setVisibility(View.VISIBLE);
  }
}
