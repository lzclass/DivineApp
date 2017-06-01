package com.liuzhao.divineapp.ui.login;

import com.liuzhao.divineapp.base.BasePresenter;
import com.liuzhao.divineapp.base.BaseView;

/**
 * Created by liuzhao on 2017/5/27.
 */

public interface LoginContract {
    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {
        void qqLogin();//QQ登录

        void weixinLogin();//微信登录
    }
}
