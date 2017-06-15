package com.liuzhao.divineapp.data.entity.constellation;

import java.io.Serializable;

/**
 * Created by liuzhao on 2017/6/15.
 */

public class Constellation implements Serializable {
    private String name;//名称
    private String date;//日期
    private String property;//属性
    private int imageDrawble;//图片

    public int getImageDrawble() {
        return imageDrawble;
    }

    public void setImageDrawble(int imageDrawble) {
        this.imageDrawble = imageDrawble;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }
}
