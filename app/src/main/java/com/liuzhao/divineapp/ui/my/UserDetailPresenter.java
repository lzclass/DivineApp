package com.liuzhao.divineapp.ui.my;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.liuzhao.divineapp.R;
import com.liuzhao.divineapp.base.BaseApplication;
import com.liuzhao.divineapp.data.UserRepository;
import com.liuzhao.divineapp.data.entity.UserResult;
import com.liuzhao.divineapp.data.local.PreferencesManager;
import com.liuzhao.divineapp.utils.CalendarUtils;
import com.liuzhao.divineapp.widget.HanZiEditText;

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
                        String birthday = new StringBuilder().append(year).append("-")
                                .append(monthOfYear < 10 ? "0" + monthOfYear : monthOfYear)
                                .append("-")
                                .append((dayOfMonth < 10) ? "0" + dayOfMonth : dayOfMonth).toString();
                        String constellation = CalendarUtils.getAstro(monthOfYear, dayOfMonth);//星座
                        int[] nongli = CalendarUtils.solarToLunar(year, monthOfYear, dayOfMonth);
                        String nongliDao = nongli[0] + "-" + nongli[1] + "-" + nongli[2];
                        String nongliDate = CalendarUtils.getSolarToLunar(year, monthOfYear, dayOfMonth);
                        String animalsYear = CalendarUtils.getAnimalsYear(nongli[0]);//属相
                        tv_birthday.setText(birthday);
                        tv_nongliDate.setText(nongliDate);
                        tv_shuxiang.setText(animalsYear);
                        tv_xingzuo.setText(constellation);

                        UserResult userResult = UserRepository.getInstance(BaseApplication.getSelf()).getUserInfo(PreferencesManager.USER.getUserId());
                        userResult.setUid(PreferencesManager.USER.getUserId());
                        userResult.setConstellation(constellation);
                        userResult.setAnimalSign(animalsYear);
                        userResult.setBirthDay(birthday);
                        userResult.setBirthDayNongli(nongliDao);
                        UserRepository.getInstance(BaseApplication.getSelf()).saveUserInfo(userResult);
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
                        String birthTime = new StringBuilder()
                                .append(hourOfDay < 10 ? "0" + hourOfDay : hourOfDay)
                                .append(":")
                                .append(minute < 10 ? "0" + minute : minute).toString();
                        tv_birthTime.setText(birthTime);
                        String birthTimeNongli = CalendarUtils.getChinaHour(hourOfDay);
                        tv_nongliTime.setText(birthTimeNongli);
                        UserResult userResult = UserRepository.getInstance(BaseApplication.getSelf()).getUserInfo(PreferencesManager.USER.getUserId());
                        userResult.setUid(PreferencesManager.USER.getUserId());
                        userResult.setBirthTime(birthTime);
                        userResult.setBirthTimeNongli(birthTimeNongli);
                        UserRepository.getInstance(BaseApplication.getSelf()).saveUserInfo(userResult);
                    }
                }
                // 设置初始时间
                , c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE),
                // true表示采用24小时制
                true).show();
    }

    @Override
    public void editSex(final TextView view) {
        int checkedItem = 0;
        if ("女".equals(view.getText())) {
            checkedItem = 1;
        }
        new AlertDialog.Builder(mContext)
                // 设置对话框标题
                .setTitle("请选择性别")
                .setSingleChoiceItems(new String[]{"男", "女"}, checkedItem,
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                view.setText(which == 0 ? "男" : "女");
                                dialog.dismiss();
                            }
                        }
                )
                .setNegativeButton("取消", null)
                .show();

    }

    @Override
    public void editRealName(final TextView view) {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.dialog_layout_edittext, null);
        final HanZiEditText et_realName = (HanZiEditText) contentView.findViewById(R.id.et_realName);
        new AlertDialog.Builder(mContext)
                // 设置对话框标题
                .setTitle("请输入真实姓名")
                .setView(contentView)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        view.setText(et_realName.getText().toString());
                        UserResult userResult = UserRepository.getInstance(BaseApplication.getSelf()).getUserInfo(PreferencesManager.USER.getUserId());
                        userResult.setUid(PreferencesManager.USER.getUserId());
                        userResult.setName(et_realName.getText().toString());
                        UserRepository.getInstance(BaseApplication.getSelf()).saveUserInfo(userResult);
                    }
                })
                .setNegativeButton("取消", null)
                .show();

    }
}
