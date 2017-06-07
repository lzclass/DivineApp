package com.liuzhao.divineapp.ui.main;

import com.liuzhao.divineapp.base.BasePresenter;
import com.liuzhao.divineapp.base.BaseView;

/**
 * Created by liuzhao on 2017/6/7.
 */

public class MainContract {
    interface View extends BaseView<MainContract.Presenter> {
        void refreshUi();
    }

    interface Presenter extends BasePresenter {

    }
}
