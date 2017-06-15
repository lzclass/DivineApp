package com.liuzhao.divineapp.ui.constellation;

import com.liuzhao.divineapp.base.BasePresenter;
import com.liuzhao.divineapp.base.BaseView;
import com.liuzhao.divineapp.data.entity.constellation.Constellation;

import java.util.List;

/**
 * Created by liuzhao on 2017/6/15.
 */

public class ConstellationContract {
    interface View extends BaseView<ConstellationContract.Presenter> {
        void refreshRecyclerView(List<Constellation> list);
    }

    interface Presenter extends BasePresenter {
        void initData();//获取网络数据
    }
}
