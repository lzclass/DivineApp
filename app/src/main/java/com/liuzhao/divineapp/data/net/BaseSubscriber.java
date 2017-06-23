package com.liuzhao.divineapp.data.net;

import android.content.Context;

import com.umeng.socialize.utils.Log;

import rx.Subscriber;

/**
 * BaseSubscriber
 * Created by Tamic on 2016-08-03.
 */
public abstract class BaseSubscriber<T> extends Subscriber<T> {

    private Context context;
    private boolean isNeedCahe;
    private String TAG = this.getClass().getSimpleName();

    public BaseSubscriber(Context context) {
        this.context = context;
    }

    @Override
    public void onError(Throwable e) {
        Log.e(TAG, e.getMessage());
        // todo error somthing

        if (e instanceof ExceptionHandle.ResponeThrowable) {
            onError((ExceptionHandle.ResponeThrowable) e);
        } else {
            onError(new ExceptionHandle.ResponeThrowable(e, ExceptionHandle.ERROR.UNKNOWN));
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "http is start");

        // todo some common as show loadding  and check netWork is NetworkAvailable
        // 如果没有网络必须关闭请求
        if (!NetworkUtil.isNetworkAvailable(context)) {
            Log.d(TAG, "无网络，读取缓存数据");
            onCompleted();
        }

    }

    @Override
    public void onCompleted() {
        Log.d(TAG, "http is Complete");
        // todo some common as  dismiss loadding
    }

    public abstract void onError(ExceptionHandle.ResponeThrowable e);

}
