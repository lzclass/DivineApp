package com.liuzhao.divineapp.data.entity;

/**
 * Created by liuzhao on 2017/5/26.
 */

public class UserResult {
    private String name;
    private String uid;
    private String nickName;
    private String accesstoken;
    private String expiration;//过期时间
    private String city;
    private String province;
    private String gender;//性别
    private String iconurl;//头像
    private String birthDay;//阳历生日
    private String birthDayNongli;//农历生日
    private String animalSign;//生肖
    private String birthTime;//出生时辰
    private String birthTimeNongli;//出生时辰农历
    private String constellation;//星座

    public String getBirthTimeNongli() {
        return birthTimeNongli;
    }

    public void setBirthTimeNongli(String birthTimeNongli) {
        this.birthTimeNongli = birthTimeNongli;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getBirthDayNongli() {
        return birthDayNongli;
    }

    public void setBirthDayNongli(String birthDayNongli) {
        this.birthDayNongli = birthDayNongli;
    }

    public String getAnimalSign() {
        return animalSign;
    }

    public void setAnimalSign(String animalSign) {
        this.animalSign = animalSign;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getBirthTime() {
        return birthTime;
    }

    public void setBirthTime(String birthTime) {
        this.birthTime = birthTime;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getAccesstoken() {
        return accesstoken;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIconurl() {
        return iconurl;
    }

    public void setIconurl(String iconurl) {
        this.iconurl = iconurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
