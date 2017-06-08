package com.liuzhao.divineapp.data.local;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.liuzhao.divineapp.base.BaseApplication;
import com.liuzhao.divineapp.data.entity.UserResult;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by liuzhao on 2017/6/7.
 */

public class DBManager {
    static private DBManager dbMgr = new DBManager();
    private DbHelper dbHelper;

    private DBManager() {
        dbHelper = DbHelper.getInstance(BaseApplication.getSelf());
    }

    public static synchronized DBManager getInstance() {
        if (dbMgr == null) {
            dbMgr = new DBManager();
        }
        return dbMgr;
    }

    /**
     * 获取联系人list
     *
     * @return
     */
    synchronized public Map<String, UserResult> getUserList() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Map<String, UserResult> users = new Hashtable<String, UserResult>();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery(
                    "select * from " + UserDao.TABLE_NAME /* + " desc" */, null);
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor
                        .getColumnIndex(UserDao.COLUMN_NAME_NAME));
                String userId = cursor.getString(cursor
                        .getColumnIndex(UserDao.COLUMN_NAME_USER_ID));
                String accessToken = cursor.getString(cursor
                        .getColumnIndex(UserDao.COLUMN_NAME_ACCESSTOKEN));
                String city = cursor.getString(cursor
                        .getColumnIndex(UserDao.COLUMN_NAME_CITY));
                String expiration = cursor.getString(cursor
                        .getColumnIndex(UserDao.COLUMN_NAME_EXPIRATION));
                String gender = cursor.getString(cursor
                        .getColumnIndex(UserDao.COLUMN_NAME_GENDER));
                String province = cursor.getString(cursor
                        .getColumnIndex(UserDao.COLUMN_NAME_PROVINCE));
                String iconUrl = cursor.getString(cursor
                        .getColumnIndex(UserDao.COLUMN_NAME_ICONURL));
                UserResult user = new UserResult();
                user.setName(name);
                user.setUid(userId);
                user.setAccesstoken(accessToken);
                user.setCity(city);
                user.setExpiration(expiration);
                user.setGender(gender);
                user.setProvince(province);
                user.setIconurl(iconUrl);
                users.put(userId, user);
            }
            cursor.close();
        }
        return users;
    }

    /**
     * 删除一个联系人
     *
     * @param userid
     */
    synchronized public void deleteUser(String userid) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.delete(UserDao.TABLE_NAME, UserDao.COLUMN_NAME_USER_ID + " = ?",
                    new String[]{userid});
        }
    }

    /**
     * 保存一个联系人
     *
     * @param user
     */
    synchronized public void saveUser(UserResult user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserDao.COLUMN_NAME_USER_ID, user.getUid());
        if (user.getName() != null)
            values.put(UserDao.COLUMN_NAME_NAME, user.getName());
        if (user.getGender() != null)
            values.put(UserDao.COLUMN_NAME_GENDER, user.getGender());
        if (user.getAccesstoken() != null)
            values.put(UserDao.COLUMN_NAME_ACCESSTOKEN,
                    user.getAccesstoken());
        if (user.getCity() != null)
            values.put(UserDao.COLUMN_NAME_CITY,
                    user.getCity());
        if (user.getExpiration() != null)
            values.put(UserDao.COLUMN_NAME_EXPIRATION, user.getExpiration());
        if (user.getProvince() != null)
            values.put(UserDao.COLUMN_NAME_PROVINCE, user.getProvince());
        if (user.getIconurl() != null)
            values.put(UserDao.COLUMN_NAME_ICONURL, user.getIconurl());
        if (db.isOpen()) {
            db.replace(UserDao.TABLE_NAME, null, values);
        }
    }

    /**
     * 获取一个联系人
     *
     * @return
     */
    synchronized public UserResult getUser(String userId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        UserResult user = new UserResult();

        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from " + UserDao.TABLE_NAME + " where "
                    + UserDao.COLUMN_NAME_USER_ID + " =?", new String[]{userId});
            if (cursor != null && cursor.moveToFirst()) {
                String name = cursor.getString(cursor
                        .getColumnIndex(UserDao.COLUMN_NAME_NAME));
                String accessToken = cursor.getString(cursor
                        .getColumnIndex(UserDao.COLUMN_NAME_ACCESSTOKEN));
                String city = cursor.getString(cursor
                        .getColumnIndex(UserDao.COLUMN_NAME_CITY));
                String expiration = cursor.getString(cursor
                        .getColumnIndex(UserDao.COLUMN_NAME_EXPIRATION));
                String gender = cursor.getString(cursor
                        .getColumnIndex(UserDao.COLUMN_NAME_GENDER));
                String province = cursor.getString(cursor
                        .getColumnIndex(UserDao.COLUMN_NAME_PROVINCE));
                String iconUrl = cursor.getString(cursor
                        .getColumnIndex(UserDao.COLUMN_NAME_ICONURL));
                user.setName(name);
                user.setUid(userId);
                user.setAccesstoken(accessToken);
                user.setCity(city);
                user.setExpiration(expiration);
                user.setGender(gender);
                user.setProvince(province);
                user.setIconurl(iconUrl);

            }
            cursor.close();
        }

        return user;
    }
}
