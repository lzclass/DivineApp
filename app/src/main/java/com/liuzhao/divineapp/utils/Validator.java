package com.liuzhao.divineapp.utils;

import android.text.TextUtils;


public class Validator {

    public Validator() {

    }

    public boolean validUsername(String username) {
        return !TextUtils.isEmpty(username);
    }

    public boolean validPassword(String password) {
        return !TextUtils.isEmpty(password);
    }
}
