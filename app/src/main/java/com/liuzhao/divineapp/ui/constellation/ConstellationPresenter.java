package com.liuzhao.divineapp.ui.constellation;

import com.liuzhao.divineapp.data.entity.constellation.Constellation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzhao on 2017/6/15.
 */

public class ConstellationPresenter implements ConstellationContract.Presenter {
    private ConstellationContract.View constellationView;

    public ConstellationPresenter(ConstellationContract.View constellationView) {
        this.constellationView = constellationView;
    }

    @Override
    public void initData() {
        List<Constellation> mList = new ArrayList<>();
        Constellation constellation = new Constellation();
        constellation.setName("白羊座");
        constellation.setDate("3.21-4.19");
        constellation.setProperty("火");
        mList.add(constellation);
        constellation.setName("金牛座");
        constellation.setDate("4.20-5.20");
        constellation.setProperty("土");
        mList.add(constellation);
        constellation.setName("双子座");
        constellation.setDate("5.21-6.21");
        constellation.setProperty("风");
        mList.add(constellation);
        constellation.setName("巨蟹座");
        constellation.setDate("6.22-7.22");
        constellation.setProperty("水");
        mList.add(constellation);
        constellation.setName("狮子座");
        constellation.setDate("7.23-8.22");
        constellation.setProperty("火");
        mList.add(constellation);
        constellationView.refreshRecyclerView(mList);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
}
