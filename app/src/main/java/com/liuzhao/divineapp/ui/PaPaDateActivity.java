package com.liuzhao.divineapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.liuzhao.divineapp.R;

import butterknife.BindView;
import butterknife.BindViews;

public class PaPaDateActivity extends AppCompatActivity {
    //    例子1： 中女与帅哥（有房） 女30岁外貌7分曾与2男有性行为 男外貌8分资产300万
//    那么交往多少天可以啪啪啪：[（40-30）^ 2 + 7 ^ 3]×10÷[（8 ^ 2 + 30）×（2 + 1）^ 2] = 5.24日
//    例子2： 屌丝与女神 女22岁外貌9分处女 男外貌3分资产0
//    那么交往多少天可以啪啪啪：[（40-22）^ 2 + 9 ^ 3]×10÷[（3 ^ 2 + 0）×（0 + 1）^ 2] = 1170日（不过熬得到去那天才算
    @BindViews({R.id.girlAge, R.id.girlPoint, R.id.boyPoint, R.id.bogMoney, R.id.bogNum})
    private EditText[] et_num;
    @BindView(R.id.papaDay)
    private TextView papaDay;
    @BindView(R.id.btn_jisuan)
    private Button btn_jisuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pa_pa_date);
    }

    int[] getIntNum() {
        int[] num = new int[et_num.length];
        for (int i = 0; i < et_num.length; i++) {
            num[i] = Integer.valueOf(et_num[i].getText().toString());
        }
        return num;
    }
}
