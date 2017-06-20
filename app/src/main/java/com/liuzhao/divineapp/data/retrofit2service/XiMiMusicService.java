package com.liuzhao.divineapp.data.retrofit2service;

import com.liuzhao.divineapp.data.entity.RootEntity;

import retrofit2.http.GET;
import rx.Observable;

/**
 * 虾米音乐
 */

public interface XiMiMusicService {
    //每日一言
    @GET("/api/dayword/getTodayWord")
    Observable<RootEntity> getTodayWord();

}
