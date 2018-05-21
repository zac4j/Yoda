package com.zac4j.yoda.data.local;

import android.content.Context;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.zac4j.yoda.di.ApplicationContext;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by zac on 2018/5/21.
 * Description:access token provider
 */
@Singleton
public class AccessTokenHelper {

    private Oauth2AccessToken mAccessToken;

    @Inject
    public AccessTokenHelper(@ApplicationContext Context context) {
        if (mAccessToken == null) {
            mAccessToken = AccessTokenKeeper.readAccessToken(context);
        }
    }

    public String getToken() {
        return mAccessToken.getToken();
    }

    public String getUid() {
        return mAccessToken.getUid();
    }
}
