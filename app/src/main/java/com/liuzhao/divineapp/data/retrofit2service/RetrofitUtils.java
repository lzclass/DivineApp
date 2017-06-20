package com.liuzhao.divineapp.data.retrofit2service;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by liuzhao on 2017/6/20.
 */

public class RetrofitUtils {
//  void  okHttp() {
//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//
//        okHttpClient = new OkHttpClient.Builder()
//                //打印日志
//                .addInterceptor(interceptor)
//
//                //设置Cache目录
//                .cache(CacheUtil.getCache(UIUtil.getContext()))
//
//                //设置缓存
//                .addInterceptor(cacheInterceptor)
//                .addNetworkInterceptor(cacheInterceptor)
//
//                //失败重连
//                .retryOnConnectionFailure(true)
//
//                //time out
//                .readTimeout(10, TimeUnit.SECONDS)
//                .connectTimeout(10, TimeUnit.SECONDS)
//                .build();
//    }
//    Retrofit Retrofit2Client() {
//        Retrofit retrofitBuilder = new Retrofit.Builder()
//                //设置OKHttpClient
//                .client(okHttp.INSTANCE.getOkHttpClient())
//                //Rx
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                //String转换器
//                .addConverterFactory(StringConverterFactory.create())
//                //gson转化器
//                .addConverterFactory(GsonConverterFactory.create());
//        return retrofitBuilder;
//    }
}
