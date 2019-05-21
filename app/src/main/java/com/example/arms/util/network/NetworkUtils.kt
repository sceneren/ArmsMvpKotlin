package com.example.arms.util.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.annotation.RequiresPermission
import android.telephony.TelephonyManager

/**
 * 网络状态相关工具类
 */
class NetworkUtils private constructor() {

    init {
        throw UnsupportedOperationException("u can't instantiate me...")
    }

    companion object {

        /**
         * 获取活动网络信息
         *
         * 需添加权限 `<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>`
         *
         * @return NetworkInfo
         */
        @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
        private fun getActiveNetworkInfo(context: Context): NetworkInfo? {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.activeNetworkInfo
        }

        /**
         * 获取当前网络类型
         *
         * 需添加权限 `<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>`
         *
         * @return 网络类型
         *
         *  * [NetworkType.NETWORK_WIFI]
         *  * [NetworkType.NETWORK_4G]
         *  * [NetworkType.NETWORK_3G]
         *  * [NetworkType.NETWORK_2G]
         *  * [NetworkType.NETWORK_UNKNOWN]
         *  * [NetworkType.NETWORK_NO]
         *
         */
        @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
        fun getNetworkType(context: Context): NetworkType {
            var netType = NetworkType.NETWORK_NO
            val info = getActiveNetworkInfo(context)
            if (info != null && info.isAvailable) {

                if (info.type == ConnectivityManager.TYPE_WIFI) {
                    netType = NetworkType.NETWORK_WIFI
                } else if (info.type == ConnectivityManager.TYPE_MOBILE) {
                    when (info.subtype) {

                        TelephonyManager.NETWORK_TYPE_TD_SCDMA, TelephonyManager.NETWORK_TYPE_EVDO_A, TelephonyManager.NETWORK_TYPE_UMTS, TelephonyManager.NETWORK_TYPE_EVDO_0, TelephonyManager.NETWORK_TYPE_HSDPA, TelephonyManager.NETWORK_TYPE_HSUPA, TelephonyManager.NETWORK_TYPE_HSPA, TelephonyManager.NETWORK_TYPE_EVDO_B, TelephonyManager.NETWORK_TYPE_EHRPD, TelephonyManager.NETWORK_TYPE_HSPAP -> netType = NetworkType.NETWORK_3G

                        TelephonyManager.NETWORK_TYPE_LTE, TelephonyManager.NETWORK_TYPE_IWLAN -> netType = NetworkType.NETWORK_4G

                        TelephonyManager.NETWORK_TYPE_GSM, TelephonyManager.NETWORK_TYPE_GPRS, TelephonyManager.NETWORK_TYPE_CDMA, TelephonyManager.NETWORK_TYPE_EDGE, TelephonyManager.NETWORK_TYPE_1xRTT, TelephonyManager.NETWORK_TYPE_IDEN -> netType = NetworkType.NETWORK_2G
                        else -> {

                            val subtypeName = info.subtypeName
                            if (subtypeName.equals("TD-SCDMA", ignoreCase = true)
                                    || subtypeName.equals("WCDMA", ignoreCase = true)
                                    || subtypeName.equals("CDMA2000", ignoreCase = true)) {
                                netType = NetworkType.NETWORK_3G
                            } else {
                                netType = NetworkType.NETWORK_UNKNOWN
                            }
                        }
                    }
                } else {
                    netType = NetworkType.NETWORK_UNKNOWN
                }
            }
            return netType
        }
    }
}
