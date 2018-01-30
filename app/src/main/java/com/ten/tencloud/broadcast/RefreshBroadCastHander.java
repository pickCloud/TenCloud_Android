package com.ten.tencloud.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import com.ten.tencloud.listener.OnRefreshListener;

/**
 * Created by lxq on 2018/1/30.
 */

public class RefreshBroadCastHander {

    /**
     * 权限发生变化
     */
    public final static String PERMISSION_REFRESH_ACTION = "PERMISSION_REFRESH_ACTION";
    /**
     * 切换公司
     */
    public final static String SWITCH_COMPANY_REFRESH_ACTION = "SWITCH_COMPANY_REFRESH_ACTION";

    private LocalBroadcastManager mLocalBroadcastManager;
    private BroadcastReceiver mReceiver;

    private String mAction;

    public RefreshBroadCastHander(Context context, String action) {
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(context);
        mAction = action;
    }

    public void sendBroadCast() {
        Intent intent = new Intent(mAction);
        mLocalBroadcastManager.sendBroadcast(intent);
    }

    public void registerReceiver(final OnRefreshListener onRefreshListener) {
        IntentFilter filter = new IntentFilter();
        filter.addAction(mAction);
        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                onRefreshListener.onRefresh();
            }
        };
        mLocalBroadcastManager.registerReceiver(mReceiver, filter);
    }

    public void unregisterReceiver() {
        mLocalBroadcastManager.unregisterReceiver(mReceiver);
    }
}
