package com.zac4j.yoda.ui.weibo.repost;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.zac4j.yoda.R;
import com.zac4j.yoda.data.model.Weibo;
import com.zac4j.yoda.ui.base.BaseDialogFragment;
import javax.inject.Inject;

/**
 * Dialog for weibo repost
 * Created by Zheng on 6/16/2017.
 */

public class WeiboRepostDialogFragment extends BaseDialogFragment implements WeiboRepostView {

  private static final String TAG = "WeiboRepostDialog";
  public static final String EXTRA_WEIBO_ID = "weibo_id";

  public interface OnRepostListener {
    void onSuccess(Weibo weibo);

    void onFailure();
  }

  private OnRepostListener mRepostListener;

  private ProgressBar mProgressBar;
  private AlertDialog mAlertDialog;
  @Inject WeiboRepostPresenter mPresenter;

  public static WeiboRepostDialogFragment newInstance(String weiboId) {
    Bundle args = new Bundle();
    WeiboRepostDialogFragment fragment = new WeiboRepostDialogFragment();
    args.putString(EXTRA_WEIBO_ID, weiboId);
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getDialogFragmentComponent().inject(this);
    mPresenter.attach(this);
  }

  @NonNull @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
    View view =
        LayoutInflater.from(getContext()).inflate(R.layout.dialog_fragment_weibo_repost, null);

    final String token = AccessTokenKeeper.readAccessToken(getContext()).getToken();
    final String weiboId = getArguments().getString(EXTRA_WEIBO_ID);

    mAlertDialog = new AlertDialog.Builder(getContext()).setView(view)
        .setIcon(R.drawable.ic_weibo)
        .setTitle(R.string.app_name)
        .create();

    mProgressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
    TextInputEditText contentInput =
        (TextInputEditText) view.findViewById(R.id.weibo_repost_dialog_et_comment);
    CheckBox asComment = (CheckBox) view.findViewById(R.id.weibo_repost_dialog_ckb_as_content);

    view.findViewById(R.id.weibo_repost_dialog_tv_ok)
        .setOnClickListener(view12 -> repostWeibo(token, weiboId, contentInput, asComment));

    view.findViewById(R.id.weibo_repost_dialog_tv_cancel)
        .setOnClickListener(view1 -> mAlertDialog.hide());

    return mAlertDialog;
  }

  public void setOnRepostListener(OnRepostListener repostListener) {
    mRepostListener = repostListener;
  }

  private void repostWeibo(String token, String weiboId, TextInputEditText contentInput,
      CheckBox asComment) {
    String content =
        TextUtils.isEmpty(contentInput.getText()) ? "转发微博" : contentInput.getText().toString();
    int ifAsComment = asComment.isChecked() ? 1 : 0;

    mPresenter.repostWeibo(token, weiboId, content, ifAsComment);
  }

  @Override public void hide() {
    mAlertDialog.hide();
  }

  @Override public void show(FragmentManager fragmentManager) {
    this.show(fragmentManager, TAG);
  }

  @Override public void onRepostSuccess(Weibo weibo) {
    mRepostListener.onSuccess(weibo);
  }

  @Override public void onRepostFailure() {
    mRepostListener.onFailure();
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
    Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
  }
}
