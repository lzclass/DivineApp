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
import com.xiaomi.mipush.sdk.MiPushClient;

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
        List<MainMenu> list = new ArrayList<>();
        String[] MENU_NAME = {"八字测算", "投资清单", "八字合婚", "风生水起", "姓名测试", "配对测算", "笑话"};
        int[] MENU_DRAWABLE = {R.mipmap.icon_menu_bazi, R.mipmap.ic_touzi, R.drawable.ic_xingzuo_baiyang,
                R.drawable.ic_xingzuo_baiyang, R.drawable.ic_xingzuo_baiyang, R.drawable.ic_xingzuo_baiyang, R.drawable.ic_xingzuo_baiyang};
        for (int i = 0; i < MENU_NAME.length; i++) {
            MainMenu mainMenu = new MainMenu();
            mainMenu.setName(MENU_NAME[i]);
            mainMenu.setImageDrawable(MENU_DRAWABLE[i]);
            list.add(mainMenu);
        }
        initRecyclerView(list);
        //设置小米推送别名
        MiPushClient.setAlias(this, "test01", null);
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
    public void initRecyclerView(List<MainMenu> mList) {
        mRecyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(mItemTouchCallBack);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);

        mAdapter = new MainMenuAdapter(getApplicationContext(), mList);
        mAdapter.setOnItemClickListener(mOnItemClickListener);
        mRecyclerView.setAdapter(mAdapter);
    }

    private MainMenuAdapter.OnRecyclerViewItemClickListener mOnItemClickListener = new MainMenuAdapter.OnRecyclerViewItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            switch (position) {
                case 0:
                    startActivity(new Intent(MainActivity.this, BaZiTestActivity.class));
                    break;
                case 1:
                    startActivity(new Intent(MainActivity.this, BaZiTestActivity.class));
                    break;
                case 2:
                    startActivity(new Intent(MainActivity.this, BaZiTestActivity.class));
                    break;
                case 3:
                    startActivity(new Intent(MainActivity.this, BaZiTestActivity.class));
                    break;
                case 4:
                    startActivity(new Intent(MainActivity.this, BaZiTestActivity.class));
                    break;
                case 5:
                    startActivity(new Intent(MainActivity.this, BaZiTestActivity.class));
                    break;
                case 6:
                    startActivity(new Intent(MainActivity.this, JokeActivity.class));
                    break;
                case 7:
                    startActivity(new Intent(MainActivity.this, BaZiTestActivity.class));
                    break;

            }

        }

        @Override
        public void onItemLongClick(View view, int position) {

        }
    };

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

    private ItemTouchHelper.Callback mItemTouchCallBack = new ItemTouchHelper.Callback() {
        /**
         * 设置滑动类型标记
         *
         * @param recyclerView
         * @param viewHolder
         * @return
         *          返回一个整数类型的标识，用于判断Item那种移动行为是允许的
         */
        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return makeMovementFlags(ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT | ItemTouchHelper.DOWN | ItemTouchHelper.UP, 0);
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

        }

        /**
         * Item是否支持长按拖动
         *
         * @return
         *          true  支持长按操作
         *          false 不支持长按操作
         */
        @Override
        public boolean isLongPressDragEnabled() {
            return true;
        }

        /**
         * Item是否支持滑动
         *
         * @return
         *          true  支持滑动操作
         *          false 不支持滑动操作
         */
        @Override
        public boolean isItemViewSwipeEnabled() {
            return false;
        }

        /**
         * 拖拽切换Item的回调
         *
         * @param recyclerView
         * @param viewHolder
         * @param target
         * @return
         *          如果Item切换了位置，返回true；反之，返回false
         */
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            mAdapter.move(viewHolder.getAdapterPosition(), target.getAdapterPosition());
            return true;
        }

        /**
         * Item被选中时候回调
         *
         * @param viewHolder
         * @param actionState
         *          当前Item的状态
         *          ItemTouchHelper.ACTION_STATE_IDLE   闲置状态
         *          ItemTouchHelper.ACTION_STATE_SWIPE  滑动中状态
         *          ItemTouchHelper#ACTION_STATE_DRAG   拖拽中状态
         */
        @Override
        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
            //  item被选中的操作
            if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                viewHolder.itemView.setBackgroundResource(R.color.holo_gray_bright);
            }
            super.onSelectedChanged(viewHolder, actionState);
        }

        /**
         * 用户操作完毕或者动画完毕后会被调用
         *
         * @param recyclerView
         * @param viewHolder
         */
        @Override
        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            // 操作完毕后恢复颜色
            viewHolder.itemView.setBackgroundResource(R.color.white);
            super.clearView(recyclerView, viewHolder);
        }
    };
}
