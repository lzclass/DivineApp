package com.liuzhao.divineapp.ui.setting;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.liuzhao.divineapp.R;
import com.liuzhao.divineapp.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by liuzhao on 2017/6/14.
 * 设置
 */

public class SettingActivity extends BaseActivity implements SettingContract.View {
    @BindView(R.id.tv_currentVersion)
    TextView tv_currentVersion;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private SettingContract.Presenter mPresenter;

    @Override
    public void setVersion(String version) {
        tv_currentVersion.setText(version);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        toolbar.setTitle("设置");
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.mipmap.icon_left_arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mPresenter = new SettingPresenter(SettingActivity.this,this);
    }

    @OnClick({R.id.tv_logout,R.id.tv_aboutUs})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_logout:
                mPresenter.loginOut();
                break;
            case R.id.tv_aboutUs:
                mPresenter.aboutUs();
                break;
        }
    }

    @Override
    public void setPresenter(SettingContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
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

}
