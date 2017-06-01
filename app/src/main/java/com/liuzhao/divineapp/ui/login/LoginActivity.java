package com.liuzhao.divineapp.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.liuzhao.divineapp.R;
import com.liuzhao.divineapp.base.BaseActivity;
import com.liuzhao.divineapp.data.UserRepository;
import com.liuzhao.divineapp.data.local.UserLocalDataSource;
import com.liuzhao.divineapp.data.remote.UserRemoteDataSource;
import com.umeng.socialize.UMShareAPI;

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
        UserRepository userRepository = UserRepository.getInstance(UserLocalDataSource.getInstance(getApplicationContext()), UserRemoteDataSource.getInstance());
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

