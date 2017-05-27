package com.liuzhao.divineapp.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.liuzhao.divineapp.R;
import com.liuzhao.divineapp.base.BaseActivity;
import com.liuzhao.divineapp.base.BaseApplication;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

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
    private UMShareAPI umShareAPI;

    @Override
    public void setPresenter(@NonNull LoginContract.Presenter presenter) {
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
    public void setupActivityComponent() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

    }

    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //授权开始的回调
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText(getApplicationContext(), "Authorize succeed", Toast.LENGTH_SHORT).show();
//            name：name（6.2以前用screen_name）
//
//            用户id：uid
//
//            accesstoken: accessToken （6.2以前用access_token）
//
//            过期时间：expiration （6.2以前用expires_in）
//
//            性别：gender
//
//            头像：iconurl（6.2以前用profile_image_url）
//
//            是否黄钻：is_yellow_year_vip
//
//            黄钻等级：yellow_vip_level
//
//            城市：city
//
//            省份：province
//
//            QQ目前uid暂时无法实现多个应用间打通操作，但QQ预留了unionID字段，但获取此字段需要单独和腾讯申请权限，邮箱connect@qq.com

        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(getApplicationContext(), "Authorize fail", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };

    @OnClick({R.id.iv_qq, R.id.iv_weixin})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_qq:
                qqLogin();
                break;
            case R.id.iv_weixin:
                break;
        }
    }

    @Override
    public void qqLogin() {
        umShareAPI.getPlatformInfo(LoginActivity.this, SHARE_MEDIA.QQ, umAuthListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }
}

