package com.liuzhao.divineapp.data.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.liuzhao.divineapp.data.UserDataSource;
import com.liuzhao.divineapp.data.entity.UserResult;

/**
 * Created by Administrator on 2016/7/11 0011.
 */
public class UserLocalDataSource implements UserDataSource {

    private static UserLocalDataSource INSTANCE = null;

    private AppDbHelper mAppDbHelper;

    private UserLocalDataSource(@NonNull Context context) {
        mAppDbHelper = new AppDbHelper(context);
    }

    public static UserLocalDataSource getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new UserLocalDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void saveUserInfo(UserResult user) {
        if (user == null) {
            return;
        }

        SQLiteDatabase db = mAppDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UsersPersistenceContract.UserEntry.COLUMN_NAME_USER_ID, user.getUid());

        db.insert(UsersPersistenceContract.UserEntry.TABLE_NAME, null, values);
        db.close();
    }
}
