package com.liuzhao.divineapp.ui.constellation;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.liuzhao.divineapp.R;
import com.liuzhao.divineapp.base.BaseActivity;
import com.liuzhao.divineapp.data.entity.constellation.Constellation;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConstellationActivity extends BaseActivity implements ConstellationContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.grid_swipe_refresh)
    SwipeRefreshLayout mSwipeLayout;
    @BindView(R.id.grid_recycler)
    RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private ConstellationAdapter mAdapter;
    private ConstellationContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constellation);
        ButterKnife.bind(this);
        toolbar.setTitle("星座");
        setSupportActionBar(toolbar);
        // 设置下拉圆圈上的颜色
        mSwipeLayout.setColorSchemeResources(R.color.holo_blue_bright,
                R.color.holo_green_light, R.color.holo_orange_light,
                R.color.holo_red_light);

        mSwipeLayout.setDistanceToTriggerSync(400);// 设置手指在屏幕下拉多少距离会触发下拉刷新
        mSwipeLayout.setProgressBackgroundColorSchemeResource(R.color.white);// 设定下拉圆圈的背景
        mSwipeLayout.setSize(SwipeRefreshLayout.DEFAULT); // 设置圆圈的大小
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
        presenter = new ConstellationPresenter(this);
        presenter.initData();
    }

    @Override
    public void refreshRecyclerView(List<Constellation> mList) {
        mAdapter = new ConstellationAdapter(getApplicationContext(), mList);
        mAdapter.setOnItemClickListener(mOnItemClickListener);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(mOnScrollListener);
    }

    @Override
    public void setPresenter(ConstellationContract.Presenter presenter) {

    }

    private ConstellationAdapter.OnRecyclerViewItemClickListener mOnItemClickListener = new ConstellationAdapter.OnRecyclerViewItemClickListener() {
        @Override
        public void onItemClick(View view) {

        }

        @Override
        public void onItemLongClick(View view) {

        }
    };
    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        private int lastVisibleItem;

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            //0：当前屏幕停止滚动；1时：屏幕在滚动 且 用户仍在触碰或手指还在屏幕上；2时：随用户的操作，屏幕上产生的惯性滑动；
            // 滑动状态停止并且剩余少于两个item时，自动加载下一页
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItem + 2 >= mLayoutManager.getItemCount()) {
//                new GetData().execute("http://gank.io/api/data/福利/10/"+(++page));
            }
        }
    };

}
