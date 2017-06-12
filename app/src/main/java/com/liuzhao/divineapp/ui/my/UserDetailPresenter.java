package com.liuzhao.divineapp.ui.my;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.liuzhao.divineapp.utils.BaZiUtils;
import com.liuzhao.divineapp.utils.CalendarUtils;

import java.util.Calendar;

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

    BaZiUtils baZiUtils;

    @Override
    public void editBirthday(final TextView tv_birthday, final TextView tv_nongliDate, final TextView tv_shuxiang, final TextView tv_xingzuo) {
        Calendar c = Calendar.getInstance();
        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        new DatePickerDialog(mContext,
                // 绑定监听器
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        ++monthOfYear;
                        tv_birthday.setText(year + "-" + monthOfYear
                                + "-" + dayOfMonth);
                        String xingzuo = CalendarUtils.getAstro(monthOfYear, dayOfMonth);//星座

                        String animalsYear = CalendarUtils.getAnimalsYear(year);//属相
                        String nongliDate = CalendarUtils.getSolarToLunar(year, monthOfYear, dayOfMonth);
                        tv_nongliDate.setText(nongliDate);
                        tv_shuxiang.setText(animalsYear);
                        tv_xingzuo.setText(xingzuo);
                    }
                }
                // 设置初始日期
                , c.get(Calendar.YEAR), c.get(Calendar.MONTH), c
                .get(Calendar.DAY_OF_MONTH)).show();
    }

    @Override
    public void editBirthTime(final TextView tv_birthTime, final TextView tv_nongliTime) {
        Calendar c = Calendar.getInstance();
        // 创建一个TimePickerDialog实例，并把它显示出来
        new TimePickerDialog(mContext,
                // 绑定监听器
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view,
                                          int hourOfDay, int minute) {
                        tv_birthTime.setText(hourOfDay + ":" + minute);
                        tv_nongliTime.setText(CalendarUtils.getChinaHour(hourOfDay));
                    }
                }
                // 设置初始时间
                , c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE),
                // true表示采用24小时制
                true).show();
    }

    @Override
    public void editSex(TextView view) {

    }

    @Override
    public void editRealName(TextView view) {

    }
}
