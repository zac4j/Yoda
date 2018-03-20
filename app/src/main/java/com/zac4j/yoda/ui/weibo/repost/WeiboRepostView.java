package com.zac4j.yoda.ui.weibo.repost;

import android.support.v4.app.FragmentManager;
import com.zac4j.yoda.data.model.Weibo;
import com.zac4j.yoda.ui.base.MvpView;

/**
 * Weibo repost view
 * Created by Zheng on 6/16/2017.
 */

public interface WeiboRepostView extends MvpView {

    void hide();

    void show(FragmentManager fragmentManager);

    void onRepostSuccess(Weibo weibo);

    void onRepostFailure();
}
