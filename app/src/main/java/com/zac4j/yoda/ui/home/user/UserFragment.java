package com.zac4j.yoda.ui.home.user;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.zac4j.yoda.R;
import com.zac4j.yoda.data.model.User;
import com.zac4j.yoda.ui.base.BaseFragment;
import javax.inject.Inject;

/**
 * User Page
 * Created by zac on 4/28/17.
 */

public class UserFragment extends BaseFragment implements UserView {

  public static final String USER_DOMAIN = "http://weibo.com/";

  @Inject UserPresenter mPresenter;

  @BindView(R.id.root_layout) View mMainView;
  @BindView(R.id.user_detail_iv_cover) ImageView mCoverView;
  @BindView(R.id.user_detail_iv_avatar) ImageView mAvatarView;
  @BindView(R.id.user_detail_tv_nickname) TextView mNicknameView;
  @BindView(R.id.user_detail_tv_description) TextView mDescriptionView;
  @BindView(R.id.user_detail_tv_location) TextView mLocationView;
  @BindView(R.id.user_detail_tv_link) TextView mLinkView;
  @BindView(R.id.user_detail_tv_follower_count) TextView mFollowerCountView;
  @BindView(R.id.user_detail_tv_following_count) TextView mFollowingCountView;
  @BindView(R.id.user_detail_swc_night_mode) Switch mNightModeSwitch;
  @BindView(R.id.user_detail_swc_data_saver) Switch mDataSaverSwitch;
  @BindView(R.id.progress_bar) ProgressBar mProgressBar;

  private Unbinder unbinder;

  public static UserFragment newInstance() {
    Bundle args = new Bundle();
    UserFragment fragment = new UserFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_home_user, container, false);
  }

  @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    getFragmentComponent().inject(this);
    mPresenter.attach(this);

    unbinder = ButterKnife.bind(this, view);
  }

  @Override public void onResume() {
    super.onResume();

    Oauth2AccessToken oa = AccessTokenKeeper.readAccessToken(getContext());
    mPresenter.getUserProfile(oa.getToken(), oa.getUid());
  }

  @Override public void onPause() {
    super.onPause();
    showProgress(false);
  }

  @Override public void showProgress(boolean show) {
    if (mProgressBar == null) {
      return;
    }
    mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
  }

  @Override public void showEmptyView(boolean show) {

  }

  @Override public void showMessage(String msg) {
    Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }

  @OnClick({
      R.id.user_detail_tv_settings, R.id.user_detail_tv_support, R.id.user_detail_tv_quit
  }) public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.user_detail_tv_profile:
        break;
      case R.id.user_detail_tv_lists:
        break;
      case R.id.user_detail_tv_recommends:
        break;
      case R.id.user_detail_tv_settings:
        break;
      case R.id.user_detail_tv_support:
        break;
      case R.id.user_detail_tv_quit:
        break;
    }
  }

  @Override public void showProfile(User user) {

    String coverUrl = user.getCoverImage();
    showCover(coverUrl);

    String avatarUrl = user.getAvatarLarge();
    showAvatar(avatarUrl);

    String nickname = user.getName();
    String gender = user.getGender();
    showNicknameAndGender(nickname, gender);

    String description = user.getDescription();
    showDescription(description);

    String location = user.getLocation();
    showLocation(location);

    String profileUrl = user.getProfileUrl();
    showUserLink(profileUrl);

    int followerCount = user.getFollowersCount();
    mFollowerCountView.setText(String.valueOf(followerCount));

    int followingCount = user.getFriendsCount();
    mFollowingCountView.setText(String.valueOf(followingCount));
  }

  private void showCover(String coverUrl) {
    if (TextUtils.isEmpty(coverUrl)) {
      // TODO set a default cover background
    }
    Glide.with(UserFragment.this)
        .load(coverUrl)
        .apply(new RequestOptions().centerCrop())
        .into(mCoverView);
  }

  private void showUserLink(String profileUrl) {
    if (TextUtils.isEmpty(profileUrl)) {
      mLinkView.setText(USER_DOMAIN);
    } else {
      String link = USER_DOMAIN + profileUrl;
      mLinkView.setText(link);
    }
  }

  private void showLocation(String location) {
    if (TextUtils.isEmpty(location)) {
      mLocationView.setText(R.string.user_has_no_location);
    } else {
      mLocationView.setText(location);
    }
  }

  private void showDescription(String description) {
    if (TextUtils.isEmpty(description)) {
      mDescriptionView.setText(R.string.user_has_no_description);
    } else {
      mDescriptionView.setText(description);
    }
  }

  private void showAvatar(String avatar) {
    Glide.with(UserFragment.this)
        .load(avatar)
        .apply(new RequestOptions().fitCenter())
        .into(mAvatarView);
  }

  private void showNicknameAndGender(String nickname, String gender) {
    if (TextUtils.isEmpty(nickname)) {
      mNicknameView.setText(R.string.user_has_no_name);
    } else {
      mNicknameView.setText(nickname);
    }

    if (TextUtils.equals(gender, "f")) {
      mNicknameView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_user_female, 0);
    } else {
      mNicknameView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_user_male, 0);
    }
  }
}
