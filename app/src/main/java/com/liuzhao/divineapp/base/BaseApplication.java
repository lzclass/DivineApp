package com.liuzhao.divineapp.base;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Process;

import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;


import java.util.List;

/**
 * Created by liuzhao on 2017/5/19.
 */

public class BaseApplication extends Application {

    //    public UMShareAPI umShareAPI;
    public static BaseApplication mApp;

    public static BaseApplication getSelf() {
        return mApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        Config.DEBUG = true;

    }

    static {
        PlatformConfig.setQQZone("1106191666", "Qt7zDyOBdrLQ9hM9");
//        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
//        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
    }

    private boolean shouldInit() {
        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }

}
