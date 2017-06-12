package com.liuzhao.divineapp.ui.my;

import android.widget.TextView;

import com.liuzhao.divineapp.base.BasePresenter;
import com.liuzhao.divineapp.base.BaseView;

/**
 * Created by liuzhao on 2017/6/8.
 */

public class UserDetailContract {
    interface View extends BaseView<UserDetailContract.Presenter> {
        void refreshUi();
    }

    interface Presenter extends BasePresenter {
        void editSex(TextView view);

        void editBirthday(TextView birthDay,TextView tv_nongliDate,TextView tv_shengxiao,TextView tv_xingzuo);

        void editBirthTime(TextView birthTime,TextView tv_nongliTime);

        void editRealName(TextView view);
    }
}
