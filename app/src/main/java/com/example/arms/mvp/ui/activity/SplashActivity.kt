package com.example.arms.mvp.ui.activity

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.arms.R
import com.example.arms.di.component.DaggerSplashComponent
import com.example.arms.di.module.SplashModule
import com.example.arms.mvp.contract.SplashContract
import com.example.arms.mvp.presenter.SplashPresenter
import com.jakewharton.rxbinding3.view.clicks
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.mvp.IView
import com.jess.arms.utils.ArmsUtils
import com.jess.arms.utils.RxLifecycleUtils
import com.tbruyelle.rxpermissions2.RxPermissions
import kotlinx.android.synthetic.main.activity_splash.*
import org.threeten.bp.Instant
import timber.log.Timber
import java.util.concurrent.TimeUnit


/**
 * 如果没presenter
 * 你可以这样写
 *
 * @ActivityScope(請注意命名空間) class NullObjectPresenterByActivity
 * @Inject constructor() : IPresenter {
 * override fun onStart() {
 * }
 *
 * override fun onDestroy() {
 * }
 * }
 */
class SplashActivity : BaseActivity<SplashPresenter>(), SplashContract.View {
    private var rxPermissions: RxPermissions? = null
    override fun setupActivityComponent(appComponent: AppComponent) {
        rxPermissions = RxPermissions(this)
        DaggerSplashComponent //如找不到该类,请编译一下项目
            .builder()
            .appComponent(appComponent)
            .splashModule(SplashModule(this))
            .build()
            .inject(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_FullScreenTheme)
        super.onCreate(savedInstanceState)
    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_splash //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    override fun initData(savedInstanceState: Bundle?) {
        btnTest.clicks()
            .throttleFirst(1, TimeUnit.SECONDS)
            .compose(
                rxPermissions?.ensure(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            )
            .compose(RxLifecycleUtils.bindToLifecycle(this as IView))
            .subscribe {
                mPresenter?.testToMain()
                Timber.e("点击时间：" + Instant.now())
            }
    }


    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun toMain() {
        Toast.makeText(this, "测试", Toast.LENGTH_SHORT).show()
    }

    override fun showMessage(message: String) {
        ArmsUtils.snackbarText(message)
    }

    override fun launchActivity(intent: Intent) {
        ArmsUtils.startActivity(intent)
    }

    override fun killMyself() {
        finish()
    }

    override fun onBackPressed() {
    }
}
