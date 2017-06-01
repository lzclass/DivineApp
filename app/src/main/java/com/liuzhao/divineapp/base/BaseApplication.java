package com.liuzhao.divineapp.base;

import android.app.Application;
import android.content.Context;

import com.umeng.qq.tencent.Tencent;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import static android.provider.UserDictionary.Words.APP_ID;

/**
 * Created by liuzhao on 2017/5/19.
 */

public class BaseApplication extends Application {

    public UMShareAPI umShareAPI;
    public static BaseApplication mApp;

    public static BaseApplication getSelf() {
        return mApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        Config.DEBUG = true;
        umShareAPI = UMShareAPI.get(this);
//        mTencent = Tencent.createInstance(APP_ID, this.getApplicationContext());
    }

    static {
        PlatformConfig.setQQZone("1106191666", "Qt7zDyOBdrLQ9hM9");
//        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
//        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
    }


}
