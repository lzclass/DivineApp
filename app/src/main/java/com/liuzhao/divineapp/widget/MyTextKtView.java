package com.liuzhao.divineapp.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * TODO: document your custom view class.
 */
public class MyTextKtView extends TextView {
    public MyTextKtView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        Typeface typeFace = Typeface.createFromAsset(getResources().getAssets(), "fonts/hwxk.ttf");
        setTypeface(typeFace);
    }
}
