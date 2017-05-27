package com.liuzhao.divineapp.ui.login;

import com.liuzhao.divineapp.base.BasePresenter;
import com.liuzhao.divineapp.base.BaseView;
import com.liuzhao.divineapp.bean.UserResult;

/**
 * Created by liuzhao on 2017/5/27.
 */

public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void qqLogin();//QQ登录
    }

    interface Presenter extends BasePresenter {

    }
}
