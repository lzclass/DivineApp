package com.liuzhao.divineapp.ui.my;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.liuzhao.divineapp.R;
import com.liuzhao.divineapp.base.BaseActivity;
import com.liuzhao.divineapp.data.UserRepository;
import com.liuzhao.divineapp.data.entity.UserResult;
import com.liuzhao.divineapp.data.local.PreferencesManager;
import com.liuzhao.divineapp.utils.CalendarUtils;
import com.liuzhao.divineapp.utils.StringUtils;
import com.liuzhao.divineapp.utils.image.GlideImgManager;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.common.base.Preconditions.checkNotNull;

public class UserDetailActivity extends BaseActivity implements UserDetailContract.View {
    @BindView(R.id.iv_head)
    ImageView iv_head;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindViews({R.id.tv_birthday, R.id.tv_dayNongli, R.id.tv_birthTime, R.id.tv_timeNongli})
    TextView[] tv_BirthViews;
    @BindViews({R.id.tv_realName, R.id.tv_sex, R.id.tv_shuxiang, R.id.tv_xingzuo, R.id.tv_nickName})
    TextView[] tv_Views;
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
        UserRepository userRepository = UserRepository.getInstance(baseApplication);
        UserResult userResult = userRepository.getUserInfo(PreferencesManager.USER.getUserId());
        toolbar.setTitle(userResult.getNickName());
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.mipmap.icon_left_arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        GlideImgManager.glideLoaderCircle(this, userResult.getIconurl(), 0, 0, iv_head);
        mPresenter = new UserDetailPresenter(this, this);
        tv_Views[1].setText(userResult.getGender());
        tv_Views[0].setText(userResult.getName());
        tv_Views[4].setText(userResult.getNickName());
        if (!StringUtils.isEmpty(userResult.getBirthDay())) {
            tv_BirthViews[0].setText(userResult.getBirthDay());
            String dateString[] = userResult.getBirthDayNongli().split("-");
            String nongliDate = CalendarUtils.getSolarToLunar(Integer.valueOf(dateString[0]), Integer.valueOf(dateString[1]), Integer.valueOf(dateString[2]));
            tv_BirthViews[1].setText(nongliDate);
            tv_Views[2].setText(userResult.getAnimalSign());
            tv_Views[3].setText(userResult.getConstellation());
        }
        if (!StringUtils.isEmpty(userResult.getBirthTime())) {
            tv_BirthViews[2].setText(userResult.getBirthTime());
            tv_BirthViews[3].setText(userResult.getBirthTimeNongli());
        }
    }

    @OnClick({R.id.ll_realName, R.id.ll_sex, R.id.ll_birthday, R.id.ll_birthTime})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_realName:
                mPresenter.editRealName(tv_Views[0]);
                break;
            case R.id.ll_sex:
                mPresenter.editSex(tv_Views[1]);
                break;
            case R.id.ll_birthday:
                mPresenter.editBirthday(tv_BirthViews[0], tv_BirthViews[1], tv_Views[2], tv_Views[3]);
                break;
            case R.id.ll_birthTime:
                mPresenter.editBirthTime(tv_BirthViews[2], tv_BirthViews[3]);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        ButterKnife.bind(this);
        refreshUi();
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
