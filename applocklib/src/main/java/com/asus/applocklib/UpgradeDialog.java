package com.asus.applocklib;

import android.app.Dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Created by MingChun_Hsu on 2015/9/24.
 */
public class UpgradeDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Dialog dialog = new Dialog(getActivity());

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        View promptView = LayoutInflater.from(getActivity()).inflate(R.layout.upgrade_layout, null);
        Button button = (Button) promptView.findViewById(R.id.upgrade_now);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppLockLib.toPlay(getActivity());
                dialog.dismiss();
            }
        });

        dialog.setContentView(promptView);

        return dialog;
    }
}
