package com.liuzhao.divineapp.data;

import android.content.Context;
import android.support.annotation.NonNull;

import com.liuzhao.divineapp.data.entity.UserResult;
import com.liuzhao.divineapp.data.local.DBManager;

import java.util.LinkedHashMap;
import java.util.Map;


public class UserRepository implements UserDataSource {

    private static UserRepository INSTANCE = null;
    private Map<String, UserResult> mCachedUser;

    private UserRepository(@NonNull Context context) {

    }

    public static UserRepository getInstance(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = new UserRepository(context);
        }
        return INSTANCE;
    }

    public static void destoryInstance() {
        INSTANCE = null;
    }

    @Override
    public void saveUserInfo(UserResult user) {
        if (user == null) {
            return;
        }
        DBManager.getInstance().saveUser(user);
//         Do in memory cache update to keep the app UI up to date
        if (mCachedUser == null) {
            mCachedUser = new LinkedHashMap<>();
        }
        mCachedUser.put(user.getUid(), user);
    }

    @Override
    public UserResult getUserInfo(String userId) {
        if (mCachedUser != null) {
            UserResult userResult = (UserResult) mCachedUser.get(userId);
            if (userResult != null) {
                return userResult;
            }
        }

        return DBManager.getInstance().getUser(userId);
    }
}
