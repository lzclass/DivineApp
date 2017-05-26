package com.liuzhao.divineapp.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by liuzhao on 2017/5/19.
 */

public class BaseApplication extends Application {


    public static BaseApplication get(Context context){
        return (BaseApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }


}
