package com.liuzhao.divineapp.ui.joke;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liuzhao.divineapp.R;
import com.liuzhao.divineapp.data.entity.main.Joke;
import com.liuzhao.divineapp.widget.BaseRecycleAdapter;
import com.liuzhao.divineapp.widget.recyclerview.SimpleFooterView;
import com.liuzhao.divineapp.widget.recyclerview.SwipeRecyclerView;
import com.umeng.socialize.utils.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 */
public class JokeFragment extends Fragment implements JokeContract.View {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private JokeContract.Presenter mPresenter;
    @BindView(R.id.swipeRecyclerView)
    SwipeRecyclerView mSwipeRecyclerView;
    private int page = 1;
    private List<Joke> list;
    private RecyclerViewAdapter recyclerViewAdapter;

    public JokeFragment() {
    }

    @Override
    public void initRecyclerView() {

        //set layoutManager
        mSwipeRecyclerView.getRecyclerView().setLayoutManager(new LinearLayoutManager(getActivity()));
        mSwipeRecyclerView.setFooterView(new SimpleFooterView(getActivity()));
        mSwipeRecyclerView.setOnLoadListener(new SwipeRecyclerView.OnLoadListener() {
            @Override
            public void onRefresh() {
                Log.d("onRefresh()");
                mSwipeRecyclerView.complete();
                page = 1;
                mPresenter.getData(page);
            }

            @Override
            public void onLoadMore() {
                Log.d("onLoadMore()");
                page++;
                mPresenter.getData(page);
                mSwipeRecyclerView.onNoMore("我也是有底线的");
                mSwipeRecyclerView.stopLoadingMore();

            }
        });
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.adapter_item_main_menu, null);
        mSwipeRecyclerView.setEmptyView(view);
        list = new ArrayList<>();
        recyclerViewAdapter = new RecyclerViewAdapter(getActivity(), R.layout.fragment_item, list);
        mSwipeRecyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.setOnItemClickListner(new BaseRecycleAdapter.OnItemClickListner() {
            @Override
            public void onItemClickListner(View v, int position) {

            }
        });
        recyclerViewAdapter.setOnItemLongClickListner(new BaseRecycleAdapter.OnItemLongClickListner() {
            @Override
            public void onItemLongClickListner(View v, int position) {

            }
        });
    }

    @Override
    public void refreshRecyclerView(List<Joke> mList) {
        if (list == null) {
            list = new ArrayList<>();
        }
        if (page == 1) {
            list = mList;
            recyclerViewAdapter.refresh(mList);
        } else {
            list.addAll(mList);
            recyclerViewAdapter.addData(mList);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unsubscribe();
    }

    @Override
    public void setPresenter(JokeContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    public static JokeFragment newInstance() {
        JokeFragment fragment = new JokeFragment();
        Bundle args = new Bundle();
//        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        ButterKnife.bind(this, view);
        mPresenter = new JokePresenter(this, getActivity());
        initRecyclerView();
        mPresenter.getData(page);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
