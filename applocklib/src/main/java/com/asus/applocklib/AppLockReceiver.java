package com.asus.applocklib;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by MingChun_Hsu on 2015/10/8.
 */
public class AppLockReceiver extends BroadcastReceiver {

    public static AppLockCallback callback;

    public AppLockReceiver() {};

    public AppLockReceiver(AppLockCallback cb) {
        callback = cb;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String pkg = intent.getStringExtra("pkg");
        boolean isLock = intent.getBooleanExtra("isLocked", false);

        callback.onLocked(pkg, isLock);
    }
}

