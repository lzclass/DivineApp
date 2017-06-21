package com.liuzhao.divineapp.data.net;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by LIUYONGKUI726 on 2016-07-25.
 */
public interface MyApiService {

    @GET("app.php?m=souguapp&c=appusers&a=network")
    Observable<SouguBean> getSougu();
}
