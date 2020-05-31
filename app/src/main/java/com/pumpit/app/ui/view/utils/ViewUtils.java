package com.pumpit.app.ui.view.utils;

import android.content.Context;
import android.widget.Toast;

public class ViewUtils {
    public static void showToast(final Context context, final String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
