package com.liuzhao.divineapp.ui.setting;

import com.liuzhao.divineapp.base.BasePresenter;
import com.liuzhao.divineapp.base.BaseView;

/**
 * Created by liuzhao on 2017/6/14.
 */

public class SettingContract {
    interface View extends BaseView<SettingContract.Presenter> {
        void setVersion(String version);//设置版本号
    }

    interface Presenter extends BasePresenter {
        void loginOut();//退出登录

        void aboutUs();//关于我们

        void evaluateUs();//评价我们

        void sendFeedback();//反馈
    }
}