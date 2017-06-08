package com.liuzhao.divineapp.ui.my;

/**
 * Created by liuzhao on 2017/6/8.
 */

public class UserDetailPresenter implements UserDetailContract.Presenter {
    private UserDetailContract.View userDetailView;
    private UserDetailActivity mContext;

    public UserDetailPresenter(UserDetailActivity mContext, UserDetailContract.View userDetailView) {
        this.mContext = mContext;
        this.userDetailView = userDetailView;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

}
