package com.zac4j.yoda.ui.adapter;

import android.support.v7.util.DiffUtil;
import com.zac4j.yoda.data.model.Weibo;
import java.util.List;
import java.util.Objects;

/**
 * Different util call
 * Created by zac on 17-5-4.
 */

public class DiffCallback extends DiffUtil.Callback {

  private List<Weibo> mCurrentList;
  private List<Weibo> mNewList;

  public DiffCallback(List<Weibo> currentList, List<Weibo> newList) {
    mCurrentList = currentList;
    mNewList = newList;
  }

  @Override public int getOldListSize() {
    if (mCurrentList == null) {
      return 0;
    }
    return mCurrentList.size();
  }

  @Override public int getNewListSize() {
    if (mNewList == null) {
      return 0;
    }
    return mNewList.size();
  }

  @Override public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
    Weibo currentWeibo = mCurrentList.get(oldItemPosition);
    Weibo newWeibo = mNewList.get(newItemPosition);
    return Objects.equals(currentWeibo.getId(), newWeibo.getId());
  }

  @Override public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
    Weibo currentWeibo = mCurrentList.get(oldItemPosition);
    Weibo newWeibo = mNewList.get(newItemPosition);
    return currentWeibo.equals(newWeibo);
  }
}
