package com.liuzhao.divineapp.ui.main;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import com.bumptech.glide.util.Util;
import com.liuzhao.divineapp.R;
import com.liuzhao.divineapp.data.UserRepository;

import net.youmi.android.AdManager;
import net.youmi.android.os.EarnPointsOrderInfo;
import net.youmi.android.os.EarnPointsOrderList;
import net.youmi.android.os.OffersBrowserConfig;
import net.youmi.android.os.OffersManager;
import net.youmi.android.os.PointsChangeNotify;
import net.youmi.android.os.PointsEarnNotify;
import net.youmi.android.os.PointsManager;

import butterknife.internal.Utils;

/**
 * Created by liuzhao on 2017/6/7.
 */

public class MainPresenter implements MainContract.Presenter, PointsChangeNotify, PointsEarnNotify {
    @NonNull
    private MainContract.View mainView;
    private MainActivity mContext;

    public MainPresenter(MainActivity mContext, @NonNull MainContract.View mainView) {
        this.mainView = mainView;
        this.mContext = mContext;
        mainView.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void onPointBalanceChange(float v) {
        float pointsBalance = PointsManager.getInstance(mContext).queryPoints();
        String money = pointsBalance / 10 + "";
        mainView.setYouMinPoint("余额：" + money);
        com.liuzhao.divineapp.utils.Utils.toast(money);
    }

    @Override
    public void onPointEarn(Context context, EarnPointsOrderList earnPointsOrderList) {
        if (earnPointsOrderList == null || earnPointsOrderList.isEmpty()) {
            return;
        }
        // 遍历订单并且toast提示
        for (int i = 0; i < earnPointsOrderList.size(); ++i) {
            EarnPointsOrderInfo info = earnPointsOrderList.get(i);
            Toast.makeText(context, info.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void unRegisterYouMiNotify() {
        // 回收积分广告占用的资源
        OffersManager.getInstance(mContext).onAppExit();
        // 注销积分监听
        PointsManager.getInstance(mContext).unRegisterNotify(this);
        // 注销积分订单赚取监听
        PointsManager.getInstance(mContext).unRegisterPointsEarnNotify(this);
    }

    @Override
    public void initYouMi() {
        // 初始化接口，应用启动的时候调用，参数：appId, appSecret, isEnableYoumiLog
        AdManager.getInstance(mContext).init("d82ca94c89089938", "c6d2f8fbc1620d7c", true);

        // 有米android 积分墙sdk 5.0.0之后支持定制浏览器顶部标题栏的部分UI
        setOfferBrowserConfig();

        // 如果使用积分广告，请务必调用积分广告的初始化接口:
        OffersManager.getInstance(mContext).onAppLaunch();
        // (可选)注册积分监听-随时随地获得积分的变动情况
        PointsManager.getInstance(mContext).registerNotify(this);
        // (可选)注册积分订单赚取监听（sdk v4.10版本新增功能）
        PointsManager.getInstance(mContext).registerPointsEarnNotify(this);

        // 查询积分余额
        // 从5.3.0版本起，客户端积分托管将由 int 转换为 float
        float pointsBalance = PointsManager.getInstance(mContext).queryPoints();
        String money = pointsBalance / 10 + "";
        mainView.setYouMinPoint("总金额 " + money);
    }

    @Override
    public void setOfferBrowserConfig() {
            // 设置标题栏——标题
            OffersBrowserConfig.getInstance(mContext).setBrowserTitleText("秒取积分");
            // 设置标题栏——背景颜色（ps：积分墙标题栏默认背景颜色为#FFBB34）
            OffersBrowserConfig.getInstance(mContext).setBrowserTitleBackgroundColor(Color.BLUE);
            // 设置标题栏——是否显示积分墙右上角积分余额区域 true：是 false：否
            OffersBrowserConfig.getInstance(mContext).setPointsLayoutVisibility(true);
            // 设置标题栏——是否显示有米的logo
            OffersBrowserConfig.getInstance(mContext).setLogoVisibility(false);
    }
    /**
     * 格式化字符串
     */
    private void addTextToSb(StringBuilder sb, String format, Object... args) {
        sb.append(String.format(format, args));
        sb.append(System.getProperty("line.separator"));
    }

    /**
     * 检查广告配置
     */
    @Override
    public void checkConfig() {
        StringBuilder sb = new StringBuilder();

        addTextToSb(sb,
                OffersManager.getInstance(mContext).checkOffersAdConfig() ? "广告配置结果：正常" :
                        "广告配置结果：异常，具体异常请查看Log，Log标签：YoumiSdk"
        );
        addTextToSb(sb, "%s服务器回调", OffersManager.getInstance(mContext).isUsingServerCallBack() ? "已经开启" : "没有开启");
        addTextToSb(sb,
                "%s通知栏下载相关的通知",
                AdManager.getInstance(mContext).isDownloadTipsDisplayOnNotification() ? "已经开启" : "没有开启"
        );
        addTextToSb(sb,
                "%s通知栏安装成功的通知",
                AdManager.getInstance(mContext).isInstallationSuccessTipsDisplayOnNotification() ? "已经开启" : "没有开启"
        );
        addTextToSb(sb,
                "%s通知栏赚取积分的提示",
                PointsManager.getInstance(mContext).isEnableEarnPointsNotification() ? "已经开启" : "没有开启"
        );
        addTextToSb(sb,
                "%s积分赚取的Toast提示",
                PointsManager.getInstance(mContext).isEnableEarnPointsToastTips() ? "已经开启" : "没有开启"
        );

        new AlertDialog.Builder(mContext).setTitle("检查结果")
                .setMessage(sb.toString())
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }

                })
                .create()
                .show();
    }
}
