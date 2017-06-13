package com.liuzhao.divineapp.utils;

/**
 * Created by liuzhao on 2017/6/13.
 */

public class StringUtils {
    public static boolean isEmpty(String s) {
        if (s == null || s.equals("")) {
            return true;
        }
        return false;
    }
}
