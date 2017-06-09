package com.liuzhao.divineapp.ui.my;

import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.TextView;

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

    @Override
    public void editBirthday(final TextView textView) {
        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                textView.setText(year + month + dayOfMonth + "");
            }
        };
        new DatePickerDialog(mContext, onDateSetListener, 1990, 01, 01).show();
    }

    @Override
    public void editSex(TextView view) {

    }

    @Override
    public void eidtRealName(TextView view) {

    }
}
