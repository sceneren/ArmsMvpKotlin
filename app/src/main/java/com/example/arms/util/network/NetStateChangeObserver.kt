package com.example.arms.util.network

/**
 * 网络状态变化观察者
 */
interface NetStateChangeObserver {

    fun onNetDisconnected()

    fun onNetConnected(networkType: NetworkType)
}
