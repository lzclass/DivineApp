package com.liuzhao.divineapp.base;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Process;
import android.util.Log;

import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.mipush.sdk.Logger;
import com.xiaomi.mipush.sdk.MiPushClient;

import java.util.List;

import static com.umeng.socialize.utils.Log.TAG;

/**
 * Created by liuzhao on 2017/5/19.
 */

public class BaseApplication extends Application {

    //    public UMShareAPI umShareAPI;
    public static BaseApplication mApp;
    public static final String XIAOMI_PUSH_APP_ID = "2882303761517594135";
    public static final String XIAOMI_PUSH_APP_KEY = "5461759467135";
    public static final String TAG = "your packagename";

    public static BaseApplication getSelf() {
        return mApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        Config.DEBUG = true;
        //初始化push推送服务
        if (shouldInit()) {
            MiPushClient.registerPush(this, XIAOMI_PUSH_APP_ID, XIAOMI_PUSH_APP_KEY);
        }
        LoggerInterface newLogger = new LoggerInterface() {
            @Override
            public void setTag(String tag) {
                // ignore
            }

            @Override
            public void log(String content, Throwable t) {
                Log.d(TAG, content, t);
            }

            @Override
            public void log(String content) {
                Log.d(TAG, content);
            }
        };
        Logger.setLogger(this, newLogger);
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
