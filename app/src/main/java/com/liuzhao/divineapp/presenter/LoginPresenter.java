package com.liuzhao.divineapp.presenter;


import android.os.Handler;

import com.liuzhao.divineapp.model.LoginModelImpl;
import com.liuzhao.divineapp.bean.UserResult;
import com.liuzhao.divineapp.model.LoginModel;
import com.liuzhao.divineapp.view.LoginView;

/**
 * Created by liuzhao on 2017/5/26.
 */

public class LoginPresenter {
    private LoginView loginView;
    private LoginModel loginModel;
    private Handler mHandler;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
        loginModel = new LoginModelImpl();
        mHandler = new Handler();
    }

    public void login() {
        loginView.showLoading();
        loginModel.login(loginView.getUsername(), loginView.getPassword(), new LoginModel.OnLoginListener() {
            @Override
            public void loginSuccess(final UserResult user) {
                //模拟登录成功后，返回信息到Activity,吐出成功信息
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginView.showSuccessMsg(user);
                        loginView.hideLoading();
                    }
                });
            }

            @Override
            public void loginFailed(final String s) {
                //模拟登录失败后，返回信息，吐出错误信息
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginView.showFailedMsg(s);
                        loginView.hideLoading();
                    }
                });
            }
        });
    }

    public void clear(){
        loginView.clearEditText();
    }
}
