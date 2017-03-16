package com.zac4j.yoda.ui.login;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.zac4j.yoda.ui.main.MainActivity;
import com.zac4j.yoda.utils.Logger;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.Callable;

public class LoginActivity extends AppCompatActivity implements WeiboAuthListener {
  private static final String TAG = "LoginActivity";
  private static final String APP_KEY = "3363502716";
  // default redirect url
  public static final String REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";

  private SsoHandler mSsoHandler;
  private final CompositeDisposable mDisposable = new CompositeDisposable();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    AuthInfo authInfo = createAuthorizeInfo();
    mSsoHandler = new SsoHandler(this, authInfo);

    Oauth2AccessToken token = AccessTokenKeeper.readAccessToken(this);
    if (token.isSessionValid()) {
      startMainPage(LoginActivity.this);
      System.out.println("token: " + token.getToken());
    } else {
      mSsoHandler.authorize(this);
    }
  }

  private AuthInfo createAuthorizeInfo() {
    String scope = getWeiboScope();
    return new AuthInfo(this, APP_KEY, REDIRECT_URL, scope);
  }

  private String getWeiboScope() {
    return "email,direct_messages_read,direct_messages_write,"
        + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
        + "follow_app_official_microblog,"
        + "invitation_write";
  }

  private void startMainPage(Context context) {
    Intent intent = new Intent(context, MainActivity.class);
    startActivity(intent);
    LoginActivity.this.finish();
  }

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (mSsoHandler != null) {
      mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
    }
  }

  /*********************** Weibo Auth Listener Implementation *****************************/
  @Override public void onComplete(final Bundle bundle) {
    mDisposable.add(Observable.defer(new Callable<ObservableSource<Oauth2AccessToken>>() {
      @Override public ObservableSource<Oauth2AccessToken> call() throws Exception {
        return Observable.just(Oauth2AccessToken.parseAccessToken(bundle));
      }
    })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(new DisposableObserver<Oauth2AccessToken>() {
          @Override public void onNext(Oauth2AccessToken token) {
            if (token.isSessionValid()) {
              AccessTokenKeeper.writeAccessToken(LoginActivity.this, token);
            } else {
              String code = bundle.getString("code");
              Logger.d(TAG, "\nObtained the code: " + code);
            }
          }

          @Override public void onError(Throwable e) {
            Logger.e(TAG, "Auth.onComplete()", e);
          }

          @Override public void onComplete() {
            Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
            startMainPage(LoginActivity.this);
          }
        }));
  }

  @Override public void onWeiboException(WeiboException e) {
    System.out.println("Weibo error: " + e);
  }

  @Override public void onCancel() {
    LoginActivity.this.finish();
  }
}
