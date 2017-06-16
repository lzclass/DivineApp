package com.liuzhao.divineapp.ui.constellation;

import com.liuzhao.divineapp.R;
import com.liuzhao.divineapp.data.entity.constellation.Constellation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzhao on 2017/6/15.
 */

public class ConstellationPresenter implements ConstellationContract.Presenter {
    private ConstellationContract.View constellationView;
    private String[] NAME = {"白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天枰座", "天蝎座", "射手座", "摩羯座", "水瓶座", "双鱼座"};
    private String[] DATE = {"3.21-4.19", "4.20-5.20", "5.21-6.21", "6.22-7.22", "7.23-8.22", "8.23-9.22", "9.23-10.23", "10.24-11.22", "11.23-12.21", "12.22-1.19", "1.20-2.18", "2.19-3.20"};
    private String[] PROPERTY = {"火", "土", "风", "水"};
    private int[] IMAGE_DRAWABLE = {R.drawable.ic_xingzuo_baiyang, R.drawable.ic_xingzuo_jinniu, R.drawable.ic_xingzuo_shuangzi,
            R.drawable.ic_xingzuo_juxie, R.drawable.ic_xingzuo_shizi, R.drawable.ic_xingzuo_chunv,
            R.drawable.ic_xingzuo_tianping, R.drawable.ic_xingzuo_tianxie, R.drawable.ic_xingzuo_sheshou, R.drawable.ic_xingzuo_mojie,
            R.drawable.ic_xingzuo_shuiping, R.drawable.ic_xingzuo_shuangyu};

    public ConstellationPresenter(ConstellationContract.View constellationView) {
        this.constellationView = constellationView;
    }

    @Override
    public void initData() {
        List<Constellation> mList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            Constellation constellation = new Constellation();
            constellation.setName(NAME[i]);
            constellation.setImageDrawble(IMAGE_DRAWABLE[i]);
            constellation.setDate(DATE[i]);
            constellation.setProperty(PROPERTY[i % 3]);
            mList.add(constellation);
        }
        constellationView.refreshRecyclerView(mList);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
}
