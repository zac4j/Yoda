package com.zac4j.yoda;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import com.facebook.stetho.Stetho;
import com.zac4j.yoda.di.ActivityContext;
import com.zac4j.yoda.di.component.ApplicationComponent;
import com.zac4j.yoda.di.component.DaggerApplicationComponent;
import com.zac4j.yoda.di.module.ApplicationModule;
import timber.log.Timber;

/**
 * App
 * Created by zac on 16-7-21.
 */

public class App extends Application
    implements Application.ActivityLifecycleCallbacks, CurrentActivityProvider {

    private ApplicationComponent mApplicationComponent;
    private Activity mCurrentActivity;

    public static App get(@ActivityContext Context context) {
        return (App) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        registerActivityLifecycleCallbacks(this);

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
            Timber.plant(new Timber.DebugTree());
        }
    }

    public ApplicationComponent getApplicationComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        }
        return mApplicationComponent;
    }

    @Override
    public Activity provideCurrentActivity() {
        return mCurrentActivity;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        mCurrentActivity = activity;
    }

    @Override
    public void onActivityStarted(Activity activity) {
        mCurrentActivity = activity;
    }

    @Override
    public void onActivityResumed(Activity activity) {
        mCurrentActivity = activity;
    }

    @Override
    public void onActivityPaused(Activity activity) {
        mCurrentActivity = null;
    }

    @Override
    public void onActivityStopped(Activity activity) {
        // onStop always be called after a new activity created
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        // do nothing
    }

    @Override
    public void onTerminate() {
        unregisterActivityLifecycleCallbacks(this);
        super.onTerminate();
    }
}
