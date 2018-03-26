package com.zac4j.yoda.util;

import android.annotation.SuppressLint;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import java.lang.reflect.Field;
import timber.log.Timber;

/**
 * Hack {@link BottomNavigationView}.
 * Created by Zac on 2018/3/26.
 */

public class BottomNavigationHelper {

    /**
     * Disable shift mode.
     * source:https://stackoverflow.com/questions/40176244/how-to-disable-bottomnavigationview-shift-mode
     * @param view BottomNavigationView
     */
    @SuppressLint("RestrictedApi")
    public static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Timber.e(e, "Unable to get shift mode field");
        } catch (IllegalAccessException e) {
            Timber.e(e, "Unable to change value of shift mode");
        }
    }
}
