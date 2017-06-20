package com.liuzhao.divineapp.ui.bazitest;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.liuzhao.divineapp.R;
import com.liuzhao.divineapp.base.BaseActivity;
import com.liuzhao.divineapp.utils.bazi.Luozhuanglvhehun;
import com.liuzhao.divineapp.utils.bazi.LuozhuangshengSha;

import java.text.ParseException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaZiTestActivity extends BaseActivity {
    @BindView(R.id.tv_bazi)
    TextView tv_bazi;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ba_zi_test);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        LuozhuangshengSha luozhuangshengSha = new LuozhuangshengSha();
        String baziDetail = null;
        try {
            baziDetail = luozhuangshengSha.paipan("1991-02-02 02", Luozhuanglvhehun.sex.man);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tv_bazi.setText(baziDetail);
    }
}
