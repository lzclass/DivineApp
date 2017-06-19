package com.liuzhao.divineapp.ui.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.liuzhao.divineapp.R;
import com.liuzhao.divineapp.base.BaseViewHolder;
import com.liuzhao.divineapp.data.entity.main.MainMenu;

import java.util.List;

/**
 * Created by liuzhao on 2017/6/15.
 */

public class MainMenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {

    private Context mContext;
    private List<MainMenu> datas;//数据

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
    public MainMenuAdapter(Context context, List<MainMenu> datas) {
        mContext = context;
        this.datas = datas;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_item_main_menu, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        //给布局设置点击和长点击监听
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //将数据与item视图进行绑定，如果是MyViewHolder就加载网络图片，如果是MyViewHolder2就显示页数
//        if (holder instanceof MyViewHolder) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        MainMenu mainMenu = datas.get(position);
        myViewHolder.iv_icon.setBackgroundResource(mainMenu.getImageDrawable());
        myViewHolder.tv_name.setText(mainMenu.getName());
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
        private ImageView iv_icon;
        private TextView tv_name;


        public MyViewHolder(View view) {
            super(view);
            iv_icon = BaseViewHolder.get(view, R.id.iv_icon);
            tv_name = BaseViewHolder.get(view, R.id.tv_name);
        }
    }

    public void move(int fromPosition, int toPosition) {
        MainMenu mainMenu = datas.remove(fromPosition);
        datas.add(toPosition > fromPosition ? toPosition - 1 : toPosition, mainMenu);
        notifyItemMoved(fromPosition, toPosition);
    }

}
