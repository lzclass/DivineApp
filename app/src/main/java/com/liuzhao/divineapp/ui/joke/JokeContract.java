package com.liuzhao.divineapp.ui.joke;

import com.liuzhao.divineapp.base.BasePresenter;
import com.liuzhao.divineapp.base.BaseView;
import com.liuzhao.divineapp.data.entity.main.Joke;

import java.util.List;

/**
 * Created by liuzhao on 2017/6/26.
 */

public class JokeContract {
    interface View extends BaseView<JokeContract.Presenter> {
        void refreshRecyclerView(List<Joke> mList);
        void initRecyclerView();
    }

    interface Presenter extends BasePresenter {
        void getData(int page);
    }
}
