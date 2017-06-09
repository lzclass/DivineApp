package com.liuzhao.divineapp.ui.my;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.liuzhao.divineapp.R;
import com.liuzhao.divineapp.base.BaseActivity;
import com.liuzhao.divineapp.data.UserRepository;
import com.liuzhao.divineapp.data.entity.UserResult;
import com.liuzhao.divineapp.data.local.PreferencesManager;
import com.liuzhao.divineapp.utils.image.GlideImgManager;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

public class UserDetailActivity extends BaseActivity implements UserDetailContract.View {
    @BindView(R.id.iv_head)
    ImageView iv_head;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private UserDetailContract.Presenter mPresenter;

    @Override
    public void setPresenter(UserDetailContract.Presenter presenter) {
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
    public void refreshUi() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        ButterKnife.bind(this);
        UserRepository userRepository = UserRepository.getInstance(baseApplication);
        UserResult userResult = userRepository.getUserInfo(PreferencesManager.USER.getUserId());
        toolbar.setTitle(userResult.getName());
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.mipmap.icon_left_arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        GlideImgManager.glideLoaderCircle(this, userResult.getIconurl(), 0, 0, iv_head);
        mPresenter = new UserDetailPresenter(this,this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
