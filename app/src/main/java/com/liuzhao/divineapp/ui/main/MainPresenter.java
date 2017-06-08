package com.liuzhao.divineapp.ui.main;

import android.support.annotation.NonNull;

import com.liuzhao.divineapp.data.UserRepository;

/**
 * Created by liuzhao on 2017/6/7.
 */

public class MainPresenter implements MainContract.Presenter {
    @NonNull
    private MainContract.View mainView;
    private MainActivity mContext;

    public MainPresenter(MainActivity mContext, @NonNull MainContract.View mainView) {
        this.mainView = mainView;
        this.mContext = mContext;
        mainView.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
}
