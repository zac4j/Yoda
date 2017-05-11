package com.zac4j.yoda.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.zac4j.yoda.R;
import com.zac4j.yoda.data.model.Comment;
import com.zac4j.yoda.di.ActivityContext;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Adapter for Home Notification
 * Created by zac on 17-5-11.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

  private Context mContext;
  private List<Comment> mCommentList;

  @Inject public NotificationAdapter(@ActivityContext Context context) {
    mContext = context;
    mCommentList = new ArrayList<>();
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_weibo, parent, false);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {

  }

  @Override public int getItemCount() {
    if (mCommentList == null || mCommentList.isEmpty()) {
      return 0;
    }
    return mCommentList.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {

    public ViewHolder(View itemView) {
      super(itemView);
    }
  }
}
