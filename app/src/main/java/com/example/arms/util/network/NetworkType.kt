package com.example.arms.util.network

/**
 * 定义网络类型
 */
enum class NetworkType private constructor(private val desc: String) {
    /**
     * 网络类型
     */
    NETWORK_WIFI("WiFi"),
    NETWORK_4G("4G"),
    NETWORK_3G("3G"),
    NETWORK_2G("2G"),
    NETWORK_UNKNOWN("Unknown"),
    NETWORK_NO("No network");

    override fun toString(): String {
        return desc
    }
}
