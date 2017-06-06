package com.liuzhao.divineapp.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by liuzhao on 2017/6/5.
 */

public class ShareUtils {
    public static void shareText(Context context) {
        Intent textIntent = new Intent(Intent.ACTION_SEND);
        textIntent.setType("text/plain");
        textIntent.putExtra(Intent.EXTRA_TEXT, "这是一段分享的文字");
        context.startActivity(Intent.createChooser(textIntent, "分享"));
    }
//    public static void shareImage(Context context) {
//        String path = context.getRgetResourcesUri(R.drawable.shu_1);
//        Intent imageIntent = new Intent(Intent.ACTION_SEND);
//        imageIntent.setType("image/jpeg");
//        imageIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(path));
//        context.startActivity(Intent.createChooser(imageIntent, "分享"));
//    }

}
