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
        void initRecyclerView(List<MainMenu> mList);
    }

    interface Presenter extends BasePresenter {

    }
}
