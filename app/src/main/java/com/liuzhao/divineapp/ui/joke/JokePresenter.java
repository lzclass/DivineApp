package com.liuzhao.divineapp.ui.joke;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.liuzhao.divineapp.data.entity.JokeResult;
import com.liuzhao.divineapp.data.net.BaseApiService;
import com.liuzhao.divineapp.data.net.BaseResponse;
import com.liuzhao.divineapp.data.net.BaseSubscriber;
import com.liuzhao.divineapp.data.net.ExceptionHandle;
import com.liuzhao.divineapp.data.net.JokeApiService;
import com.liuzhao.divineapp.data.net.RetrofitClient;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by liuzhao on 2017/6/26.
 */

public class JokePresenter implements JokeContract.Presenter {
    private JokeActivity mActivity;
    @NonNull
    private final JokeContract.View mAddTaskView;

    public JokePresenter(JokeContract.View view, JokeActivity mActivity) {
        mAddTaskView = checkNotNull(view);
        this.mActivity = mActivity;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    public void getData() {
        JokeApiService service = RetrofitClient.getInstance(mActivity).create(JokeApiService.class);
        Map<String, Object> maps = new HashMap<>();
        maps.put("sort", "asc");
        maps.put("time", "1418816972");
        maps.put("page", 1);
        maps.put("pagesize", 20);
        maps.put("key", "0c2775b5d1c7ecd8430e49449ea4ec43");

        RetrofitClient.getInstance(mActivity, BaseApiService.JUHE_URL).execute(
                service.getJoke(maps), new BaseSubscriber<ResponseBody>(mActivity) {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        String jstr = null;
                        try {
                            jstr = new String(responseBody.bytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Type type = new TypeToken<BaseResponse<JokeResult>>() {
                        }.getType();

                        BaseResponse<JokeResult> baseResponse = new Gson().fromJson(jstr, type);
                        JokeResult list = baseResponse.getResult();
                        mAddTaskView.initRecyclerView(list.getData());
                    }
                });
    }
}
