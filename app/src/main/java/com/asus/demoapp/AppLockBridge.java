package com.asus.demoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.asus.applocklib.AppLockLib;
import com.asus.applocklib.AppLockReceiver;
import com.asus.applocklib.AppLockCallback;

public class AppLockBridge extends AppCompatActivity implements AppLockCallback {

    public static final String PKG_NAME = "com.asus.demoapp";
    public static boolean sIsLocked;
    public static boolean sActionLock;

    private MenuItem mAppLockMenu;
    private boolean mFirstTime = true;

    AppLockReceiver ar;

    @Override
    public void onLocked(String pkg, boolean isLock) {
        Toast.makeText(getApplicationContext(), pkg + " " + isLock, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ar = new AppLockReceiver(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        sIsLocked = AppLockLib.checkIfLocked(this, PKG_NAME);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        mAppLockMenu = menu.findItem(R.id.action_lock_gallery);

        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        // Update menu & action
        if (sIsLocked) {
            mAppLockMenu.setTitle(R.string.action_unlock_gallery);
        } else {
            mAppLockMenu.setTitle(R.string.action_lock_gallery);
        }
        sActionLock = sIsLocked;

        // prevent from first launch
        if (mFirstTime == true) {
            mFirstTime = false;
            return true;
        }
        AppLockLib.sendGaMenuDisplay(this, PKG_NAME);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_lock_gallery) {
            if (!AppLockLib.isLauncherSupport(getApplicationContext())) {
                AppLockLib.showUpgradeDialog(getFragmentManager());
            } else if (!AppLockLib.checkIfActivated(getApplicationContext())) {
                AppLockLib.showActivateDialog(getFragmentManager(), PKG_NAME);
            } else {
                AppLockLib.lockThisOrNot(getApplicationContext(), PKG_NAME, !sActionLock);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onLockThis(View view) {
        AppLockLib.lockThisOrNot(this, PKG_NAME, true);
    }

    public void onUnlockThis(View view) {
        AppLockLib.lockThisOrNot(this, PKG_NAME, false);
    }

    public void onCheckClick(View view) {
        boolean isLocked = AppLockLib.checkIfLocked(this, PKG_NAME);
        Toast.makeText(this, "locked? " + isLocked, Toast.LENGTH_LONG).show();
    }

    public void onPlayClick(View view) {
        AppLockLib.toPlay(this);
    }

    public void onVersionCheck(View view) {
        Toast.makeText(this, "support =" + AppLockLib.isLauncherSupport(getApplicationContext()), Toast.LENGTH_SHORT).show();
    }

    public void onShowUpgrade(View view) {
        AppLockLib.showUpgradeDialog(getFragmentManager());
    }

    public void onShowActivate(View view) {
        AppLockLib.showActivateDialog(getFragmentManager(), PKG_NAME);
    }
}
