package com.liuzhao.divineapp.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public abstract class BaseActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
//    public T presenter;

    @Override
    protected void onResume() {
        super.onResume();
//        presenter.attach((V) this);
    }

    @Override
    protected void onDestroy() {
//        presenter.dettach();
        super.onDestroy();
    }

    // 实例化presenter
//    public abstract T initPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActivityComponent();
//        presenter = initPresenter();
    }


    public abstract void setupActivityComponent();


    protected void showProgressDialog(String message) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(message);
        progressDialog.show();
    }

    protected void dissmissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    protected void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
