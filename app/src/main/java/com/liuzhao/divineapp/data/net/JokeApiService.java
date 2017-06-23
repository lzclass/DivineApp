package com.liuzhao.divineapp.data.net;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by LIUYONGKUI726 on 2016-07-25.
 */
public interface JokeApiService {

    @GET("joke/content/list.from")
    Observable<ResponseBody> getJoke(@QueryMap Map<String, Object> maps);

}
