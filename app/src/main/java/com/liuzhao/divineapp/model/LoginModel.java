package com.liuzhao.divineapp.model;

import com.liuzhao.divineapp.bean.UserResult;

/**
 * Created by liuzhao on 2017/5/26.
 */

//接口类
public interface LoginModel {
    void login(String username, String password, OnLoginListener onLoginListener);

    interface OnLoginListener {
        void loginSuccess(UserResult user);

        void loginFailed(String s);
    }
}
