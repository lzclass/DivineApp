package com.liuzhao.divineapp.data.local;

import android.provider.BaseColumns;

/**
 * Created by liuzhao on 2017/6/7.
 */

public class UserDao implements BaseColumns {
    public static final String TABLE_NAME = "user";
    public static final String COLUMN_NAME_USER_ID = "uid";
    public static final String COLUMN_NAME_NAME = "name";
    public static final String COLUMN_NAME_NICK_NAME = "nickName";
    public static final String COLUMN_NAME_ACCESSTOKEN = "accesstoken";
    public static final String COLUMN_NAME_EXPIRATION = "expiration";//过期时间
    public static final String COLUMN_NAME_CITY = "city";
    public static final String COLUMN_NAME_PROVINCE = "province";
    public static final String COLUMN_NAME_GENDER = "gender";//性别
    public static final String COLUMN_NAME_ICONURL = "iconurl";//头像
    public static final String COLUMN_NAME_BIRTHDAY_NONGLI = "birthDayNongli";//农历生日
    public static final String COLUMN_NAME_BIRTHDAY = "birthDay";//阳历生日
    public static final String COLUMN_NAME_ANIMAL_SIGN = "animalSign";//生肖
    public static final String COLUMN_NAME_BIRTH_TIME = "birthTime";//时辰
    public static final String COLUMN_NAME_BIRTH_TIME_NONGLI = "birthTimeNongli";//农历时辰
    public static final String COLUMN_NAME_CONSTELLATION = "constellation";//星座

}