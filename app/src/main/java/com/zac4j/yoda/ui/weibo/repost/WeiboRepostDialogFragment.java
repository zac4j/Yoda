package com.zac4j.yoda.ui.weibo.repost;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.zac4j.yoda.R;
import com.zac4j.yoda.ui.base.BaseDialogFragment;
import javax.inject.Inject;

/**
 * Dialog for weibo repost
 * Created by Zheng on 6/16/2017.
 */

public class WeiboRepostDialogFragment extends BaseDialogFragment implements WeiboRepostView {

  private static final String TAG = "WeiboRepostDialog";
  public static final String EXTRA_WEIBO_ID = "weibo_id";

  private ProgressBar mProgressBar;

  @Inject WeiboRepostPresenter mPresenter;

  public static WeiboRepostDialogFragment newInstance(String weiboId) {
    Bundle args = new Bundle();
    WeiboRepostDialogFragment fragment = new WeiboRepostDialogFragment();
    args.putString(EXTRA_WEIBO_ID, weiboId);
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    getDialogFragmentComponent().inject(this);
    mPresenter.attach(this);
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    mPresenter.detach();
  }

  @NonNull @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
    View view =
        LayoutInflater.from(getContext()).inflate(R.layout.dialog_fragment_weibo_repost, null);

    TextInputEditText contentInput =
        (TextInputEditText) view.findViewById(R.id.weibo_repost_dialog_et_comment);
    CheckBox asComment = (CheckBox) view.findViewById(R.id.weibo_repost_dialog_ckb_as_content);

    mProgressBar = (ProgressBar) view.findViewById(R.id.progress_bar);

    final String token = AccessTokenKeeper.readAccessToken(getContext()).getToken();
    final String weiboId = getArguments().getString(EXTRA_WEIBO_ID);

    AlertDialog.Builder builder = new AlertDialog.Builder(getContext()).setView(view)
        .setIcon(R.drawable.ic_weibo)
        .setMessage(R.string.weibo_repost)
        .setPositiveButton(R.string.ok, (dialogInterface, i) -> {

        })
        .setNegativeButton(R.string.cancel, null);

    return builder.create();
  }

  private void repostWeibo(String token, String weiboId, TextInputEditText contentInput,
      CheckBox asComment) {
    String content =
        TextUtils.isEmpty(contentInput.getText()) ? "" : contentInput.getText().toString();
    int ifAsComment = asComment.isChecked() ? 1 : 0;
  }

  public void show(FragmentManager fragmentManager) {
    this.show(fragmentManager, TAG);
  }

  @Override public void showProgress(boolean show) {
    if (mProgressBar == null) {
      return;
    }

    mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
  }

  @Override public void showEmptyView(boolean show) {

  }

  @Override public void showErrorView(String error) {

  }
}
