package com.gsbusiness.fancylivecricketscore;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.facebook.ads.AudienceNetworkAds;
import com.gsbusiness.fancylivecricketscore.CricketUtility.UserPreferData;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MyApplications extends Application {
    private AppOpenAds appOpenManager;
    private static final String TAG = MyApplications.class.getSimpleName();
    private static MyApplications _instance;
    private SharedPreferences _sharedPRefrences;
    @Override
    public void onCreate() {
        super.onCreate();

        AudienceNetworkAds.isInitialized(this);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });



        appOpenManager = new AppOpenAds(this);
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {


            public void uncaughtException(Thread thread, Throwable th) {
            }
        });
        _instance = this;
        this._sharedPRefrences = getApplicationContext().getSharedPreferences(UserPreferData.PREFRENCE, 0);

    }
    public static synchronized MyApplications getInstance() {
        MyApplications cricicricline;
        synchronized (MyApplications.class) {
            synchronized (MyApplications.class) {
                cricicricline = _instance;
            }
            return cricicricline;
        }
    }

    public SharedPreferences getPrefrences() {
        return this._sharedPRefrences;
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }
}