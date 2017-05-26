package com.liuzhao.divineapp.bean;

/**
 * Created by liuzhao on 2017/5/26.
 */

public class UserResult {
    private String username;
    private String password;

    public UserResult(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
