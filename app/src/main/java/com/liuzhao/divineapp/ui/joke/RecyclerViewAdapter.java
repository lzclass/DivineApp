package com.liuzhao.divineapp.ui.joke;

import android.content.Context;

import com.liuzhao.divineapp.R;
import com.liuzhao.divineapp.base.BaseViewHolder;
import com.liuzhao.divineapp.data.entity.main.Joke;
import com.liuzhao.divineapp.widget.BaseRecycleAdapter;

import java.util.List;

/**
 * Created by liuzhao on 2017/6/28.
 */

public class RecyclerViewAdapter extends BaseRecycleAdapter<Joke> {
    public RecyclerViewAdapter(Context context, int layoutId, List<Joke> data) {
        super(context, layoutId, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, Joke bean) {
        holder.setText(R.id.content,bean.getContent());
    }

}