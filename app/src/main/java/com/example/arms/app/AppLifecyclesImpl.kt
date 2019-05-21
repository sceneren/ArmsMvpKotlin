/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.arms.app

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.alibaba.android.arouter.launcher.ARouter
import com.example.arms.BuildConfig
import com.example.arms.app.service.InitializeService
import com.example.arms.util.Utils
import com.example.arms.util.network.NetStateChangeReceiver
import com.jakewharton.threetenabp.AndroidThreeTen
import com.jess.arms.base.delegate.AppLifecycles
import com.lzy.imagepicker.ImagePicker
import com.lzy.imagepicker.view.CropImageView
import timber.log.Timber

/**
 * ================================================
 * 展示 [AppLifecycles] 的用法
 *
 *
 * Created by JessYan on 04/09/2017 17:12
 * [Contact me](mailto:jess.yan.effort@gmail.com)
 * [Follow me](https://github.com/JessYanCoding)
 * ================================================
 */
class AppLifecyclesImpl : AppLifecycles {
    override fun attachBaseContext(base: Context) {
        MultiDex.install(base)  //这里比 onCreate 先执行,常用于 MultiDex 初始化,插件化框架的初始化
    }

    override fun onCreate(application: Application) {
        //对单位的自定义配置, 请在 App 启动时完成
        configUnits()
        Utils.app = application
        Preference.init(application)
        AndroidThreeTen.init(application)
        // 注册BroadcastReceiver
        NetStateChangeReceiver.registerReceiver(application)
        if (BuildConfig.DEBUG) {//Timber初始化
            Timber.plant(Timber.DebugTree())
        }
        initImagePicker()
        InitializeService.start(application)
        initARouter(application)
    }

    private fun initARouter(application: Application) {
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(application)
    }

    private fun initImagePicker() {
        ImagePicker.getInstance().apply {
            imageLoader = GlideImageLoader()
            isCrop = true        //允许裁剪（单选才有效）
            isSaveRectangle = false //是否按矩形区域保存
            style = CropImageView.Style.CIRCLE  //裁剪框的形状
            focusWidth = 800   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
            focusHeight = 800  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
            outPutX = 500//保存文件的宽度。单位像素
            outPutY = 500//保存文件的高度。单位像素
            isMultiMode = false
        }
    }

    override fun onTerminate(application: Application) {

    }

    /**
     * 注意!!! 布局时的实时预览在开发阶段是一个很重要的环节, 很多情况下 Android Studio 提供的默认预览设备并不能完全展示我们的设计图
     * 所以我们就需要自己创建模拟设备, 以下链接是给大家的福利, 按照链接中的操作可以让预览效果和设计图完全一致!
     * @see [dp、pt、in、mm 这四种单位的模拟设备创建方法](https://github.com/JessYanCoding/AndroidAutoSize/blob/master/README-zh.md.preview)
     *
     *
     * v0.9.0 以后, AndroidAutoSize 强势升级, 将这个方案做到极致, 现在支持5种单位
     */
    private fun configUnits() {
        //AndroidAutoSize 默认开启对 dp 的支持, 调用 UnitsManager.setSupportDP(false); 可以关闭对 dp 的支持
        //主单位 dp 和 副单位可以同时开启的原因是, 对于旧项目中已经使用了 dp 进行布局的页面的兼容
        //让开发者的旧项目可以渐进式的从 dp 切换到副单位, 即新页面用副单位进行布局, 然后抽时间逐渐的将旧页面的布局单位从 dp 改为副单位
        //最后将 dp 全部改为副单位后, 再使用 UnitsManager.setSupportDP(false); 将 dp 的支持关闭, 彻底隔离修改 density 所造成的不良影响
        //如果项目完全使用副单位, 则可以直接以像素为单位填写 AndroidManifest 中需要填写的设计图尺寸, 不需再把像素转化为 dp
//        AutoSizeConfig.getInstance().unitsManager
//            .setSupportDP(false).supportSubunits = Subunits.MM
    }
}
