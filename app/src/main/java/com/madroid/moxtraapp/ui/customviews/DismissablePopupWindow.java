package com.madroid.moxtraapp.ui.customviews;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by mohamed on 03/05/17.
 */

public class DismissablePopupWindow extends android.widget.PopupWindow {
    Context ctx;

    View popupView;

    public DismissablePopupWindow(Context context, int popup_layout ) {
        super(context);

        ctx = context;
        popupView = LayoutInflater.from(context).inflate(popup_layout, null);
        setContentView(popupView);

        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        setWidth(WindowManager.LayoutParams.WRAP_CONTENT);

        // Closes the popup window when touch outside of it - when looses focus
        setOutsideTouchable(true);
        setFocusable(true);

        // Removes default black background
        setBackgroundDrawable(new BitmapDrawable());



        // Closes the popup window when touch it
		/*
		 * this.setTouchInterceptor(new View.OnTouchListener() {
		 *
		 * @Override public boolean onTouch(View v, MotionEvent event) {
		 *
		 * if (event.getAction() == MotionEvent.ACTION_MOVE) { dismiss(); }
		 * return true; } });
		 */
    } // End constructor

    // Attaches the view to its parent anchor-view at position x and y
    public void show(View anchor, int x, int y) {
        showAtLocation(anchor, Gravity.CENTER, x, y);
    }
}