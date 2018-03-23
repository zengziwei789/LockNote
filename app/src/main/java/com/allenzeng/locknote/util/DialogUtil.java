package com.allenzeng.locknote.util;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.allenzeng.locknote.model.DialogOptionClickListener;

import java.lang.ref.WeakReference;

/**
 * Created by allen.zeng on 3/22/18.
 */

public class DialogUtil {

    public static void showTwoOptionDialog(WeakReference<Activity> weakActivity, String msg, final DialogOptionClickListener listener) {
        if (weakActivity != null) {
            new AlertDialog.Builder(weakActivity.get())
                    .setMessage(msg)
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            if(listener != null) {
                                listener.onNegtiveClicked();
                            }
                        }
                    })
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            if(listener != null) {
                                listener.onPositiveClicked();
                            }
                        }
                    }).show();
        }
    }

    public static void showOneOptionDialog(WeakReference<Activity> weakActivity, String msg) {
        if (weakActivity != null) {
            new AlertDialog.Builder(weakActivity.get())
                    .setMessage(msg)
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();
        }
    }
}
