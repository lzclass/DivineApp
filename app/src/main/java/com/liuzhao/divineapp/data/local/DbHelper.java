package com.liuzhao.divineapp.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/7/12 0012.
 */
public class DbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 4;//数据库版本
    public static final String DATABASE_NAME = "divine_lz.db";//数据库名称
    private static DbHelper instance;

    private static final String TEXT_TYPE = " TEXT,";

    //创建用户表的sql句
    private static final String USERNAME_TABLE_CREATE = "CREATE TABLE "
            + UserDao.TABLE_NAME + " ("
            + UserDao.COLUMN_NAME_NAME + TEXT_TYPE
            + UserDao.COLUMN_NAME_NICK_NAME + TEXT_TYPE
            + UserDao.COLUMN_NAME_ACCESSTOKEN + TEXT_TYPE
            + UserDao.COLUMN_NAME_EXPIRATION + TEXT_TYPE
            + UserDao.COLUMN_NAME_CITY + TEXT_TYPE
            + UserDao.COLUMN_NAME_PROVINCE + TEXT_TYPE
            + UserDao.COLUMN_NAME_GENDER + TEXT_TYPE
            + UserDao.COLUMN_NAME_ICONURL + TEXT_TYPE
            + UserDao.COLUMN_NAME_BIRTHDAY_NONGLI + TEXT_TYPE
            + UserDao.COLUMN_NAME_BIRTHDAY + TEXT_TYPE
            + UserDao.COLUMN_NAME_ANIMAL_SIGN + TEXT_TYPE
            + UserDao.COLUMN_NAME_BIRTH_TIME + TEXT_TYPE
            + UserDao.COLUMN_NAME_BIRTH_TIME_NONGLI + TEXT_TYPE
            + UserDao.COLUMN_NAME_CONSTELLATION + TEXT_TYPE
            + UserDao.COLUMN_NAME_USER_ID
            + " TEXT PRIMARY KEY);";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DbHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USERNAME_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 使用for实现跨版本升级数据库
//        for (int i = oldVersion; i < newVersion; i++) {
//            switch (i) {
//                case 3:
//                    upgradeToVersion2(db);
//                    break;
//                default:
//                    break;
//            }
//        }
    }

//    private void upgradeToVersion2(SQLiteDatabase db) {
//        // user表新增1个字段
//        String sql1 = "ALTER TABLE " + UserDao.TABLE_NAME + " ADD COLUMN " + UserDao.COLUMN_NAME_BIRTH_TIME_NONGLI + " TEXT;";
//        db.execSQL(sql1);
//    }

    public void closeDB() {
        if (instance != null) {
            try {
                SQLiteDatabase db = instance.getWritableDatabase();
                db.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            instance = null;
        }
    }
}
