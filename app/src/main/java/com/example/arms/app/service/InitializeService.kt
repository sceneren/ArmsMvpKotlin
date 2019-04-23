package com.example.arms.app.service

import android.app.IntentService
import android.content.Context
import android.content.Intent

/**
 * 初始化服务，防止在application的主线程中做耗时初始化
 * @author don
 */
class InitializeService : IntentService("InitializeService") {

    override fun onHandleIntent(intent: Intent?) {
        if (intent != null) {
            val action = intent.action
            if (ACTION_INIT_WHEN_APP_CREATE == action) {
                performInit()
            }
        }
    }

    private fun performInit() {

    }

    companion object {

        private val ACTION_INIT_WHEN_APP_CREATE = "com.example.arms.service.action.INIT"

        fun start(context: Context) {
            val intent = Intent(context, InitializeService::class.java)
            intent.action = ACTION_INIT_WHEN_APP_CREATE
            context.startService(intent)
        }
    }

}
