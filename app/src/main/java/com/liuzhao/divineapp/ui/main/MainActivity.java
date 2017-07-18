package com.liuzhao.divineapp.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liuzhao.divineapp.R;
import com.liuzhao.divineapp.base.BaseActivity;
import com.liuzhao.divineapp.data.UserRepository;
import com.liuzhao.divineapp.data.entity.UserResult;
import com.liuzhao.divineapp.data.entity.main.MainMenu;
import com.liuzhao.divineapp.data.local.PreferencesManager;
import com.liuzhao.divineapp.ui.bazitest.BaZiTestActivity;
import com.liuzhao.divineapp.ui.constellation.ConstellationActivity;
import com.liuzhao.divineapp.ui.joke.JokeActivity;
import com.liuzhao.divineapp.ui.login.LoginActivity;
import com.liuzhao.divineapp.ui.my.UserDetailActivity;
import com.liuzhao.divineapp.ui.setting.SettingActivity;
import com.liuzhao.divineapp.utils.ShareUtils;
import com.liuzhao.divineapp.utils.image.GlideImgManager;

import net.youmi.android.os.OffersManager;
import net.youmi.android.os.PointsManager;

import java.util.ArrayList;
import java.util.List;

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
    @BindView(R.id.grid_recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_money_today)
    TextView tv_money_today;
    @BindView(R.id.tv_money_total)
    TextView tv_money_total;
    public static final int LOGIN_SUCCESS = 101;
    public static final int LOGOUT_SUCCESS = 102;
    private MainContract.Presenter mPresenter;
    private String userId;
    private MainMenuAdapter mAdapter;


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
    public void initMainMenu() {
        String name[] = {"赚红包","兑换"};
        int image[] = {R.drawable.ic_menu_camera,R.drawable.ic_menu_gallery};
        List<MainMenu> datas = new ArrayList<>();
        for (int i = 0; i < name.length; i++) {
            MainMenu mainMenu = new MainMenu();
            mainMenu.setName(name[i]);
            mainMenu.setImageDrawable(image[i]);
            datas.add(mainMenu);
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new MainMenuAdapter(MainActivity.this, datas);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new MainMenuAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position){
                    case 0:
                         OffersManager.getInstance(MainActivity.this).showOffersWall();
                        break;
                    case 1:
                        break;
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    @Override
    public void setYouMinPoint(String pointNum) {
        tv_money_total.setText(pointNum);
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
                    startActivityForResult(new Intent(MainActivity.this, LoginActivity.class), LOGIN_SUCCESS);
                } else {
                    startActivity(new Intent(MainActivity.this, UserDetailActivity.class));
                }

            }
        });
        mPresenter = new MainPresenter(MainActivity.this, this);
        refreshUi();
        mPresenter.initYouMi();
        initMainMenu();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unRegisterYouMiNotify();
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
        switch (item.getItemId()) {
            case R.id.nav_1:
            case R.id.nav_2:
                break;
            case R.id.nav_share:
                ShareUtils.shareText(MainActivity.this);
                break;
            case R.id.nav_send:
                break;
            case R.id.nav_constellation:
                startActivity(new Intent(MainActivity.this, ConstellationActivity.class));
                break;
            case R.id.nav_setting:
                startActivityForResult(new Intent(MainActivity.this, SettingActivity.class), LOGOUT_SUCCESS);
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void refreshUi() {
        UserRepository userRepository = UserRepository.getInstance(baseApplication);
        userId = PreferencesManager.USER.getUserId();
        String iconUrl = null;
        if ("".equals(userId)) {
            tv_name.setText("未登录");
        } else {
            UserResult userResult = userRepository.getUserInfo(userId);
            iconUrl = userResult.getIconurl();
            tv_name.setText(userResult.getNickName());
        }
        GlideImgManager.glideLoaderCircle(MainActivity.this, iconUrl, R.mipmap.user_defaut_head, R.mipmap.user_defaut_head, iv_head);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOGIN_SUCCESS && resultCode == RESULT_OK) {
            refreshUi();
        }
        if (requestCode == LOGOUT_SUCCESS && resultCode == RESULT_OK) {
            refreshUi();
        }
    }

}
