package com.zac4j.yoda.ui.home.notif;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.zac4j.yoda.R;
import com.zac4j.yoda.data.model.Comment;
import com.zac4j.yoda.data.model.User;
import com.zac4j.yoda.ui.adapter.NotificationAdapter;
import com.zac4j.yoda.ui.base.BaseFragment;
import com.zac4j.yoda.util.WeiboReader;
import javax.inject.Inject;

/**
 * UI for Notification
 * Created by zac on 3/22/2017.
 */

public class NotificationFragment extends BaseFragment implements NotificationView {

  @Inject NotificationPresenter mPresenter;
  @Inject NotificationAdapter mAdapter;

  @BindView(R.id.home_notif_gv_at_me_avatars) GridView mAvatarContainer;
  @BindView(R.id.home_notif_tv_at_me_count) TextView mAtMeCountView;
  @BindView(R.id.home_notif_comment_container) View mCommentContainer;
  @BindView(R.id.comment_list_item_iv_avatar) ImageView mCommentAvatarView;
  @BindView(R.id.comment_list_item_tv_nickname) TextView mCommentNicknameView;
  @BindView(R.id.comment_list_item_tv_username) TextView mCommentUsernameView;
  @BindView(R.id.comment_list_item_tv_post_time) TextView mCommentPostTimeView;
  @BindView(R.id.comment_list_item_tv_post_source) TextView mCommentPostSourceView;
  @BindView(R.id.comment_list_item_tv_comment_content) TextView mCommentContentView;
  @BindView(R.id.comment_list_item_tv_weibo_content) TextView mWeiboContentView;
  @BindView(R.id.weibo_list_item_tv_repost) TextView mRepostBtn;
  @BindView(R.id.weibo_list_item_tv_comment) TextView mCommentBtn;
  @BindView(R.id.weibo_list_item_tv_like) TextView mLikeBtn;
  @BindView(R.id.progress_bar) ProgressBar mProgressBar;
  Unbinder unbinder1;

  private Unbinder unbinder;

  public static NotificationFragment newInstance() {
    return new NotificationFragment();
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_home_notif, container, false);
    unbinder1 = ButterKnife.bind(this, view);
    return view;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    getFragmentComponent().inject(this);
    unbinder = ButterKnife.bind(this, view);
    mPresenter.attach(this);
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
    mPresenter.detach();
  }

  @Override public void onResume() {
    super.onResume();

    String token = AccessTokenKeeper.readAccessToken(getContext()).getToken();
    mPresenter.getLatestComment(token);
  }

  @Override public void showErrorView(String message) {
    Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
  }

  @Override public void showProgress(boolean show) {
    if (mProgressBar == null) {
      return;
    }

    if (show && mProgressBar.isShown()) {
      return;
    }

    mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
  }

  @Override public void showEmptyView(boolean show) {

  }

  @Override public void showComment(Comment comment) {
    showCommentUser(comment.getUser());

    WeiboReader.readPostTime(mCommentPostTimeView, comment.getCreatedAt());
    WeiboReader.readPostSource(mCommentPostSourceView, comment.getSource());
    WeiboReader.readContent(mCommentContentView, comment.getText());
    if (comment.getWeibo() != null) {
      WeiboReader.readContent(mWeiboContentView, comment.getWeibo().getText());
    }
    WeiboReader.readLikeNumber(mLikeBtn, comment.getLikeCount());
  }

  @Override public void showEmptyComment(boolean show) {
    mCommentContainer.setVisibility(show ? View.GONE : View.VISIBLE);
  }

  private void showCommentUser(User user) {
    if (user == null) {
      return;
    }
    WeiboReader.readAvatar(getContext(), mCommentAvatarView, user.getProfileImageUrl());
    WeiboReader.readNickname(mCommentNicknameView, user.getScreenName());
    WeiboReader.readUsername(mCommentUsernameView, user.getDomain());
  }
}
