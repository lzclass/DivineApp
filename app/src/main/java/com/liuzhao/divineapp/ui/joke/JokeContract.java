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

        void initRecyclerView(List<Joke> mList);
    }

    interface Presenter extends BasePresenter {
        void getData(int page);
    }
}
