package com.liuzhao.divineapp.data.net;

import com.liuzhao.divineapp.data.entity.LoginResult;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by LIUYONGKUI726 on 2016-07-25.
 */
public interface MyApiService {

    @GET("http://japi.juhe.cn/joke/content/list.from?key=0c2775b5d1c7ecd8430e49449ea4ec43")
    Observable<LoginResult> getJoke(@Query("sort") String sort,
                                  @Query("time") String time,
                                  @Query("page") int page,
                                  @Query("pagesize") int pagesize);
}
