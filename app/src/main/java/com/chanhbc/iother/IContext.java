package com.chanhbc.iother;

import android.app.Application;
import android.content.Context;

public class IContext extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }
}
