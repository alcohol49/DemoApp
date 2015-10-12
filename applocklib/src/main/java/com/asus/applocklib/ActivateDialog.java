package com.asus.applocklib;

import android.app.Dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;


/**
 * Created by MingChun_Hsu on 2015/9/24.
 */
public class ActivateDialog extends DialogFragment {

    public static ActivateDialog newInstance(String pkg) {
        ActivateDialog f = new ActivateDialog();

        Bundle args = new Bundle();
        args.putString("pkg", pkg);
        f.setArguments(args);

        return f;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final String pkg = getArguments().getString("pkg");

        final Dialog dialog = new Dialog(getActivity());

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        View promptView = LayoutInflater.from(getActivity()).inflate(R.layout.activate_layout, null);
        Button button = (Button) promptView.findViewById(R.id.lock_now);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppLockLib.lockThisOrNot(getActivity(), pkg, true);
                dialog.dismiss();
            }
        });

        dialog.setContentView(promptView);

        return dialog;
    }
}
