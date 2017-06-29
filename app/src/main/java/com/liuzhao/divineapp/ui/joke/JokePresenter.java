package com.liuzhao.divineapp.ui.joke;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.liuzhao.divineapp.data.entity.JokeResult;
import com.liuzhao.divineapp.data.net.BaseApiService;
import com.liuzhao.divineapp.data.net.BaseResponse;
import com.liuzhao.divineapp.data.net.BaseSubscriber;
import com.liuzhao.divineapp.data.net.ExceptionHandle;
import com.liuzhao.divineapp.data.net.JokeApiService;
import com.liuzhao.divineapp.data.net.RetrofitClient;
import com.umeng.socialize.utils.Log;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by liuzhao on 2017/6/26.
 */

public class JokePresenter implements JokeContract.Presenter {
    private Context mContext;
    @NonNull
    private final JokeContract.View mAddTaskView;

    public JokePresenter(JokeContract.View view, Context mContext) {
        mAddTaskView = checkNotNull(view);
        this.mContext = mContext;
        mAddTaskView.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    public void getData(int page) {
        JokeApiService service = RetrofitClient.getInstance(mContext).create(JokeApiService.class);
        Map<String, Object> maps = new HashMap<>();
        maps.put("sort", "asc");
        maps.put("time", "1418816972");
        maps.put("page", page);
        maps.put("pagesize", 20);
        maps.put("key", "0c2775b5d1c7ecd8430e49449ea4ec43");

        RetrofitClient.getInstance(mContext, BaseApiService.JUHE_URL).execute(
                service.getJoke(maps), new BaseSubscriber<ResponseBody>(mContext) {
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
                        Log.d(jstr);
                        JsonReader jsonReader = new JsonReader(new StringReader(jstr));//其中jsonContext为String类型的Json数据
                        jsonReader.setLenient(true);
                        BaseResponse<JokeResult> baseResponse = new Gson().fromJson(jsonReader, type);
                        JokeResult list = baseResponse.getResult();
                        mAddTaskView.refreshRecyclerView(list.getData());
                    }
                });
    }
}
