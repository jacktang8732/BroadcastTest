package com.example.administrator.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.nio.channels.NetworkChannel;

import static android.net.ConnectivityManager.CONNECTIVITY_ACTION;

public class MainActivity extends AppCompatActivity {

    /*private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;*/

    private IntentFilter intentFilter;
    private  LocalReceiver localReceiver;
    private LocalBroadcastManager localBroadcastManager;//通过LocalBroadcastManager对本地广播进行管理，提供发送广播和注册广播接收器的方法
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        localBroadcastManager = localBroadcastManager.getInstance(this);//通过LocalBroadcastManager的getInstance方法获取LocalBroadcastManager实例
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(".LOCAL_BROADCAST");
                localBroadcastManager.sendBroadcast(intent);//通过LocalBroadcastManager实例的sendBroadcast方法发送本地广播
            }
        });
        intentFilter = new IntentFilter();
        intentFilter.addAction(".LOCAL_BROADCAST");
        localReceiver = new LocalReceiver();
        localBroadcastManager.registerReceiver(localReceiver, intentFilter);//通过LocalBroadcastManager实例的registerReceiver方法注册本地广播监听器
     /*   intentFilter = new IntentFilter();
        intentFilter.addAction(CONNECTIVITY_ACTION);
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver, intentFilter);*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(localReceiver);
    }

    private class LocalReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,"receiver local broadcast",Toast.LENGTH_SHORT).show();
        }
    }

   /* @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);
    }

    //定义一个内部类NetworkChangeReceiver,继承BroadcastReceiver
    private class NetworkChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectionManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);//通过getSystemServer方法得到ConnectivityManager实例（系统服务类,用于管理网络连接）
            NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();//通过调用ConnectivityManager实例的getActiveNetworkInfo方法得到NetworkInfo实例
            if (networkInfo != null && networkInfo.isAvailable()) {//调用NetworkInfo实例的isAvailable方法判断当前是否有网络连接
                Toast.makeText(context, "已连接网络", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "未连接网络", Toast.LENGTH_SHORT).show();
            }
        }
    }*/
}
