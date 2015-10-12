package com.asus.applocklib;

/**
 * Created by Alcohol on 2015/10/9.
 */
public interface AppLockCallback {
    void onLocked(String pkg, boolean isLock);
}
