package com.liuzhao.divineapp.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast;

import com.umeng.socialize.utils.Log;

public abstract class BaseActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    protected BaseApplication baseApplication= BaseApplication.getSelf();
    protected static final String TAG = BaseActivity.class.getSimpleName();

    protected Context mContext;

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("onResume()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("onDestroy()");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        super.onCreate(savedInstanceState);
        mContext = this;
        Log.d("onCreate()");
    }


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
