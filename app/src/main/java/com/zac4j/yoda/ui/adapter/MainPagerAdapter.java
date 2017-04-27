package com.zac4j.yoda.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import com.zac4j.yoda.ui.home.HomeNotificationFragment;
import com.zac4j.yoda.ui.home.HomeTimelineFragment;
import java.util.ArrayList;
import java.util.List;

/**
 * Main Pager Adapter
 * Created by zac on 3/16/2017.
 */

public class MainPagerAdapter extends SmartPagerAdapter {

  public static final int MAIN_PAGER_COUNT = 5;

  public MainPagerAdapter(FragmentManager fm) {
    super(fm);
  }

  @Override public Fragment getItem(int position) {
    switch (position) {
      case 0:
        return HomeTimelineFragment.newInstance(false);
      case 1:
        return HomeTimelineFragment.newInstance(true);
      case 2:
        return HomeNotificationFragment.newInstance();
      case 3:
        break;
      case 4:
        break;
    }
    return null;
  }

  @Override public int getCount() {
    return MAIN_PAGER_COUNT;
  }
}
