package com.daomingedu.art.mvp.presenter

import android.app.Application
import com.jess.arms.di.scope.ActivityScope
import com.jess.arms.http.imageloader.ImageLoader
import com.jess.arms.integration.AppManager
import com.jess.arms.mvp.BasePresenter
import com.daomingedu.art.app.Constant
import com.daomingedu.art.app.Preference
import com.daomingedu.art.mvp.contract.SplashContract
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject


@ActivityScope
class SplashPresenter
@Inject
constructor(model: SplashContract.Model, rootView: SplashContract.View) :
    BasePresenter<SplashContract.Model, SplashContract.View>(model, rootView) {
    @Inject
    lateinit var mErrorHandler: RxErrorHandler
    @Inject
    lateinit var mApplication: Application
    @Inject
    lateinit var mImageLoader: ImageLoader
    @Inject
    lateinit var mAppManager: AppManager

    private var mCosPath by Preference(Constant.COS_PATH, "")
    override fun onDestroy() {
        super.onDestroy()
    }

    /*fun fileSize(){
        mModel.fileSize(Constant.KEY)
            .subscribeOn(Schedulers.io())
            .retryWhen(RetryWithDelay(2, 5))
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(AndroidSchedulers.mainThread())
            .compose(RxLifecycleUtils.bindToLifecycle(mRootView))
            .subscribe(object : ErrorHandleSubscriber<BaseJson<FileSizeBean>>(mErrorHandler) {
                override fun onNext(t: BaseJson<FileSizeBean>) {
                    if (t.code == Api.SUCCESS) {
                        val data = t.data
                        if (data != null) {
                            mImageFileSize = data.image.toLong()
                            mRecordFileSize = data.record.toLong()
                            mVideoFileSize = data.video.toLong()
                        }
                        mCosPath = t.data?.cosPath?:""
                        mRootView.requestFileSizeSuccess()
                    } else {
                        mRootView.requestFileSizeFail()
                        mRootView.showMessage(t.msg)
                    }
                }

                override fun onError(t: Throwable) {
                    super.onError(t)
                }
            })
    }*/
}
