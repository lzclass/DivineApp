package com.liuzhao.divineapp.data.local;

import android.provider.BaseColumns;

/**
 * Created by liuzhao on 2017/6/7.
 */

public class UserDao implements BaseColumns {
    public static final String TABLE_NAME = "user";
    public static final String COLUMN_NAME_USER_ID = "uid";
    public static final String COLUMN_NAME_NAME = "name";
    public static final String COLUMN_NAME_ACCESSTOKEN = "accesstoken";
    public static final String COLUMN_NAME_EXPIRATION = "expiration";//过期时间
    public static final String COLUMN_NAME_CITY = "city";
    public static final String COLUMN_NAME_PROVINCE = "province";
    public static final String COLUMN_NAME_GENDER = "gender";//性别
    public static final String COLUMN_NAME_ICONURL = "iconurl";//头像

}