package com.liuzhao.divineapp.ui.main;

import com.liuzhao.divineapp.base.BasePresenter;
import com.liuzhao.divineapp.base.BaseView;
import com.liuzhao.divineapp.data.entity.main.MainMenu;

import java.util.List;

/**
 * Created by liuzhao on 2017/6/7.
 */

public class MainContract {
    interface View extends BaseView<MainContract.Presenter> {
        void refreshUi();
        void setYouMinPoint(String pointNum);
        void initMainMenu();
    }

    interface Presenter extends BasePresenter {
        void initYouMi();
        void unRegisterYouMiNotify();
        void setOfferBrowserConfig();//设置积分墙浏览器标题栏样式

    }
}
