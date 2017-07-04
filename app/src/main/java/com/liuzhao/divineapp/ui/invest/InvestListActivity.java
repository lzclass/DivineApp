package com.liuzhao.divineapp.ui.invest;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.liuzhao.divineapp.R;
import com.liuzhao.divineapp.base.BaseActivity;

/**
 * Created by liuzhao on 2017/7/4.
 * 投资列表
 */

public class InvestListActivity extends BaseActivity {
    String money1;
    String lilv1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invest_list);
    }
}
