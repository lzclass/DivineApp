package com.liuzhao.divineapp.ui.joke;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.liuzhao.divineapp.R;
import com.liuzhao.divineapp.base.BaseActivity;
import com.liuzhao.divineapp.data.entity.main.Joke;
import com.liuzhao.divineapp.ui.joke.dummy.DummyContent;
import com.liuzhao.divineapp.utils.ActivityUtils;

import java.util.HashSet;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JokeActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        ButterKnife.bind(this);
        JokeFragment fragment = (JokeFragment) getSupportFragmentManager().findFragmentById(R.id.fl_joke);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (fragment == null) {
            fragment = JokeFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    fragment, R.id.fl_joke);
        }
    }

}
