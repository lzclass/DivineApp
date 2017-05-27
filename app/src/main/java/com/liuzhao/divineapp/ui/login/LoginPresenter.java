package com.liuzhao.divineapp.ui.login;


import android.support.annotation.NonNull;

/**
 * Created by liuzhao on 2017/5/26.
 */

public class LoginPresenter implements LoginContract.Presenter {
    @NonNull
    private final LoginContract.View loginView;

    public LoginPresenter(@NonNull LoginContract.View loginView) {
        this.loginView = loginView;
        loginView.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

}
