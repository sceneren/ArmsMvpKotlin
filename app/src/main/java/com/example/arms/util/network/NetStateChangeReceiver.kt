package com.example.arms.util.network

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager


import java.util.ArrayList

/**
 * 监听网络状态变化的BroadcastReceiver
 * @author cd
 */
class NetStateChangeReceiver : BroadcastReceiver() {

    private val mObservers = ArrayList<NetStateChangeObserver>()

    object InstanceHolder {
        internal val INSTANCE = NetStateChangeReceiver()
    }

    override fun onReceive(context: Context, intent: Intent) {
        if (ConnectivityManager.CONNECTIVITY_ACTION == intent.action) {
            val networkType = NetworkUtils.getNetworkType(context)
            notifyObservers(networkType)
        }
    }

    /**
     * 通知所有的Observer网络状态变化
     */
    private fun notifyObservers(networkType: NetworkType) {
        if (networkType == NetworkType.NETWORK_NO) {
            for (observer in mObservers) {
                observer.onNetDisconnected()
            }
        } else {
            for (observer in mObservers) {
                observer.onNetConnected(networkType)
            }
        }
    }

    companion object {

        /**
         * 注册网络监听
         */
        fun registerReceiver(context: Context) {
            val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
            context.registerReceiver(InstanceHolder.INSTANCE, intentFilter)
        }

        /**
         * 取消网络监听
         */
        fun unregisterReceiver(context: Context) {
            context.unregisterReceiver(InstanceHolder.INSTANCE)
        }

        /**
         * 注册网络变化Observer
         */
        fun registerObserver(observer: NetStateChangeObserver?) {
            if (observer == null) {
                return
            }
            if (!InstanceHolder.INSTANCE.mObservers.contains(observer)) {
                InstanceHolder.INSTANCE.mObservers.add(observer)
            }
        }

        /**
         * 取消网络变化Observer的注册
         */
        fun unregisterObserver(observer: NetStateChangeObserver?) {
            if (observer == null) {
                return
            }
            if (InstanceHolder.INSTANCE.mObservers == null) {
                return
            }
            InstanceHolder.INSTANCE.mObservers.remove(observer)
        }
    }
}
