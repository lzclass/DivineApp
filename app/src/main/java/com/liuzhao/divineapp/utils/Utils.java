package com.liuzhao.divineapp.utils;

import android.widget.Toast;

import com.liuzhao.divineapp.base.BaseApplication;

/**
 * Created by liuzhao on 2017/7/20.
 */

public class Utils {
    public static void toast(String strMsg) {
        Toast.makeText(BaseApplication.getSelf(),strMsg,Toast.LENGTH_SHORT).show();
    }
}
