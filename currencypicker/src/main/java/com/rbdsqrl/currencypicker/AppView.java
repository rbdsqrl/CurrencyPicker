package com.rbdsqrl.currencypicker;

import android.app.Dialog;
import android.graphics.Point;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

public class AppView {
    public static void setWindowDimens(Dialog dialog, Double widthFactor, int gravity) throws Exception {
        Window window = dialog.getWindow();
        Point size = new Point();
        Display display = window.getWindowManager().getDefaultDisplay();
        display.getSize(size);
        int width = size.x;
        window.setLayout((int) (width * widthFactor), WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(gravity);
    }

    public static void setWindowProps(Dialog dialog, Boolean isCancelable, int windowFeature) throws Exception {
        Window window = dialog.getWindow();
        window.requestFeature(windowFeature);
        dialog.setCanceledOnTouchOutside(isCancelable);
    }
}
