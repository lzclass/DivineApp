package com.liuzhao.divineapp.ui.main;

import android.support.annotation.NonNull;

import com.liuzhao.divineapp.data.UserRepository;

/**
 * Created by liuzhao on 2017/6/7.
 */

public class MainPresenter extends MainContract {
    @NonNull
    private MainContract.View mainView;
    private UserRepository mUserRepository;
    private MainActivity mContext;

    public MainPresenter() {
    }
}
