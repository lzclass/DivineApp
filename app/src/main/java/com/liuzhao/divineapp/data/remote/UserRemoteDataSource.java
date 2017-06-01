package com.liuzhao.divineapp.data.remote;

import com.liuzhao.divineapp.data.UserDataSource;
import com.liuzhao.divineapp.data.entity.UserResult;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/11 0011.
 */
public class UserRemoteDataSource implements UserDataSource {

    private static UserRemoteDataSource INSTANCE;

    private final static Map<String, UserResult> USERS_SERVICE_DATA;

    static {
        USERS_SERVICE_DATA = new LinkedHashMap<>();
    }

    public static UserRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserRemoteDataSource();
        }
        return INSTANCE;
    }

    // Prevent direct instantiation.
    private UserRemoteDataSource() {

    }

    @Override
    public void saveUserInfo(UserResult user) {
        USERS_SERVICE_DATA.put(user.getUid(), user);
    }
}
