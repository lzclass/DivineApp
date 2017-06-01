package com.liuzhao.divineapp.data.retrofit2service;

import com.liuzhao.divineapp.data.entity.RootEntity;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by chengsy on 2016/5/26.
 */
public interface ZhiHuService {

    //今日头条
    @GET("/api/4/news/latest")
    Observable<RootEntity> getLatestNews();

    //互联网安全
    @GET("/api/4/theme/10")
    Observable<RootEntity> getSafety();

    //不准无聊
    @GET("/api/4/theme/11")
    Observable<RootEntity> getInterest();

    //体育日报
    @GET("/api/4/theme/8")
    Observable<RootEntity> getSport();

}
