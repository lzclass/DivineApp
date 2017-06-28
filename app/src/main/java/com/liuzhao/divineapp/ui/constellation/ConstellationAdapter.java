package com.liuzhao.divineapp.ui.constellation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.liuzhao.divineapp.R;
import com.liuzhao.divineapp.base.BaseViewHolder;
import com.liuzhao.divineapp.data.entity.constellation.Constellation;

import java.util.List;

/**
 * Created by liuzhao on 2017/6/15.
 */

public class ConstellationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {

    private Context mContext;
    private List<Constellation> datas;//数据

    //自定义监听事件
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view);

        void onItemLongClick(View view);
    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    //适配器初始化
    public ConstellationAdapter(Context context, List<Constellation> datas) {
        mContext = context;
        this.datas = datas;
    }

//    @Override
//    public int getItemViewType(int position) {
//        //判断item类别，是图还是显示页数（图片有URL）
//        if (!TextUtils.isEmpty(datas.get(position).getName())) {
//            return 0;
//        } else {
//            return 1;
//        }
//    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //根据item类别加载不同ViewHolder
//        if (viewType == 0) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_item_constellation_list, parent,
                    false);
            MyViewHolder holder = new MyViewHolder(view);
            //给布局设置点击和长点击监听
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);

            return holder;
//        }
//        return null;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //将数据与item视图进行绑定，如果是MyViewHolder就加载网络图片，如果是MyViewHolder2就显示页数
//        if (holder instanceof MyViewHolder) {
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            Constellation constellation = datas.get(position);
            myViewHolder.iv_constellation_logo.setBackgroundResource(constellation.getImageDrawble());
            myViewHolder.tv_date.setText(constellation.getDate());
            myViewHolder.tv_name.setText(constellation.getName());
            myViewHolder.tv_property.setText(constellation.getProperty());
//        }

    }

    @Override
    public int getItemCount() {
        return datas.size();//获取数据的个数
    }

    //点击事件回调
    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemLongClick(v);
        }
        return false;
    }

    //自定义ViewHolder，用于加载图片
    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_constellation_logo;
        private TextView tv_name;
        private TextView tv_date;
        private TextView tv_property;

        public MyViewHolder(View view) {
            super(view);
            iv_constellation_logo = (ImageView)view.findViewById(R.id.iv_constellation_logo);
            tv_name = (TextView)view.findViewById(R.id.tv_name);
            tv_date = (TextView)view.findViewById(R.id.tv_date);
            tv_property = (TextView)view.findViewById(R.id.tv_property);
        }
    }


}
