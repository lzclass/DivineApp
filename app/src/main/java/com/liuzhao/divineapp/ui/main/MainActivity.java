package com.liuzhao.divineapp.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liuzhao.divineapp.R;
import com.liuzhao.divineapp.base.BaseActivity;
import com.liuzhao.divineapp.data.UserRepository;
import com.liuzhao.divineapp.data.entity.UserResult;
import com.liuzhao.divineapp.data.local.PreferencesManager;
import com.liuzhao.divineapp.ui.login.LoginActivity;
import com.liuzhao.divineapp.ui.my.UserDetailActivity;
import com.liuzhao.divineapp.utils.ShareUtils;
import com.liuzhao.divineapp.utils.image.GlideImgManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainContract.View {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    ImageView iv_head;
    TextView tv_name;
    LinearLayout ll_homepage;
    public static final int LOGIN_SUCCESS = 101;
    private MainContract.Presenter mPresenter;
    private String userId;

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
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
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);
        View headerView = mNavigationView.getHeaderView(0);
        iv_head = (ImageView) headerView.findViewById(R.id.iv_head);
        tv_name = (TextView) headerView.findViewById(R.id.tv_name);
        ll_homepage = (LinearLayout) headerView.findViewById(R.id.ll_homepage);

        ll_homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("".equals(userId)) {
                    startActivityForResult(new Intent(MainActivity.this, LoginActivity.class), 1);
                } else {
                    startActivity(new Intent(MainActivity.this, UserDetailActivity.class));
                }

            }
        });
        mPresenter = new MainPresenter(MainActivity.this, this);
        refreshUi();
    }

    @OnClick({R.id.fab})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:

                break;
        }
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_1) {

        } else if (id == R.id.nav_2) {

        } else if (id == R.id.nav_share) {
            ShareUtils.shareText(MainActivity.this);
        } else if (id == R.id.nav_send) {

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void refreshUi() {
        UserRepository userRepository = UserRepository.getInstance(baseApplication);
        userId = PreferencesManager.USER.getUserId();
        UserResult userResult = userRepository.getUserInfo(userId);
        if ("".equals(userId)) {
            tv_name.setText("未登录");
        } else {
            tv_name.setText(userResult.getName());
        }
        GlideImgManager.glideLoaderCircle(MainActivity.this, userResult.getIconurl(), R.mipmap.user_defaut_head, R.mipmap.user_defaut_head, iv_head);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == LOGIN_SUCCESS) {
            refreshUi();
        }
    }
}
