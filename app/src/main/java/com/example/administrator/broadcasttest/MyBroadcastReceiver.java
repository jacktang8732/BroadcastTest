package com.example.administrator.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/11/13.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "received in myBroadcastReceiver", Toast.LENGTH_SHORT).show();
        abortBroadcast();//可以截断广播，后面的广播接收器无法结合艘此广播
    }
}
