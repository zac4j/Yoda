package com.zac4j.yoda.ui.adapter;

import com.zac4j.yoda.data.model.Weibo;

/**
 * Adapter for
 * Created by Zac on 2018/3/28.
 */

public class WeiboAdapter {

    private Weibo mWeibo;

    public WeiboAdapter(Weibo weibo) {
        mWeibo = weibo;
    }

    public Weibo getWeibo() {
        return mWeibo;
    }

}
