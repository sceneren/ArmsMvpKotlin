package com.example.arms.mvp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.arms.R
import com.example.arms.app.onClick
import com.example.arms.di.component.DaggerSplashComponent
import com.example.arms.di.module.SplashModule
import com.example.arms.mvp.contract.SplashContract
import com.example.arms.mvp.presenter.SplashPresenter
import com.jess.arms.base.BaseActivity
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import kotlinx.android.synthetic.main.activity_splash.*


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

    override fun setupActivityComponent(appComponent: AppComponent) {
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
//        startActivity<LoginActivity>()
//        overridePendingTransition(0,0)
//        killMyself()
        btnTest.onClick {
            mPresenter?.testToMain()
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
}
