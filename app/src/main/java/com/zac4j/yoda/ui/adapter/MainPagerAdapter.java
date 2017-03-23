package com.zac4j.yoda.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Main Pager Adapter
 * Created by zac on 3/16/2017.
 */

public class MainPagerAdapter extends SmartPagerAdapter {

  private static final int MAIN_PAGER_COUNT = 3;
  private final List<Fragment> mPagerContainer = new ArrayList<>();
  private final List<String> mPagerTitles = new ArrayList<>();

  public MainPagerAdapter(FragmentManager fm) {
    super(fm);
  }

  public void addFragment(Fragment fragment, String title) {
    mPagerContainer.add(fragment);
    mPagerTitles.add(title);
  }

  @Override public Fragment getItem(int position) {
    return mPagerContainer.get(position);
  }

  @Override public int getCount() {
    return mPagerContainer.size() % MAIN_PAGER_COUNT;
  }

  @Override public CharSequence getPageTitle(int position) {
    return mPagerTitles.get(position);
  }
}
