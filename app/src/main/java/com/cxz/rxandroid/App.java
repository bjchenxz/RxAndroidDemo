package com.cxz.rxandroid;

import android.app.Application;

/**
 * Created by chenxz on 2017/3/8.
 */
public class App extends Application {

    public static Application mApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
    }

    public static Application getApplication(){
        return mApp;
    }
}
