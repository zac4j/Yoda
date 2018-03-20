package com.zac4j.yoda.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.zac4j.yoda.ui.home.hot.HotTagFragment;
import com.zac4j.yoda.ui.home.message.MessengerFragment;
import com.zac4j.yoda.ui.home.notif.NotificationFragment;
import com.zac4j.yoda.ui.home.timeline.TimelineFragment;
import com.zac4j.yoda.ui.home.user.UserFragment;

/**
 * Pager adapter for main page.
 * Created by Zaccc on 2017/8/22.
 */

public class MainPagerAdapter extends FragmentStatePagerAdapter {

    private static final int MAIN_PAGER_COUNT = 5;

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return TimelineFragment.newInstance();
            case 1:
                return HotTagFragment.newInstance();
            case 2:
                return NotificationFragment.newInstance();
            case 3:
                return MessengerFragment.newInstance();
            case 4:
                return UserFragment.newInstance();
        }
        return TimelineFragment.newInstance();
    }

    @Override
    public int getCount() {
        return MAIN_PAGER_COUNT;
    }
}
