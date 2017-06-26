package com.liuzhao.divineapp.ui.joke;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.liuzhao.divineapp.R;
import com.liuzhao.divineapp.base.BaseActivity;
import com.liuzhao.divineapp.ui.joke.dummy.DummyContent;
import com.liuzhao.divineapp.utils.ActivityUtils;

public class JokeActivity extends BaseActivity implements JokeFragment.OnListFragmentInteractionListener {
    private JokeContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        JokeFragment fragment = (JokeFragment) getSupportFragmentManager().findFragmentById(R.id.fl_joke);

        if (fragment == null) {
            fragment = JokeFragment.newInstance(2);
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    fragment, R.id.fl_joke);
        }
        // Create the presenter
        presenter = new JokePresenter(fragment, JokeActivity.this);
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
