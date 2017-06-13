package com.zac4j.yoda.ui.user.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.zac4j.yoda.R;
import com.zac4j.yoda.data.model.db.Profile;
import com.zac4j.yoda.util.WeiboReader;

/**
 * User Detail Dialog
 * Created by zaccc on 6/8/2017.
 */

public class UserDetailDialogFragment extends DialogFragment {

  public static final String EXTRA_FRIEND_PROFILE = "extra_profile";
  private static final String TAG = "UserDetailDialog";

  @BindView(R.id.user_detail_iv_avatar) ImageView mAvatarView;
  @BindView(R.id.user_detail_tv_nickname) TextView mNicknameView;
  @BindView(R.id.user_detail_tv_description) TextView mDescriptionView;
  @BindView(R.id.user_detail_tv_location) TextView mLocationView;
  @BindView(R.id.user_detail_tv_following_count) TextView mFollowingCountView;
  @BindView(R.id.user_detail_tv_follower_count) TextView mFollowerCountView;

  private Unbinder unbinder;

  public static UserDetailDialogFragment newInstance(Profile friend) {
    Bundle args = new Bundle();
    UserDetailDialogFragment fragment = new UserDetailDialogFragment();
    args.putParcelable(EXTRA_FRIEND_PROFILE, friend);
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.dialog_fragment_user_detail, container);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    unbinder = ButterKnife.bind(this, view);

    Profile friend = getArguments().getParcelable(EXTRA_FRIEND_PROFILE);

    if (friend == null) {
      return;
    }
    WeiboReader.readAvatar(getContext(), mAvatarView, friend.avatarUrl());
    WeiboReader.readNickname(mNicknameView, friend.nickname());

    boolean isMale = "m".equals(friend.gender());
    mNicknameView.setCompoundDrawablesWithIntrinsicBounds(0, 0,
        isMale ? R.drawable.ic_user_male : R.drawable.ic_user_female, 0);

    WeiboReader.readLocation(mLocationView, friend.location());
    WeiboReader.readDescription(mDescriptionView, friend.description());
    mFollowerCountView.setText(String.valueOf(friend.follower()));
    mFollowingCountView.setText(String.valueOf(friend.follow()));
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }

  public void show(FragmentManager fragmentManager) {
    this.show(fragmentManager, TAG);
  }
}
