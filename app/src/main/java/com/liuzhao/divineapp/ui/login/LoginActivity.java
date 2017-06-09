package com.liuzhao.divineapp.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.liuzhao.divineapp.R;
import com.liuzhao.divineapp.base.BaseActivity;
import com.liuzhao.divineapp.base.BaseApplication;
import com.liuzhao.divineapp.data.UserRepository;
import com.umeng.socialize.UMShareAPI;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * 登录
 */
public class LoginActivity extends BaseActivity implements LoginContract.View {
    @BindViews({R.id.iv_qq, R.id.iv_weixin})
    ImageView[] loginViews;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private LoginContract.Presenter mPresenter;

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        UserRepository userRepository = UserRepository.getInstance(BaseApplication.getSelf());
        mPresenter = new LoginPresenter(this, userRepository, this);
    }

    @OnClick({R.id.iv_qq, R.id.iv_weixin})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_qq:
                mPresenter.qqLogin();
                break;
            case R.id.iv_weixin:
                mPresenter.weixinLogin();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}

