package com.liuzhao.divineapp.view;

import com.liuzhao.divineapp.bean.UserResult;

/**
 * Created by liuzhao on 2017/5/26.
 */

public interface LoginView {
    //得到用户填写的信息
    String getUsername();
    String getPassword();

    //显示和隐藏登录ProgressBar
    void showLoading();
    void hideLoading();

    //登录成功或失败后，返回信息的方法
    void showSuccessMsg(UserResult user);
    void showFailedMsg(String s);

    //清除数据
    void clearEditText();
}
