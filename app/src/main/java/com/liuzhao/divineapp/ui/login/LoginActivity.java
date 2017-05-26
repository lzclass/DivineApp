package com.liuzhao.divineapp.ui.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.liuzhao.divineapp.R;
import com.liuzhao.divineapp.bean.UserResult;
import com.liuzhao.divineapp.view.LoginView;

/**
 * 登录
 */
public class LoginActivity extends AppCompatActivity implements LoginView {
    @BindViews({R.id.iv_qq, R.id.iv_weixin, R.id.iv_weibo})
    ImageView[] loginViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_qq, R.id.iv_weixin, R.id.iv_weibo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_qq:
                Toast.makeText(LoginActivity.this, "QQ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_weixin:
                Toast.makeText(LoginActivity.this, "微信", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_weibo:
                Toast.makeText(LoginActivity.this, "微博", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void clearEditText() {

    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showSuccessMsg(UserResult user) {

    }

    @Override
    public void showFailedMsg(String s) {

    }
}

