package com.zac4j.yoda.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Main Pager Adapter
 * Created by zac on 3/16/2017.
 */

public class MainPagerAdapter extends SmartPagerAdapter {

  private static final int MAIN_PAGER_COUNT = 3;
  private final String[] mMainPagerTitle = {};
  private Context mContext;

  public MainPagerAdapter(FragmentManager fm, Context context) {
    super(fm);
    mContext = context;
  }

  @Override public Fragment getItem(int position) {
    return null;
  }

  @Override public int getCount() {
    return MAIN_PAGER_COUNT;
  }

  @Override public CharSequence getPageTitle(int position) {
    return mMainPagerTitle[position % MAIN_PAGER_COUNT];
  }
}
