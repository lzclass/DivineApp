package com.liuzhao.divineapp.ui.setting;

import android.app.Activity;
import android.support.annotation.Nullable;

import com.liuzhao.divineapp.BuildConfig;
import com.liuzhao.divineapp.base.BaseApplication;
import com.liuzhao.divineapp.data.local.PreferencesManager;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by liuzhao on 2017/6/14.
 */

public class SettingPresenter implements SettingContract.Presenter {
    public SettingContract.View settingView;
    public SettingActivity mContext;

    public SettingPresenter(SettingActivity mContext, @Nullable SettingContract.View settingView) {
        this.mContext = mContext;
        settingView = checkNotNull(settingView);
        settingView.setPresenter(this);
        settingView.setVersion("V " + BuildConfig.VERSION_NAME);
    }

    @Override
    public void aboutUs() {

    }

    @Override
    public void loginOut() {
        UMShareAPI.get(BaseApplication.getSelf()).deleteOauth(mContext, SHARE_MEDIA.QQ, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {

            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                PreferencesManager.USER.deleteString(PreferencesManager.PREFERENCES_USER_ID);
                mContext.setResult(Activity.RESULT_OK);
                mContext.finish();
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {

            }
        });

    }

    @Override
    public void evaluateUs() {

    }

    @Override
    public void sendFeedback() {

    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

}
